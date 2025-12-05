package luckytrial.loot.chest;

import luckytrial.loot.ChestReward;
import luckytrial.loot.strategy.DropStrategy;

public abstract class Chest {
    private DropStrategy dropStrategy;

    protected Chest(DropStrategy dropStrategy) {
        this.dropStrategy = dropStrategy;
    }

    public void setDropStrategy(DropStrategy dropStrategy) {
        this.dropStrategy = dropStrategy;
    }

    protected DropStrategy getDropStrategy() {
        return dropStrategy;
    }

    public abstract ChestReward open();
}
