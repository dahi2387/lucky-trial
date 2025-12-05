package luckytrial.events;

import java.util.ArrayList;
import java.util.List;

import luckytrial.enemy.Enemy;
import luckytrial.loot.Loot;

public final class EventBus {
    private static final List<GameEventListener> LISTENERS = new ArrayList<>();

    private EventBus() {
    }

    public static void register(GameEventListener listener) {
        LISTENERS.add(listener);
    }

    public static void notifyLootDropped(Loot loot) {
        for (GameEventListener listener : LISTENERS) {
            listener.onLootDropped(loot);
        }
    }

    public static void notifyEnemyDefeated(Enemy enemy) {
        for (GameEventListener listener : LISTENERS) {
            listener.onEnemyDefeated(enemy);
        }
    }
}
