package lolice.xyz.Skill;

import lolice.xyz.Enemies.Enemy_init;
import lolice.xyz.Players.Characters_init;

import java.io.Serial;
import java.io.Serializable;

public class Effect implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String EffectName;
    private final String Description;
    private final int Damage;
    private int Duration;
    private final EffectType Type;

    public Effect(String EffectName, String Description, int Damage, int Duration, EffectType Type) {
        this.EffectName = EffectName;
        this.Description = Description;
        this.Damage = Damage;
        this.Duration = Duration;
        this.Type = Type;
    }

    public String getEffectName() {
        return EffectName;
    }

    public String getDescription() {
        return Description;
    }

    public int getDamage() {
        return Damage;
    }

    public int getDuration() {
        return Duration;
    }

    public EffectType getType() {
        return Type;
    }

    public void setDuration(int Duration) {
        this.Duration = Duration;
    }

    public void decreaseDuration() {
        setDuration(getDuration() - 1);
    }

    public void applyEffect(Characters_init player, Enemy_init enemy) {
        switch (Type) {
            case BUFF:
                if (Description.equals("Give 10% damage buff")) {
                    player.setStrength(player.getStrength()*110/100);
                    System.out.println("Buff applied");
                }
                break;
            case DEBUFF:
                System.out.println("Debuff applied");
                break;
            case POISON:
                System.out.println("Poison applied");
                break;
            case HEAL:
                System.out.println("Heal applied");
                break;
            case DAMAGE:
                System.out.println("Damage applied");
                break;
            case STUN:
                System.out.println("Stun applied");
                if (Description.equals("Stun the enemy")) {
                    player.stunEnemy(enemy);
                    System.out.println("Stun applied");
                }
                break;
        }
    }

    public void removeEffect(Characters_init player, Enemy_init enemy) {
        switch (Type) {
            case BUFF:
                if (Description.equals("Give 10% damage buff")) {
                    player.setStrength(player.getStrength()*100/110);
                    System.out.println("Buff removed");
                }
                break;
            case DEBUFF:
                System.out.println("Debuff removed");
                break;
            case POISON:
                System.out.println("Poison removed");
                break;
            case HEAL:
                System.out.println("Heal removed");
                break;
            case DAMAGE:
                System.out.println("Damage removed");
                break;
            case STUN:
                System.out.println("Stun removed");
                if (Description.equals("Stun the enemy")) {
                    enemy.setStunned(false);
                    System.out.println("Stun removed");
                }
                break;
        }
    }

    public static class StunEffect extends Effect {
        public StunEffect(String EffectName, String Description, int Duration) {
            super(EffectName, Description, 0, Duration, EffectType.STUN);
        }

        @Override
        public void applyEffect(Characters_init player, Enemy_init enemy) {
            if (player != null) {
                System.out.println("Applying stun to player: " + getDescription());
                player.setStunned(true);
            } else if (enemy != null) {
                System.out.println("Applying stun to enemy: " + getDescription());
                enemy.setStunned(true);
            }
        }

        @Override
        public void removeEffect(Characters_init player, Enemy_init enemy) {
            if (player != null) {
                System.out.println("Removing stun from player: " + getDescription());
                player.setStunned(false);
            } else if (enemy != null) {
                System.out.println("Removing stun from enemy: " + getDescription());
                enemy.setStunned(false);
            }
        }
    }

    public static class BuffEffect extends Effect {
        public BuffEffect(String EffectName, String Description, int Duration) {
            super(EffectName, Description, 0, Duration, EffectType.BUFF);
        }

        @Override
        public void applyEffect(Characters_init player, Enemy_init enemy) {
            if (player != null) {
                System.out.println("Applying buff to player: " + getDescription());
                player.setStrength(player.getStrength()*110/100);
            } else if (enemy != null) {
                System.out.println("Applying buff to enemy: " + getDescription());
                enemy.setStrength(enemy.getStrength()*110/100);
            }
        }

        @Override
        public void removeEffect(Characters_init player, Enemy_init enemy) {
            if (player != null) {
                System.out.println("Removing buff from player: " + getDescription());
                player.setStrength(player.getStrength()*100/110);
            } else if (enemy != null) {
                System.out.println("Removing buff from enemy: " + getDescription());
                enemy.setStrength(enemy.getStrength()*100/110);
            }
        }
    }
}
