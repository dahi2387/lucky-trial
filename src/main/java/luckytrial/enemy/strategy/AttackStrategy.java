package luckytrial.enemy.strategy;

import java.util.List;

import luckytrial.squad.SquadMember;

public interface AttackStrategy {
    void attack(List<SquadMember> squad);
}
