package luckytrial.squad.factory;

import luckytrial.squad.SquadMember;
import luckytrial.squad.Tank;

public class TankFactory implements SquadMemberFactory {
    @Override
    public SquadMember createMember() {
        return new Tank();
    }
}
