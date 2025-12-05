package luckytrial.squad.factory;

import luckytrial.squad.SquadMember;
import luckytrial.squad.Warrior;

public class WarriorFactory implements SquadMemberFactory {
    @Override
    public SquadMember createMember() {
        return new Warrior();
    }
}
