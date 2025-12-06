package luckytrial.loot.chest;

import java.util.List;

import luckytrial.TestSupport;
import luckytrial.loot.ChestReward;
import luckytrial.loot.Loot;
import luckytrial.squad.SquadMember;
import luckytrial.squad.Warrior;
import luckytrial.squad.factory.WarriorFactory;
import luckytrial.squad.factory.WizardFactory;

public class ChestTest {
    public static void main(String[] args) {
        BasicChest basicChest = new BasicChest();
        ChestReward basicReward = basicChest.open();
        TestSupport.assertTrue(basicReward.hasLoot(), "Basic chest contains loot");
        Loot basicLoot = basicReward.getLoot();
        TestSupport.assertTrue(basicLoot.getStatValue() >= 1 && basicLoot.getStatValue() <= 3, "Basic loot range");

        RareChest rareChest = new RareChest();
        Loot rareLoot = rareChest.open().getLoot();
        TestSupport.assertTrue(rareLoot.getStatValue() >= 4 && rareLoot.getStatValue() <= 8, "Rare loot range");

        LegendaryChest legendaryChest = new LegendaryChest();
        Loot legendaryLoot = legendaryChest.open().getLoot();
        TestSupport.assertTrue(legendaryLoot.getStatValue() >= 9 && legendaryLoot.getStatValue() <= 10, "Legendary loot range");

        SquadChest squadChest = new SquadChest(List.of(new WarriorFactory(), new WizardFactory()));
        ChestReward squadReward = squadChest.open();
        TestSupport.assertTrue(squadReward.hasRecruit(), "Squad chest returns recruit");
        SquadMember recruit = squadReward.getRecruit();
        TestSupport.assertTrue(recruit instanceof Warrior || "Wizard".equals(recruit.getName()), "Recruit from configured factories");

        TestSupport.assertTrue(new BasicChestFactory().createChest() instanceof BasicChest, "Basic factory");
        TestSupport.assertTrue(new RareChestFactory().createChest() instanceof RareChest, "Rare factory");
        TestSupport.assertTrue(new LegendaryChestFactory().createChest() instanceof LegendaryChest, "Legendary factory");
        TestSupport.assertTrue(new SquadChestFactory(List.of(new WarriorFactory())).createChest() instanceof SquadChest, "Squad factory");
        System.out.println("ChestTest passed.");
    }
}
