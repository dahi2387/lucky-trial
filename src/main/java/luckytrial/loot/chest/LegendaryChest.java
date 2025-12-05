package luckytrial.loot.chest;

import luckytrial.loot.ChestReward;
import luckytrial.loot.strategy.LegendaryDropStrategy;

public class LegendaryChest extends Chest {
    public LegendaryChest() {
        super(new LegendaryDropStrategy());
    }

    @Override
    public ChestReward open() {
        return ChestReward.forLoot(getDropStrategy().generateLoot());
    }
}
