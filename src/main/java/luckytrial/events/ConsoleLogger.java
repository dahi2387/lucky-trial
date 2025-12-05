package luckytrial.events;

import luckytrial.enemy.Enemy;
import luckytrial.loot.Loot;

public class ConsoleLogger implements GameEventListener {
    @Override
    public void onLootDropped(Loot loot) {
        System.out.println("[Event] Loot dropped: " + loot.getName() + " (" + loot.getRarity() + ")");
    }

    @Override
    public void onEnemyDefeated(Enemy enemy) {
        System.out.println("[Event] Enemy defeated: " + enemy.getName());
    }
}
