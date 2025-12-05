package luckytrial.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import luckytrial.enemy.Enemy;
import luckytrial.enemy.factory.DragonFactory;
import luckytrial.enemy.factory.EnemyFactory;
import luckytrial.enemy.factory.GoblinFactory;
import luckytrial.enemy.factory.OrcFactory;
import luckytrial.events.EventBus;
import luckytrial.loot.ChestReward;
import luckytrial.loot.Loot;
import luckytrial.loot.chest.BasicChest;
import luckytrial.loot.chest.BasicChestFactory;
import luckytrial.loot.chest.Chest;
import luckytrial.loot.chest.ChestFactory;
import luckytrial.loot.chest.LegendaryChest;
import luckytrial.loot.chest.LegendaryChestFactory;
import luckytrial.loot.chest.RareChest;
import luckytrial.loot.chest.RareChestFactory;
import luckytrial.loot.chest.SquadChestFactory;
import luckytrial.loot.strategy.BasicDropStrategy;
import luckytrial.loot.strategy.DropStrategy;
import luckytrial.loot.strategy.LegendaryDropStrategy;
import luckytrial.loot.strategy.RareDropStrategy;
import luckytrial.loot.strategy.WizardDropStrategy;
import luckytrial.squad.SquadMember;
import luckytrial.squad.Wizard;
import luckytrial.squad.factory.AllRounderFactory;
import luckytrial.squad.factory.GodFactory;
import luckytrial.squad.factory.SquadMemberFactory;
import luckytrial.squad.factory.SniperFactory;
import luckytrial.squad.factory.SuperTankFactory;
import luckytrial.squad.factory.TankFactory;
import luckytrial.squad.factory.WarriorFactory;
import luckytrial.squad.factory.WizardFactory;

public class Game {
    private final GameState state = GameState.getInstance();
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private final List<SquadMemberFactory> squadFactories = List.of(
            new WarriorFactory(),
            new TankFactory(),
            new WizardFactory(),
            new SniperFactory(),
            new AllRounderFactory(),
            new SuperTankFactory(),
            new GodFactory());
    private final List<EnemyFactory> enemyFactories = List.of(
            new GoblinFactory(),
            new OrcFactory(),
            new DragonFactory());
    private final ChestFactory basicChestFactory = new BasicChestFactory();
    private final ChestFactory rareChestFactory = new RareChestFactory();
    private final ChestFactory legendaryChestFactory = new LegendaryChestFactory();
    private final SquadChestFactory squadChestFactory = new SquadChestFactory(squadFactories);

    public void start() {
        System.out.println("=== Lucky Trial ===");
        state.reset();
        initializeSquad();
        for (int waveIndex = 1; waveIndex <= 10; waveIndex++) {
            state.setWaveNumber(waveIndex);
            System.out.println("\n-- Wave " + waveIndex + " --");
            Wave wave = Wave.randomWave(enemyFactories, random);
            runCombat(wave);
            if (!state.isSquadAlive()) {
                System.out.println("Your squad has fallen. Better luck next time.");
                return;
            }
            System.out.println("Wave " + waveIndex + " cleared!");
            handleSquadChest();
            state.incrementWave();
        }
        System.out.println("You survived all 10 waves! The Lucky Trial is complete.");
    }

    private void initializeSquad() {
        SquadMemberFactory warriorFactory = new WarriorFactory();
        for (int i = 0; i < 3; i++) {
            state.addSquadMember(warriorFactory.createMember());
        }
        System.out.println("Three Warriors join your squad.");
    }

    private void runCombat(Wave wave) {
        while (state.isSquadAlive() && !wave.isCleared()) {
            playerTurn(wave);
            if (!state.isSquadAlive() || wave.isCleared()) {
                break;
            }
            enemyTurn(wave);
            state.removeFallenMembers();
        }
    }

    private void playerTurn(Wave wave) {
        for (SquadMember member : state.getSquad()) {
            if (!member.isAlive()) {
                continue;
            }
            Enemy target = selectEnemy(wave);
            if (target == null) {
                return;
            }
            int damage = member.attack(target);
            System.out.println(member.getName() + " hits " + target.getName() + " for " + damage + " damage.");
            if (!target.isAlive()) {
                EventBus.notifyEnemyDefeated(target);
                handleEnemyDefeat(member);
            }
            if (wave.isCleared()) {
                return;
            }
        }
    }

    private void enemyTurn(Wave wave) {
        System.out.println("\nEnemies strike back!");
        for (Enemy enemy : wave.getEnemies()) {
            if (enemy.isAlive()) {
                enemy.attack(state.getSquad());
            }
        }
    }

    private Enemy selectEnemy(Wave wave) {
        List<Enemy> enemies = wave.getEnemies();
        if (aliveEnemies(enemies).isEmpty()) {
            return null;
        }
        while (true) {
            displayStatus(enemies);
            System.out.println("Choose an enemy to attack:");
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
                String status = enemy.isAlive() ? enemy.getCurrentHealth() + " HP" : "DEFEATED";
                System.out.printf("%d) %s - %s%n", i + 1, enemy.getName(), status);
            }
            int choice = readInt("Enemy #", 1, enemies.size());
            Enemy selected = enemies.get(choice - 1);
            if (!selected.isAlive()) {
                System.out.println("That enemy is already down.");
            } else {
                return selected;
            }
        }
    }

    private void displayStatus(List<Enemy> enemies) {
        System.out.println("\nSquad Status:");
        for (int i = 0; i < state.getSquad().size(); i++) {
            SquadMember member = state.getSquad().get(i);
            System.out.printf("%d) %s - %d/%d HP%n", i + 1, member.getName(), member.getCurrentHealth(), member.getMaxHealth());
        }
        System.out.println("Enemies:");
        for (Enemy enemy : enemies) {
            System.out.printf("%s - %d/%d HP%n", enemy.getName(), enemy.getCurrentHealth(), enemy.getMaxHealth());
        }
        System.out.println();
    }

    private void handleEnemyDefeat(SquadMember attacker) {
        Chest chest = rollLootChest();
        if (attacker instanceof Wizard) {
            chest.setDropStrategy(new WizardDropStrategy(baseStrategyForChest(chest)));
        }
        ChestReward reward = chest.open();
        if (reward.hasLoot()) {
            Loot loot = reward.getLoot();
            EventBus.notifyLootDropped(loot);
            state.addToInventory(loot);
            assignLoot(loot);
        }
    }

    private Chest rollLootChest() {
        int roll = random.nextInt(100);
        if (roll < 60) {
            return basicChestFactory.createChest();
        }
        if (roll < 90) {
            return rareChestFactory.createChest();
        }
        return legendaryChestFactory.createChest();
    }

    private DropStrategy baseStrategyForChest(Chest chest) {
        if (chest instanceof LegendaryChest) {
            return new LegendaryDropStrategy();
        }
        if (chest instanceof RareChest) {
            return new RareDropStrategy();
        }
        return new BasicDropStrategy();
    }

    private void assignLoot(Loot loot) {
        System.out.println("Loot found: " + loot.getName() + " +" + loot.getStatValue());
        List<SquadMember> squad = new ArrayList<>(state.getSquad());
        if (squad.isEmpty()) {
            return;
        }
        for (int i = 0; i < squad.size(); i++) {
            SquadMember member = squad.get(i);
            System.out.printf("%d) %s (%d/%d HP)%n", i + 1, member.getName(), member.getCurrentHealth(), member.getMaxHealth());
        }
        int choice = readInt("Equip on member #", 1, squad.size());
        loot.equip(squad.get(choice - 1));
    }

    private void handleSquadChest() {
        Chest chest = squadChestFactory.createChest();
        ChestReward reward = chest.open();
        if (!reward.hasRecruit()) {
            return;
        }
        SquadMember recruit = reward.getRecruit();
        System.out.println("Squad chest offers: " + recruit.getName());
        List<SquadMember> squad = new ArrayList<>(state.getSquad());
        if (squad.size() < 3) {
            state.addSquadMember(recruit);
            System.out.println(recruit.getName() + " joins your squad.");
            return;
        }
        System.out.println("Choose a member to replace (or 0 to skip):");
        for (int i = 0; i < squad.size(); i++) {
            SquadMember member = squad.get(i);
            System.out.printf("%d) %s (%d/%d HP)%n", i + 1, member.getName(), member.getCurrentHealth(), member.getMaxHealth());
        }
        int choice = readInt("Replace #", 0, squad.size());
        if (choice == 0) {
            System.out.println("You keep your current squad.");
        } else {
            state.replaceSquadMember(choice - 1, recruit);
            System.out.println(recruit.getName() + " replaces " + squad.get(choice - 1).getName());
        }
    }

    private List<Enemy> aliveEnemies(List<Enemy> enemies) {
        List<Enemy> alive = new ArrayList<>();
        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                alive.add(enemy);
            }
        }
        return alive;
    }

    private int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt + " [" + min + "-" + max + "]: ");
            String line = scanner.nextLine();
            try {
                int value = Integer.parseInt(line.trim());
                if (value < min || value > max) {
                    System.out.println("Enter a value between " + min + " and " + max + ".");
                } else {
                    return value;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a number.");
            }
        }
    }
}
