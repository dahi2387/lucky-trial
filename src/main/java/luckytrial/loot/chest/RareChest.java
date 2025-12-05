package luckytrial.loot.chest;

import luckytrial.loot.ChestReward;
import luckytrial.loot.strategy.RareDropStrategy;

public class RareChest extends Chest {
    public RareChest() {
        super(new RareDropStrategy());
    }

    @Override
    public ChestReward open() {
        return ChestReward.forLoot(getDropStrategy().generateLoot());
    }
}
