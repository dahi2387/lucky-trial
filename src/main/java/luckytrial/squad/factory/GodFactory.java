package luckytrial.squad.factory;

import luckytrial.squad.God;
import luckytrial.squad.SquadMember;

public class GodFactory implements SquadMemberFactory {
    @Override
    public SquadMember createMember() {
        return new God();
    }
}
