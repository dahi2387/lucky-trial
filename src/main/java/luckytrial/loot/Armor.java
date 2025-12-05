package luckytrial.loot;

import luckytrial.squad.SquadMember;

public class Armor extends Loot {
    public Armor(String name, int statValue, Rarity rarity) {
        super(name, statValue, rarity, LootType.ARMOR);
    }

    @Override
    public void equip(SquadMember member) {
        member.equipArmor(this);
    }
}
