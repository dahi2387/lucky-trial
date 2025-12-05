package luckytrial.enemy;

import java.util.Random;

import luckytrial.enemy.strategy.AggressiveAttackStrategy;
import luckytrial.enemy.strategy.DefensiveAttackStrategy;

public class Dragon extends Enemy {
    public Dragon() {
        super("Dragon", 20, 10);
        if (new Random().nextBoolean()) {
            setStrategy(new DefensiveAttackStrategy(this));
        } else {
            setStrategy(new AggressiveAttackStrategy(this));
        }
    }
}
