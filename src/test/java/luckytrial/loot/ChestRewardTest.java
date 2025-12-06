package luckytrial.loot;

import luckytrial.TestSupport;
import luckytrial.squad.Warrior;

public class ChestRewardTest {
    public static void main(String[] args) {
        Loot loot = new LootBuilder()
                .setName("Reward Blade")
                .setStatValue(4)
                .setRarity(Rarity.COMMON)
                .buildWeapon();
        ChestReward lootReward = ChestReward.forLoot(loot);
        TestSupport.assertTrue(lootReward.hasLoot(), "Loot reward flag");
        TestSupport.assertEquals(loot, lootReward.getLoot(), "Loot reference");
        TestSupport.assertFalse(lootReward.hasRecruit(), "No recruit for loot");

        Warrior recruit = new Warrior();
        ChestReward recruitReward = ChestReward.forRecruit(recruit);
        TestSupport.assertTrue(recruitReward.hasRecruit(), "Recruit flag");
        TestSupport.assertEquals(recruit, recruitReward.getRecruit(), "Recruit reference");
        TestSupport.assertFalse(recruitReward.hasLoot(), "No loot for recruit");
        System.out.println("ChestRewardTest passed.");
    }
}
