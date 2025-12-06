package luckytrial.loot;

import luckytrial.TestSupport;
import luckytrial.squad.Warrior;

public class LootBuilderTest {
    public static void main(String[] args) {
        LootBuilder builder = new LootBuilder()
                .setName("Steel Saber")
                .setStatValue(7)
                .setRarity(Rarity.RARE);
        Weapon weapon = builder.buildWeapon();
        TestSupport.assertEquals("Steel Saber", weapon.getName(), "Weapon name");
        TestSupport.assertEquals(7, weapon.getStatValue(), "Weapon stat");
        TestSupport.assertEquals(Rarity.RARE, weapon.getRarity(), "Weapon rarity");
        TestSupport.assertEquals(LootType.WEAPON, weapon.getLootType(), "Weapon type");

        builder.setName("Iron Plate").setStatValue(3).setRarity(Rarity.COMMON);
        Armor armor = builder.buildArmor();
        TestSupport.assertEquals("Iron Plate", armor.getName(), "Armor name");
        TestSupport.assertEquals(3, armor.getStatValue(), "Armor stat");
        TestSupport.assertEquals(LootType.ARMOR, armor.getLootType(), "Armor type");

        Warrior warrior = new Warrior();
        weapon.equip(warrior);
        TestSupport.assertNotNull(warrior.getWeapon(), "Weapon equipped");
        armor.equip(warrior);
        TestSupport.assertNotNull(warrior.getArmor(), "Armor equipped");
        System.out.println("LootBuilderTest passed.");
    }
}
