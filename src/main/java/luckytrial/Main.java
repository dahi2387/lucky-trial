package luckytrial;

import luckytrial.events.ConsoleLogger;
import luckytrial.events.EventBus;
import luckytrial.game.Game;

public class Main {
    public static void main(String[] args) {
        EventBus.register(new ConsoleLogger());
        Game game = new Game();
        game.start();
    }
}
