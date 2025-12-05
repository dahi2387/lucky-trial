package luckytrial.enemy.factory;

import luckytrial.enemy.Enemy;
import luckytrial.enemy.Orc;

public class OrcFactory implements EnemyFactory {
    @Override
    public Enemy createEnemy() {
        return new Orc();
    }
}
