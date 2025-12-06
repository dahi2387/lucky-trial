package luckytrial.enemy;

import java.util.List;

import luckytrial.TestSupport;
import luckytrial.squad.Warrior;

public class EnemyTest {
    public static void main(String[] args) {
        Goblin goblin = new Goblin();
        TestSupport.assertEquals("Goblin", goblin.getName(), "Goblin name");
        TestSupport.assertEquals(4, goblin.getMaxHealth(), "Goblin health");
        TestSupport.assertEquals(2, goblin.getDamage(), "Goblin damage");

        Warrior warrior = new Warrior();
        goblin.attack(List.of(warrior));
        TestSupport.assertEquals(8, warrior.getCurrentHealth(), "Goblin attack damage");

        Orc orc = new Orc();
        TestSupport.assertEquals(10, orc.getMaxHealth(), "Orc health");
        TestSupport.assertEquals(5, orc.getDamage(), "Orc damage");

        Dragon dragon = new Dragon();
        TestSupport.assertEquals(20, dragon.getMaxHealth(), "Dragon health");
        TestSupport.assertEquals(10, dragon.getDamage(), "Dragon damage");
        System.out.println("EnemyTest passed.");
    }
}
