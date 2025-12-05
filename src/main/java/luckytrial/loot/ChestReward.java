package luckytrial.loot;

import luckytrial.squad.SquadMember;

public class ChestReward {
    private final Loot loot;
    private final SquadMember recruit;

    private ChestReward(Loot loot, SquadMember recruit) {
        this.loot = loot;
        this.recruit = recruit;
    }

    public static ChestReward forLoot(Loot loot) {
        return new ChestReward(loot, null);
    }

    public static ChestReward forRecruit(SquadMember recruit) {
        return new ChestReward(null, recruit);
    }

    public boolean hasLoot() {
        return loot != null;
    }

    public Loot getLoot() {
        return loot;
    }

    public boolean hasRecruit() {
        return recruit != null;
    }

    public SquadMember getRecruit() {
        return recruit;
    }
}
