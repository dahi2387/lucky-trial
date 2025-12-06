package luckytrial.squad;

import luckytrial.TestSupport;
import luckytrial.enemy.Goblin;
import luckytrial.enemy.Orc;
import luckytrial.loot.Armor;
import luckytrial.loot.LootBuilder;
import luckytrial.loot.Rarity;
import luckytrial.loot.Weapon;

public class SquadMemberTest {
    public static void main(String[] args) {
        testStats();
        testCombatFlow();
        System.out.println("SquadMemberTest passed.");
    }

    private static void testStats() {
        assertMemberStats(new Warrior(), "Warrior", 10, 5);
        assertMemberStats(new Tank(), "Tank", 20, 3);
        assertMemberStats(new Wizard(), "Wizard", 5, 5);
        assertMemberStats(new Sniper(), "Sniper", 10, 20);
        assertMemberStats(new AllRounder(), "AllRounder", 15, 10);
        assertMemberStats(new SuperTank(), "SuperTank", 40, 5);
        assertMemberStats(new God(), "God", 50, 50);
    }

    private static void assertMemberStats(SquadMember member, String name, int hp, int dmg) {
        TestSupport.assertEquals(name, member.getName(), "Name");
        TestSupport.assertEquals(hp, member.getMaxHealth(), "Health");
        TestSupport.assertEquals(dmg, member.getBaseDamage(), "Damage");
    }

    private static void testCombatFlow() {
        Warrior warrior = new Warrior();
        Goblin goblin = new Goblin();
        warrior.receiveDamage(3, goblin);
        TestSupport.assertEquals(7, warrior.getCurrentHealth(), "Damage applied");
        warrior.healToFull();
        TestSupport.assertEquals(warrior.getMaxHealth(), warrior.getCurrentHealth(), "Heal to full");

        Weapon weapon = new LootBuilder()
                .setName("Practice Blade")
                .setStatValue(2)
                .setRarity(Rarity.COMMON)
                .buildWeapon();
        warrior.equipWeapon(weapon);
        Armor armor = new LootBuilder()
                .setName("Practice Armor")
                .setStatValue(2)
                .setRarity(Rarity.COMMON)
                .buildArmor();
        warrior.equipArmor(armor);
        int expectedMax = 12;
        TestSupport.assertEquals(expectedMax, warrior.getMaxHealth(), "Armor boosts health");

        Orc orc = new Orc();
        int orcBefore = orc.getCurrentHealth();
        int dealt = warrior.attack(orc);
        int expectedDamage = warrior.getBaseDamage() + weapon.getStatValue();
        TestSupport.assertEquals(expectedDamage, dealt, "Damage value");
        TestSupport.assertEquals(orcBefore - expectedDamage, orc.getCurrentHealth(), "Enemy takes damage");

        warrior.receiveDamage(100, orc);
        TestSupport.assertFalse(warrior.isAlive(), "Warrior defeated");
        warrior.revive();
        TestSupport.assertTrue(warrior.isAlive(), "Warrior revived");
        TestSupport.assertEquals(expectedMax, warrior.getCurrentHealth(), "Revive restores health");
    }
}
