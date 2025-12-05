package luckytrial.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import luckytrial.enemy.Enemy;
import luckytrial.enemy.factory.EnemyFactory;

public class Wave {
    private final List<Enemy> enemies;

    private Wave(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public static Wave randomWave(List<EnemyFactory> factories, Random random) {
        List<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            EnemyFactory factory = factories.get(random.nextInt(factories.size()));
            enemies.add(factory.createEnemy());
        }
        return new Wave(enemies);
    }

    public List<Enemy> getEnemies() {
        return Collections.unmodifiableList(enemies);
    }

    public boolean isCleared() {
        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                return false;
            }
        }
        return true;
    }
}
