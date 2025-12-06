package luckytrial.game;

import java.util.List;
import java.util.Random;

import luckytrial.TestSupport;
import luckytrial.enemy.Enemy;
import luckytrial.enemy.factory.EnemyFactory;
import luckytrial.enemy.Goblin;

public class WaveTest {
    public static void main(String[] args) {
        List<EnemyFactory> factories = List.of(Goblin::new);
        Wave wave = Wave.randomWave(factories, new Random(1));
        TestSupport.assertEquals(3, wave.getEnemies().size(), "Three enemies per wave");
        TestSupport.assertFalse(wave.isCleared(), "Wave starts uncleared");
        for (Enemy enemy : wave.getEnemies()) {
            enemy.receiveDamage(100);
        }
        TestSupport.assertTrue(wave.isCleared(), "Wave cleared after defeats");
        System.out.println("WaveTest passed.");
    }
}
