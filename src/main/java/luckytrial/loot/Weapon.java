package luckytrial.loot;

import luckytrial.squad.SquadMember;

public class Weapon extends Loot {
    public Weapon(String name, int statValue, Rarity rarity) {
        super(name, statValue, rarity, LootType.WEAPON);
    }

    @Override
    public void equip(SquadMember member) {
        member.equipWeapon(this);
    }
}
