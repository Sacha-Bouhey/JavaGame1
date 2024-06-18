package lolice.xyz;


import lolice.xyz.Enemies.Enemy;
import lolice.xyz.Enemies.Enemy_init;
import lolice.xyz.Enemies.Enemy_skill_init;
import lolice.xyz.Players.*;

public class Main {
    public static void main(String[] args) {
        //Init player and enemy
        Characters_init player = Player_init.Player_init(Characters.InitCharValue(), Player_choice.GetClassName());
        Enemy_init enemy = Enemy_skill_init.Enemies_skill_init(Enemy.InitEnemyValue(), "Goblin");
        //Start battle
        Battle battle = new Battle(player, enemy);
        battle.Start();
        player.showInfo();
    }
}