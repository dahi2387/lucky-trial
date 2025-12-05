package luckytrial.enemy.factory;

import luckytrial.enemy.Dragon;
import luckytrial.enemy.Enemy;

public class DragonFactory implements EnemyFactory {
    @Override
    public Enemy createEnemy() {
        return new Dragon();
    }
}
