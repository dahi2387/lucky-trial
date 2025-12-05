package luckytrial.events;

import luckytrial.enemy.Enemy;
import luckytrial.loot.Loot;

public interface GameEventListener {
    void onLootDropped(Loot loot);
    void onEnemyDefeated(Enemy enemy);
}
