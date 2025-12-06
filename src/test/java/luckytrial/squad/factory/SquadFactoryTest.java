package luckytrial.squad.factory;

import luckytrial.TestSupport;
import luckytrial.squad.AllRounder;
import luckytrial.squad.God;
import luckytrial.squad.Sniper;
import luckytrial.squad.SquadMember;
import luckytrial.squad.SuperTank;
import luckytrial.squad.Tank;
import luckytrial.squad.Warrior;
import luckytrial.squad.Wizard;

public class SquadFactoryTest {
    public static void main(String[] args) {
        assertFactory(new WarriorFactory(), Warrior.class);
        assertFactory(new TankFactory(), Tank.class);
        assertFactory(new WizardFactory(), Wizard.class);
        assertFactory(new SniperFactory(), Sniper.class);
        assertFactory(new AllRounderFactory(), AllRounder.class);
        assertFactory(new SuperTankFactory(), SuperTank.class);
        assertFactory(new GodFactory(), God.class);
        System.out.println("SquadFactoryTest passed.");
    }

    private static void assertFactory(SquadMemberFactory factory, Class<? extends SquadMember> expected) {
        SquadMember member = factory.createMember();
        TestSupport.assertTrue(expected.isInstance(member), "Factory should create " + expected.getSimpleName());
    }
}
