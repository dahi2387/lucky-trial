package luckytrial.enemy.factory;

import luckytrial.enemy.Enemy;
import luckytrial.enemy.Goblin;

public class GoblinFactory implements EnemyFactory {
    @Override
    public Enemy createEnemy() {
        return new Goblin();
    }
}
