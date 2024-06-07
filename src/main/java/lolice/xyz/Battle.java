package lolice.xyz;

import java.util.List;

public class Battle {
    private Characters_init player;
    private List<Enemy_init> enemies;

    public Battle(Characters_init player, List<Enemy_init> enemies) {
        this.player = player;
        this.enemies = enemies;
    }
    public void Start() {
        System.out.println("Battle started !");
        System.out.println("You have encountered " + enemies);
    }
    private void playerTurn() {
        System.out.println(player.getName()+"'s turn");
    }
    private void enemyTurn() {

    }
    private boolean isBattleOver() {
        return false;
    }
}
