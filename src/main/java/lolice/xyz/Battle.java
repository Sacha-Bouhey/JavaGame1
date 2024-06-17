package lolice.xyz;

import lolice.xyz.Enemies.Enemy_init;
import lolice.xyz.Players.Characters_init;
import lolice.xyz.Players.Leveling;
import lolice.xyz.Players.Player_choice;

import java.util.List;

public class Battle {
    private final Characters_init player;
    private final Enemy_init enemies;

    public Battle(Characters_init player, Enemy_init enemies) {
        this.player = player;
        this.enemies = enemies;
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
        System.out.println("You have encountered " + enemies.getName());
        //while the battle isn't over
        while (!isBattleOver()) {
            playerTurn();
            if (isBattleOver()) {
                System.out.println("You have gained experience points");
                Leveling.gainExp(enemies, player);
                break;
            }
            enemiesTurn();
        }
    }

    //Player turn
    private void playerTurn() {
        boolean player_choice = false;
        System.out.println(player.getName()+"'s turn");

        //While player hasn't chosen a skill
        while(!player_choice) {
            System.out.println("Choose an action");
            //List all skills of the player
            List<Skill> player_skill_list = player.getSkills();
            int y = 0;
            for (int i = 0; i < player_skill_list.size(); i++) {
                if(player_skill_list.get(i).isActive()) {
                    y++;
                    System.out.println(y  + ". " + player_skill_list.get(i).getName());
                }
            }

            //Get player choice
            String skillName = Player_choice.GetSkillName();
            //if skillName doesn't exist, ask for another skill
            if(getSkillByName(skillName) == null) {
                System.out.println("Skill doesn't exist");
                continue;
            }

            //Make player choice in a skill instance
            Skill playerskill = getSkillByName(skillName);

            //Use skill if it exists
            if(playerskill.isActive()) {
                if (player.checkMana(playerskill.getManaCost())) {
                    player.useSkill(playerskill);
                    System.out.println(playerskill.getName() + " hit the enemy for " + player.useSkill(playerskill) + " damage");
                    enemies.takeDamage(player.useSkill(playerskill));
                    System.out.println(enemies.getName() + " has " + enemies.getHealth() + " health left");
                    player_choice = true;

                } else {
                    System.out.println("Not enough mana");
                }
            } else {
                System.out.println("Skill is not active");
            }
        }
    }
    private void enemiesTurn() {
        //TODO: enemy AI
        System.out.println(enemies.getName() + "'s turn");
        //Get a random active skill from the enemy
        List<Skill> enemy_skill_list = enemies.getSkills();
        Skill enemyskill = enemy_skill_list.get((int) (Math.random() * enemy_skill_list.size()));
        //Use skill
        if(enemies.getMana() >= enemyskill.getManaCost()) {
            enemies.useSkill(enemyskill);
            System.out.println(enemyskill.getName() + " hit you for " + enemies.useSkill(enemyskill) + " damage");
            player.TakeDamage(enemies.useSkill(enemyskill));
            System.out.println(player.getName() + " has " + player.getHealth() + " health left");
        } else {
            System.out.println("Not enough mana");
        }
    }

    private boolean isBattleOver() {
        return player.getHealth() <= 0 || enemies.getHealth() <= 0;
    }
}
