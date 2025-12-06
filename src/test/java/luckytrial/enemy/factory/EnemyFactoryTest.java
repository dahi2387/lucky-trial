package luckytrial.enemy.factory;

import luckytrial.TestSupport;
import luckytrial.enemy.Dragon;
import luckytrial.enemy.Enemy;
import luckytrial.enemy.Goblin;
import luckytrial.enemy.Orc;

public class EnemyFactoryTest {
    public static void main(String[] args) {
        assertFactory(new GoblinFactory(), Goblin.class);
        assertFactory(new OrcFactory(), Orc.class);
        assertFactory(new DragonFactory(), Dragon.class);
        System.out.println("EnemyFactoryTest passed.");
    }

    private static void assertFactory(EnemyFactory factory, Class<? extends Enemy> expected) {
        Enemy enemy = factory.createEnemy();
        TestSupport.assertTrue(expected.isInstance(enemy), "Factory should create " + expected.getSimpleName());
    }
}
