package luckytrial.loot.strategy;

import java.util.Random;

import luckytrial.loot.Loot;
import luckytrial.loot.LootBuilder;
import luckytrial.loot.Rarity;
import luckytrial.loot.Weapon;
import luckytrial.loot.effects.FireEffect;
import luckytrial.loot.effects.ShieldEffect;
import luckytrial.loot.effects.ThornEffect;

public class RareDropStrategy implements DropStrategy {
    private static final String[] WEAPON_NAMES = {"Steel Blade", "Hunter Bow", "Arcane Wand"};
    private static final String[] ARMOR_NAMES = {"Chain Vest", "Hunter Cloak", "Mystic Robe"};
    private final Random random = new Random();

    @Override
    public Loot generateLoot() {
        int stat = random.nextInt(5) + 4; // 4-8
        LootBuilder builder = new LootBuilder()
                .setStatValue(stat)
                .setRarity(Rarity.RARE);
        Loot loot;
        if (random.nextBoolean()) {
            loot = builder.setName(WEAPON_NAMES[random.nextInt(WEAPON_NAMES.length)]).buildWeapon();
        } else {
            loot = builder.setName(ARMOR_NAMES[random.nextInt(ARMOR_NAMES.length)]).buildArmor();
        }
        if (random.nextDouble() < 0.4) {
            loot = decorate(loot);
        }
        return loot;
    }

    private Loot decorate(Loot loot) {
        if (loot instanceof Weapon) {
            return new FireEffect(loot);
        }
        return random.nextBoolean() ? new ThornEffect(loot) : new ShieldEffect(loot);
    }
}
