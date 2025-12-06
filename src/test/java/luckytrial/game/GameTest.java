package luckytrial.game;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import luckytrial.TestSupport;
import luckytrial.loot.Loot;
import luckytrial.loot.LootBuilder;
import luckytrial.loot.Rarity;
import luckytrial.squad.SquadMember;
import luckytrial.squad.Warrior;

public class GameTest {
    public static void main(String[] args) throws Exception {
        GameState state = GameState.getInstance();
        state.reset();

        Game initializer = new Game();
        invokePrivate(initializer, "initializeSquad");
        TestSupport.assertEquals(3, state.getSquad().size(), "Initialize squad creates three members");

        Game lootGame = new Game();
        setScanner(lootGame, "1\n");
        Loot weapon = new LootBuilder().setName("Test Sword").setStatValue(3).setRarity(Rarity.RARE).buildWeapon();
        invokePrivate(lootGame, "assignLoot", weapon);
        SquadMember first = state.getSquad().get(0);
        TestSupport.assertNotNull(first.getWeapon(), "Assign loot equips weapon");

        state.reset();
        state.addSquadMember(new Warrior());
        state.addSquadMember(new Warrior());
        Game squadGame = new Game();
        invokePrivate(squadGame, "handleSquadChest");
        TestSupport.assertEquals(3, state.getSquad().size(), "Squad chest fills empty slot");

        System.out.println("GameTest passed.");
    }

    private static void invokePrivate(Game game, String methodName, Object... args) throws Exception {
        Method target = null;
        outer:
        for (Method method : Game.class.getDeclaredMethods()) {
            if (!method.getName().equals(methodName) || method.getParameterCount() != args.length) {
                continue;
            }
            Class<?>[] params = method.getParameterTypes();
            for (int i = 0; i < params.length; i++) {
                if (!params[i].isAssignableFrom(args[i].getClass())) {
                    continue outer;
                }
            }
            target = method;
            break;
        }
        if (target == null) {
            throw new NoSuchMethodException("Method " + methodName);
        }
        target.setAccessible(true);
        target.invoke(game, args);
    }

    private static void setScanner(Game game, String data) throws Exception {
        Field field = Game.class.getDeclaredField("scanner");
        field.setAccessible(true);
        field.set(game, new Scanner(new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8))));
    }
}
