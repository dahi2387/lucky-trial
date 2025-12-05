package luckytrial.loot.chest;

import java.util.List;
import java.util.Random;

import luckytrial.loot.ChestReward;
import luckytrial.squad.SquadMember;
import luckytrial.squad.factory.SquadMemberFactory;

public class SquadChest extends Chest {
    private final List<SquadMemberFactory> factories;
    private final Random random = new Random();

    public SquadChest(List<SquadMemberFactory> factories) {
        super(null);
        this.factories = factories;
    }

    @Override
    public ChestReward open() {
        if (factories.isEmpty()) {
            return ChestReward.forRecruit((SquadMember) null);
        }
        SquadMemberFactory factory = factories.get(random.nextInt(factories.size()));
        return ChestReward.forRecruit(factory.createMember());
    }
}
