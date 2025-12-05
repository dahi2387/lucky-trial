package luckytrial.loot.strategy;

import java.util.Random;

import luckytrial.loot.Loot;
import luckytrial.loot.LootBuilder;
import luckytrial.loot.Rarity;
import luckytrial.loot.Weapon;
import luckytrial.loot.effects.FireEffect;
import luckytrial.loot.effects.ShieldEffect;
import luckytrial.loot.effects.ThornEffect;

public class LegendaryDropStrategy implements DropStrategy {
    private static final String[] WEAPON_NAMES = {"Dragon Slayer", "Void Scepter", "Storm Reaver"};
    private static final String[] ARMOR_NAMES = {"Titan Plate", "Phoenix Cloak", "Aegis Shell"};
    private final Random random = new Random();

    @Override
    public Loot generateLoot() {
        int stat = random.nextInt(2) + 9; // 9-10
        LootBuilder builder = new LootBuilder()
                .setStatValue(stat)
                .setRarity(Rarity.LEGENDARY);
        Loot loot;
        if (random.nextBoolean()) {
            loot = builder.setName(WEAPON_NAMES[random.nextInt(WEAPON_NAMES.length)]).buildWeapon();
        } else {
            loot = builder.setName(ARMOR_NAMES[random.nextInt(ARMOR_NAMES.length)]).buildArmor();
        }
        return decorate(loot);
    }

    private Loot decorate(Loot loot) {
        if (loot instanceof Weapon) {
            return new FireEffect(loot);
        }
        if (random.nextBoolean()) {
            return new ThornEffect(loot);
        }
        return new ShieldEffect(loot);
    }
}
