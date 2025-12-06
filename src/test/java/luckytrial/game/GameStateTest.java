package luckytrial.game;

import luckytrial.TestSupport;
import luckytrial.loot.LootBuilder;
import luckytrial.loot.Rarity;
import luckytrial.loot.Weapon;
import luckytrial.squad.Warrior;

public class GameStateTest {
    public static void main(String[] args) {
        GameState state = GameState.getInstance();
        state.reset();
        Warrior first = new Warrior();
        state.addSquadMember(first);
        TestSupport.assertEquals(1, state.getSquad().size(), "Squad add");
        Warrior second = new Warrior();
        state.addSquadMember(second);
        TestSupport.assertEquals(2, state.getSquad().size(), "Second member added");

        Weapon loot = new LootBuilder().setName("Test").setStatValue(1).setRarity(Rarity.COMMON).buildWeapon();
        state.addToInventory(loot);
        TestSupport.assertEquals(1, state.getInventory().size(), "Inventory add");

        state.setWaveNumber(3);
        TestSupport.assertEquals(3, state.getWaveNumber(), "Wave setter");
        state.incrementWave();
        TestSupport.assertEquals(4, state.getWaveNumber(), "Wave increment");

        Warrior replacement = new Warrior();
        state.replaceSquadMember(0, replacement);
        TestSupport.assertEquals(replacement, state.getSquad().get(0), "Replace member");

        replacement.receiveDamage(100, null);
        state.removeFallenMembers();
        TestSupport.assertEquals(1, state.getSquad().size(), "Remove fallen");
        TestSupport.assertTrue(state.isSquadAlive(), "Squad still alive");
        state.reset();
        System.out.println("GameStateTest passed.");
    }
}
