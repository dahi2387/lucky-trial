package luckytrial.enemy.strategy;

import java.util.List;

import luckytrial.enemy.Enemy;
import luckytrial.squad.SquadMember;

public class AggressiveAttackStrategy implements AttackStrategy {
    private final Enemy enemy;

    public AggressiveAttackStrategy(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void attack(List<SquadMember> squad) {
        SquadMember target = null;
        for (SquadMember member : squad) {
            if (!member.isAlive()) {
                continue;
            }
            if (target == null || member.getCurrentHealth() < target.getCurrentHealth()) {
                target = member;
            }
        }
        if (target == null) {
            return;
        }
        target.receiveDamage(enemy.getDamage(), enemy);
        System.out.println(enemy.getName() + " aggressively mauls " + target.getName() + " for " + enemy.getDamage() + " damage.");
    }
}
