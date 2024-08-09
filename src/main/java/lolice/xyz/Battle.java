package lolice.xyz;

import lolice.xyz.Enemies.Enemy;
import lolice.xyz.Enemies.Enemy_init;
import lolice.xyz.Iddle.Menu;
import lolice.xyz.Players.Characters_init;
import lolice.xyz.Players.Leveling;
import lolice.xyz.Players.Player_choice;
import lolice.xyz.Skill.*;

import java.util.*;

public class Battle {
    private final Characters_init player;
    private final List<Enemy_init> enemies;
    private final List<Effect> activeEffects;

    public Battle(Characters_init player, List<Enemy_init> enemies) {
        this.player = player;
        this.enemies = enemies;
        this.activeEffects = new ArrayList<>();
    }

    private Skill getSkillByName(String skillName) {
        for(Skill skill : player.getSkills()) {
            if(skill.getName().equals(skillName)) {
                return skill;
            }
        }
        return null;
    }

    //Start the battle
    public void Start() {
        System.out.println("Battle started !");
        System.out.println("You have encountered:");
        for(Enemy_init enemy: enemies) {
            System.out.println("-" + enemy.getName());
        }
        // while the battle isn't over
        while (!isBattleOver()) {
            playerTurn();
            if (isBattleOver()) {
                endBattle();
                break;
            }
            enemiesTurn();
            if (isBattleOver()) {
                endBattle();
                break;
            }
            handleEffectDuration();
        }
    }

    //Player turn
    private void playerTurn() {
        boolean player_choice = false;
        System.out.println(player.getName() + "'s turn");



        // While player hasn't chosen a skill
        while (!player_choice) {
            System.out.println("Choose an action");
            System.out.println("1. Attack");
            System.out.println("2. Use a skill");
            Scanner UserChoice = new Scanner(System.in);
            int Choice = -1;
            try {
                Choice = UserChoice.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                UserChoice.nextInt();
                continue;
            }

            if (Choice == 1) {

                Enemy_init enemy = chooseEnemy();
                System.out.println("You hit the enemy for " + player.getStrength() + " damage");
                enemy.takeDamage(player.getStrength());
                System.out.println(enemy.getName() + " has " + enemy.getHealth() + " health left");
                player_choice = true;

            } else if (Choice == 2) {
                // List all skills of the player
                List<Skill> player_skill_list = player.getSkills();
                int y = 0;
                for (int i = 0; i < player_skill_list.size(); i++) {
                    if (player_skill_list.get(i).isActive()) {
                        y++;
                        System.out.println(y + ". " + player_skill_list.get(i).getName());
                    }
                }

                // Get player choice
                String skillName = Player_choice.GetSkillName();
                // if skillName doesn't exist, ask for another skill
                if (getSkillByName(skillName) == null) {
                    System.out.println("Skill doesn't exist");
                    continue;
                }

                // Make player choice in a skill instance
                Skill playerskill = getSkillByName(skillName);

                // Use skill if it exists
                if (playerskill.isActive()) {
                    if(!playerskill.isPassive()) {

                        Enemy_init enemy = chooseEnemy();
                        if (player.checkMana(playerskill.getManaCost())) {
                            int damage = player.useSkill(playerskill, enemy);
                            System.out.println(playerskill.getName() + " hit the enemy for " + (damage - enemy.getDefence()) + " damage");
                            enemy.takeDamage(damage);
                            System.out.println(enemy.getName() + " has " + enemy.getHealth() + " health left");
                            if (playerskill.getEffect() != null) {
                                for (Effect effect : playerskill.getEffect()) {

                                    if (effect.getType() == EffectType.STUN) {
                                        if (enemy.isStunned()) {
                                            System.out.println(enemy.getName() + " is already stunned");
                                            continue;
                                        } else {
                                            effect.applyEffect(null, enemy);
                                        }
                                    } else if (effect.getType() == EffectType.BUFF) {
                                        effect.applyEffect(player, null);
                                    }
                                }
                            }
                            player_choice = true;
                        } else {
                            System.out.println("Not enough mana");
                        }
                    }
                } else {
                    System.out.println("Skill is not active");
                }
            } else {
                System.out.println("Invalid choice");
            }
        }
    }
    private void enemiesTurn() {
        //TODO: enemy AI
        for(Enemy_init enemy : enemies) {
            if(enemy.isStunned()) {
                System.out.println(enemy.getName() + " is stunned and can't move");
                return;
            }
            System.out.println(enemy.getName() + "'s turn");
            //Get a random active skill from the enemy
            List<Skill> enemy_skill_list = enemy.getSkills();
            Skill enemyskill = enemy_skill_list.get((int) (Math.random() * enemy_skill_list.size()));
            //Use skill
            if(enemy.getMana() >= enemyskill.getManaCost()) {
                enemy.useSkill(enemyskill);
                System.out.println(enemyskill.getName() + " hit you for " + enemy.useSkill(enemyskill) + " damage");
                player.TakeDamage(enemy.useSkill(enemyskill));
                System.out.println(player.getName() + " has " + player.getHealth() + " health left");
            } else {
                System.out.println("Not enough mana");
            }
        }


    }

    private void handleEffectDuration() {
        System.out.println("Handling effects");
        Iterator<Effect> iterator = activeEffects.iterator();
        while (iterator.hasNext()) {
            Effect effect = iterator.next();
            effect.decreaseDuration();
            if (effect.getDuration() <= 0) {
                effect.removeEffect(player, null);
                for(Enemy_init enemy : enemies) {
                    effect.removeEffect(null, enemy);
                }
                iterator.remove();
            }
        }
    }

    private boolean isBattleOver() {
        return player.getHealth() <= 0 || enemies.stream().allMatch(enemy -> enemy.getHealth() <= 0);
    }

    private void endBattle() {
        // TODO: When all status effects are implemented, remove them here
        for(Effect effect : activeEffects) {
            effect.removeEffect(player, null);
            for(Enemy_init enemy : enemies) {
                effect.removeEffect(null, enemy);
            }
        }
        int exp = 0;
        for(Enemy_init enemy : enemies) {
            exp += (enemy.getLeveling().getTotalExp() / 8);
        }
        System.out.println("You have gained " + exp + " experience points");
        Leveling.gainExp(exp, player);
        for (Enemy_init enemy : enemies) {
            player.updateAllQuests(enemy, null, null);
        }

    }

    private Enemy_init chooseEnemy(){
        if (enemies.size() == 1) {
            return enemies.getFirst();
        }
        System.out.println("Choose an enemy to attack");
        for (int i = 0; i < enemies.size(); i++) {
            System.out.println(i + 1 + ". " + enemies.get(i).getName());
        }
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        return enemies.get(choice - 1);
    }
}