package lolice.xyz;


public class Main {
    public static void main(String[] args) {
        //Init player and enemy
        Characters_init player = Player_init.Player_init(Characters.InitCharValue(), Player_choice.GetClassName(args));
        Enemy_init enemy = Enemy_skill_init.Enemy_skill_init(Enemy.InitEnemyValue(), "Goblin");
        //Start battle
        Battle battle = new Battle(player, enemy);
        battle.Start();
    }
}