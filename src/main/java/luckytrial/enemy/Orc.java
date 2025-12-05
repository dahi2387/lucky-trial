package luckytrial.enemy;

import luckytrial.enemy.strategy.AggressiveAttackStrategy;

public class Orc extends Enemy {
    public Orc() {
        super("Orc", 10, 5);
        setStrategy(new AggressiveAttackStrategy(this));
    }
}
