package luckytrial.enemy;

import luckytrial.enemy.strategy.RandomAttackStrategy;

public class Goblin extends Enemy {
    public Goblin() {
        super("Goblin", 4, 2);
        setStrategy(new RandomAttackStrategy(this));
    }
}
