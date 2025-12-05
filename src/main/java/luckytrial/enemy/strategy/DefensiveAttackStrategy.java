package luckytrial.enemy.strategy;

import java.util.List;

import luckytrial.enemy.Enemy;
import luckytrial.squad.SquadMember;

public class DefensiveAttackStrategy implements AttackStrategy {
    private final Enemy enemy;

    public DefensiveAttackStrategy(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void attack(List<SquadMember> squad) {
        SquadMember target = null;
        for (SquadMember member : squad) {
            if (!member.isAlive()) {
                continue;
            }
            if (target == null || member.getCurrentHealth() > target.getCurrentHealth()) {
                target = member;
            }
        }
        if (target == null) {
            return;
        }
        target.receiveDamage(enemy.getDamage(), enemy);
        System.out.println(enemy.getName() + " defensively breathes on " + target.getName() + " for " + enemy.getDamage() + " damage.");
    }
}
