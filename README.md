# Lucky Trial

Lucky Trial is a console-based text adventure that puts your three-person squad up against ten randomized waves of enemies. Every victory grants loot-filled chests, new recruits, and ever-increasing pressure to make the right tactical calls.

## Gameplay Overview
- Start each run with three warriors supplied through the `WarriorFactory`.
- Fight ten waves, each containing three enemies created via the enemy factories. Enemies attack according to their strategy (random, aggressive, or defensive).
- On each squad turn, pick a living member and select the enemy to strike. Defeated enemies drop a random chest (basic, rare, or legendary). Defeated waves award a squad chest that can add or replace party members.
- Equip loot immediately on the squad member you choose. Weapons boost outgoing damage while armor increases max health. Decorator effects (Fire, Thorns, Shield) add situational perks.
- Clear all ten waves to win. If the entire squad goes down, the trial restarts from wave one.

## Design Patterns
- **Factory / Abstract Factory** – Separate factories create squad members, enemies, and all chest types.
- **Builder** – `LootBuilder` assembles weapons or armor with randomized stats (1–10 depending on chest tier).
- **Strategy** – Enemy attack behaviors (`RandomAttackStrategy`, `AggressiveAttackStrategy`, `DefensiveAttackStrategy`) and wizard loot drops (`WizardDropStrategy`) are interchangeable strategies.
- **Decorator** – Loot effects (`FireEffect`, `ThornEffect`, `ShieldEffect`) wrap base loot items to layer additional behavior.
- **Observer** – The lightweight `EventBus` notifies registered `GameEventListener`s (e.g., `ConsoleLogger`) about loot drops and enemy defeats.
- **Singleton** – `GameState` centralizes squad composition, inventory tracking, and the current wave number.

## Running the Game
Compile and launch with the JDK (Java 11+ recommended):

```bash
javac $(find src/main/java -name "*.java")
java -cp src/main/java luckytrial.Main
```

java -cp src/main/java luckytrial.Main

Follow the on-screen prompts to target enemies, assign loot, and manage your squad between waves.

## Credits
Joey Tursi, Daniel Hill
