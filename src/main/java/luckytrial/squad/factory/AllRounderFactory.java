package luckytrial.squad.factory;

import luckytrial.squad.AllRounder;
import luckytrial.squad.SquadMember;

public class AllRounderFactory implements SquadMemberFactory {
    @Override
    public SquadMember createMember() {
        return new AllRounder();
    }
}
