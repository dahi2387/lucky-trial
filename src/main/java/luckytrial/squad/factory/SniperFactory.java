package luckytrial.squad.factory;

import luckytrial.squad.Sniper;
import luckytrial.squad.SquadMember;

public class SniperFactory implements SquadMemberFactory {
    @Override
    public SquadMember createMember() {
        return new Sniper();
    }
}
