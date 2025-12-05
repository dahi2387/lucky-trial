package luckytrial.loot.chest;

import java.util.List;

import luckytrial.squad.factory.SquadMemberFactory;

public class SquadChestFactory implements ChestFactory {
    private final List<SquadMemberFactory> factories;

    public SquadChestFactory(List<SquadMemberFactory> factories) {
        this.factories = factories;
    }

    @Override
    public Chest createChest() {
        return new SquadChest(factories);
    }
}
