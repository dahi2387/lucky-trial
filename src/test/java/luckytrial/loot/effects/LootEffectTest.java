package luckytrial.loot.effects;

import luckytrial.TestSupport;
import luckytrial.enemy.Orc;
import luckytrial.enemy.Goblin;
import luckytrial.loot.Armor;
import luckytrial.loot.LootBuilder;
import luckytrial.loot.Rarity;
import luckytrial.loot.Weapon;
import luckytrial.squad.Warrior;

public class LootEffectTest {
    public static void main(String[] args) {
        testFireEffect();
        testThornEffect();
        testShieldEffect();
        testValidation();
        System.out.println("LootEffectTest passed.");
    }

    private static void testFireEffect() {
        Weapon weapon = new LootBuilder()
                .setName("Blazing Edge")
                .setStatValue(2)
                .setRarity(Rarity.RARE)
                .buildWeapon();
        FireEffect fireEffect = new FireEffect(weapon);
        Warrior warrior = new Warrior();
        fireEffect.equip(warrior);

        Orc orc = new Orc();
        int before = orc.getCurrentHealth();
        warrior.attack(orc);
        int expectedDamage = warrior.getBaseDamage() + weapon.getStatValue() + 5;
        TestSupport.assertEquals(before - expectedDamage, orc.getCurrentHealth(), "Fire effect extra damage applied");
    }

    private static void testThornEffect() {
        Armor armor = new LootBuilder()
                .setName("Bramble Mail")
                .setStatValue(3)
                .setRarity(Rarity.RARE)
                .buildArmor();
        ThornEffect thornEffect = new ThornEffect(armor);
        Warrior warrior = new Warrior();
        thornEffect.equip(warrior);
        Goblin goblin = new Goblin();
        warrior.receiveDamage(1, goblin);
        TestSupport.assertEquals(0, goblin.getCurrentHealth(), "Thorn effect damages attacker");
    }

    private static void testShieldEffect() {
        Armor armor = new LootBuilder()
                .setName("Shield Vest")
                .setStatValue(1)
                .setRarity(Rarity.RARE)
                .buildArmor();
        ShieldEffect shieldEffect = new ShieldEffect(armor);
        Warrior warrior = new Warrior();
        shieldEffect.equip(warrior);
        int before = warrior.getCurrentHealth();
        Goblin goblin = new Goblin();
        warrior.receiveDamage(2, goblin);
        TestSupport.assertEquals(before, warrior.getCurrentHealth(), "Shield blocks first hit");
    }

    private static void testValidation() {
        Armor armor = new LootBuilder()
                .setName("Wrong Armor")
                .setStatValue(1)
                .setRarity(Rarity.COMMON)
                .buildArmor();
        TestSupport.assertThrows(IllegalArgumentException.class, () -> new FireEffect(armor), "Fire effect cannot wrap armor");
        Weapon weapon = new LootBuilder()
                .setName("Wrong Weapon")
                .setStatValue(1)
                .setRarity(Rarity.COMMON)
                .buildWeapon();
        TestSupport.assertThrows(IllegalArgumentException.class, () -> new ShieldEffect(weapon), "Shield effect cannot wrap weapon");
    }
}
