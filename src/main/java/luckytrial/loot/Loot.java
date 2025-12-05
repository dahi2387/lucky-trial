package luckytrial.loot;

import luckytrial.squad.SquadMember;

public abstract class Loot {
    private final String name;
    private final int statValue;
    private final Rarity rarity;
    private final LootType lootType;

    protected Loot(String name, int statValue, Rarity rarity, LootType lootType) {
        this.name = name;
        this.statValue = statValue;
        this.rarity = rarity;
        this.lootType = lootType;
    }

    public String getName() {
        return name;
    }

    public int getStatValue() {
        return statValue;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public LootType getLootType() {
        return lootType;
    }

    public abstract void equip(SquadMember member);
}
