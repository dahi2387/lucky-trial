package luckytrial;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.Scanner;

import luckytrial.game.Game;

public class MainTest {
    public static void main(String[] args) throws Exception {
        TestSupport.assertNotNull(new Main(), "Main should be instantiable");
        Game game = new Game();
        Field scannerField = Game.class.getDeclaredField("scanner");
        scannerField.setAccessible(true);
        scannerField.set(game, new Scanner(new ByteArrayInputStream("1\n".getBytes())));
        TestSupport.assertNotNull(game, "Game should construct");
        System.out.println("MainTest passed.");
    }
}
