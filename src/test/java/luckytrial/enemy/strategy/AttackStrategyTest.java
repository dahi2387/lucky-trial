package luckytrial.enemy.strategy;

import java.util.Arrays;
import java.util.List;

import luckytrial.TestSupport;
import luckytrial.enemy.Enemy;
import luckytrial.squad.AllRounder;
import luckytrial.squad.SquadMember;
import luckytrial.squad.Tank;
import luckytrial.squad.Warrior;

public class AttackStrategyTest {
    public static void main(String[] args) {
        testRandomAttack();
        testAggressiveAttack();
        testDefensiveAttack();
        System.out.println("AttackStrategyTest passed.");
    }

    private static void testRandomAttack() {
        Warrior target = new Warrior();
        TestEnemy enemy = new TestEnemy("Random", 3);
        enemy.setStrategy(new RandomAttackStrategy(enemy));
        enemy.attack(List.of(target));
        TestSupport.assertEquals(7, target.getCurrentHealth(), "Random attack damages only target");
    }

    private static void testAggressiveAttack() {
        Warrior lowHealth = new Warrior();
        Tank highHealth = new Tank();
        highHealth.receiveDamage(5, null);
        lowHealth.receiveDamage(6, null);
        TestEnemy enemy = new TestEnemy("Aggro", 4);
        enemy.setStrategy(new AggressiveAttackStrategy(enemy));
        enemy.attack(Arrays.asList(lowHealth, highHealth));
        TestSupport.assertEquals(0, lowHealth.getCurrentHealth(), "Aggressive attack targets lowest health");
    }

    private static void testDefensiveAttack() {
        Warrior warrior = new Warrior();
        AllRounder allRounder = new AllRounder();
        warrior.receiveDamage(5, null);
        TestEnemy enemy = new TestEnemy("Defensive", 2);
        enemy.setStrategy(new DefensiveAttackStrategy(enemy));
        enemy.attack(Arrays.asList(warrior, allRounder));
        TestSupport.assertEquals(allRounder.getMaxHealth() - 2, allRounder.getCurrentHealth(), "Defensive attack targets highest health");
    }

    private static final class TestEnemy extends Enemy {
        private TestEnemy(String name, int damage) {
            super(name, 30, damage);
        }

        protected void setStrategy(AttackStrategy strategy) {
            super.setStrategy(strategy);
        }
    }
}
