package luckytrial.squad.factory;

import luckytrial.squad.SquadMember;
import luckytrial.squad.Wizard;

public class WizardFactory implements SquadMemberFactory {
    @Override
    public SquadMember createMember() {
        return new Wizard();
    }
}
