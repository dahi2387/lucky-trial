package luckytrial.squad.factory;

import luckytrial.squad.SquadMember;
import luckytrial.squad.SuperTank;

public class SuperTankFactory implements SquadMemberFactory {
    @Override
    public SquadMember createMember() {
        return new SuperTank();
    }
}
