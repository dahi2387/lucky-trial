package luckytrial.loot.chest;

import luckytrial.loot.ChestReward;
import luckytrial.loot.strategy.BasicDropStrategy;

public class BasicChest extends Chest {
    public BasicChest() {
        super(new BasicDropStrategy());
    }

    @Override
    public ChestReward open() {
        return ChestReward.forLoot(getDropStrategy().generateLoot());
    }
}
