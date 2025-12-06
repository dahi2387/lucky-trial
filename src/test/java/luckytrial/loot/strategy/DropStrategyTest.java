package luckytrial.loot.strategy;

import luckytrial.TestSupport;
import luckytrial.loot.Loot;
import luckytrial.loot.LootBuilder;
import luckytrial.loot.Rarity;
import luckytrial.loot.effects.EffectDecorator;

public class DropStrategyTest {
    public static void main(String[] args) {
        BasicDropStrategy basic = new BasicDropStrategy();
        for (int i = 0; i < 10; i++) {
            Loot loot = basic.generateLoot();
            TestSupport.assertTrue(loot.getStatValue() >= 1 && loot.getStatValue() <= 3, "Basic stat range");
            TestSupport.assertTrue(loot.getRarity() == Rarity.COMMON, "Basic rarity");
        }

        RareDropStrategy rare = new RareDropStrategy();
        for (int i = 0; i < 10; i++) {
            Loot loot = rare.generateLoot();
            TestSupport.assertTrue(loot.getStatValue() >= 4 && loot.getStatValue() <= 8, "Rare stat range");
            TestSupport.assertEquals(Rarity.RARE, loot.getRarity(), "Rare rarity");
        }

        LegendaryDropStrategy legendary = new LegendaryDropStrategy();
        for (int i = 0; i < 10; i++) {
            Loot loot = legendary.generateLoot();
            TestSupport.assertTrue(loot.getStatValue() >= 9 && loot.getStatValue() <= 10, "Legendary stat range");
            TestSupport.assertTrue(loot instanceof EffectDecorator, "Legendary always decorated");
            TestSupport.assertEquals(Rarity.LEGENDARY, loot.getRarity(), "Legendary rarity");
        }

        Loot weak = new LootBuilder().setName("Weak").setStatValue(1).setRarity(Rarity.COMMON).buildWeapon();
        Loot strong = new LootBuilder().setName("Strong").setStatValue(8).setRarity(Rarity.RARE).buildWeapon();
        DropStrategy stub = new StubDropStrategy(weak, strong);
        WizardDropStrategy wizardDropStrategy = new WizardDropStrategy(stub);
        Loot picked = wizardDropStrategy.generateLoot();
        TestSupport.assertEquals("Strong", picked.getName(), "Wizard picks better loot");
        System.out.println("DropStrategyTest passed.");
    }

    private static final class StubDropStrategy implements DropStrategy {
        private final Loot[] loot;
        private int index;

        private StubDropStrategy(Loot... loot) {
            this.loot = loot;
        }

        @Override
        public Loot generateLoot() {
            return loot[index++ % loot.length];
        }
    }
}
