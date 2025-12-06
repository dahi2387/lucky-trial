package luckytrial;

public final class AllTests {
    private AllTests() {
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Running Lucky Trial test suite...");
        luckytrial.events.EventBusTest.main(args);
        luckytrial.loot.LootBuilderTest.main(args);
        luckytrial.loot.ChestRewardTest.main(args);
        luckytrial.loot.effects.LootEffectTest.main(args);
        luckytrial.loot.strategy.DropStrategyTest.main(args);
        luckytrial.loot.chest.ChestTest.main(args);
        luckytrial.squad.SquadMemberTest.main(args);
        luckytrial.squad.factory.SquadFactoryTest.main(args);
        luckytrial.enemy.EnemyTest.main(args);
        luckytrial.enemy.strategy.AttackStrategyTest.main(args);
        luckytrial.enemy.factory.EnemyFactoryTest.main(args);
        luckytrial.game.GameStateTest.main(args);
        luckytrial.game.WaveTest.main(args);
        luckytrial.game.GameTest.main(args);
        luckytrial.MainTest.main(args);
        System.out.println("All tests passed!");
    }
}
