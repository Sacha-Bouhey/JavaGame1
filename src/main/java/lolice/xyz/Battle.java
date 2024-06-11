package lolice.xyz;

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
                break;
            }
            enemiesTurn();
        }
    }
    //Player turn
    private void playerTurn() {
        boolean playerchoice = false;
        System.out.println(player.getName()+"'s turn");

        //While player hasn't chosen a skill
        while(!playerchoice) {
            System.out.println("Choose an action");
            //List all skills of the player
            List<Skill> skills = player.getSkills();
            for (int i = 0; i < skills.size(); i++) {
                System.out.println(i + 1 + ". " + skills.get(i).getName());
            }

            //Get player choice
            String skillName = Player_choice.GetSkillName();

            //Make player choice in a skill instance
            Skill playerskill = getSkillByName(skillName);

            //Use skill if it exists
            if (playerskill != null) {
                player.useSkill(playerskill);
                System.out.println(playerskill.getName() + "hit the enemy for" + playerskill.getDamage() + "damage");
                enemies.takeDamage(playerskill.getDamage());
                System.out.println(enemies.getName() + " has " + enemies.getHealth() + " health left");
                playerchoice = true;
            } else {
                System.out.println("Invalid skill name");
            }
        }
    }
    private void enemiesTurn() {
        //TODO: enemy AI
        System.out.println(enemies.getName() + "'s turn");
    }

    private boolean isBattleOver() {
        if(player.getHealth() <= 0 || enemies.getHealth() <= 0)
            return true;
        else
            return false;
    }
}
