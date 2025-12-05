package luckytrial.loot.effects;

import luckytrial.enemy.Enemy;
import luckytrial.squad.SquadMember;

public interface Effect {
    void apply(SquadMember target);
    void apply(Enemy enemy);
}
