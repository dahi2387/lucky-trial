package luckytrial.enemy.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import luckytrial.enemy.Enemy;
import luckytrial.squad.SquadMember;

public class RandomAttackStrategy implements AttackStrategy {
    private final Enemy enemy;
    private final Random random = new Random();

    public RandomAttackStrategy(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void attack(List<SquadMember> squad) {
        List<SquadMember> alive = new ArrayList<>();
        for (SquadMember member : squad) {
            if (member.isAlive()) {
                alive.add(member);
            }
        }
        if (alive.isEmpty()) {
            return;
        }
        SquadMember target = alive.get(random.nextInt(alive.size()));
        target.receiveDamage(enemy.getDamage(), enemy);
        System.out.println(enemy.getName() + " strikes " + target.getName() + " for " + enemy.getDamage() + " damage.");
    }
}
