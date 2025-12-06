package luckytrial.events;

import java.io.ByteArrayOutputStream;

import luckytrial.TestSupport;
import luckytrial.enemy.Goblin;
import luckytrial.loot.Loot;
import luckytrial.loot.LootBuilder;
import luckytrial.loot.Rarity;

public class EventBusTest {
    public static void main(String[] args) {
        TestSupport.clearEventBus();
        TrackingListener listener = new TrackingListener();
        EventBus.register(listener);

        Loot loot = new LootBuilder()
                .setName("Test Blade")
                .setStatValue(5)
                .setRarity(Rarity.RARE)
                .buildWeapon();
        EventBus.notifyLootDropped(loot);
        TestSupport.assertEquals(1, listener.lootEvents, "Loot event count");

        Goblin goblin = new Goblin();
        EventBus.notifyEnemyDefeated(goblin);
        TestSupport.assertEquals(1, listener.enemyEvents, "Enemy event count");

        ConsoleLogger logger = new ConsoleLogger();
        ByteArrayOutputStream print = TestSupport.captureStdOut(() -> {
            logger.onLootDropped(loot);
            logger.onEnemyDefeated(goblin);
        });
        String output = print.toString();
        TestSupport.assertTrue(output.contains("Loot dropped") && output.contains("Enemy defeated"), "Console logger output");
        System.out.println("EventBusTest passed.");
    }

    private static final class TrackingListener implements GameEventListener {
        private int lootEvents;
        private int enemyEvents;

        @Override
        public void onLootDropped(Loot loot) {
            lootEvents++;
        }

        @Override
        public void onEnemyDefeated(luckytrial.enemy.Enemy enemy) {
            enemyEvents++;
        }
    }
}
