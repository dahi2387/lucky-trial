package luckytrial.loot.strategy;

import java.util.Random;

import luckytrial.loot.Loot;
import luckytrial.loot.LootBuilder;
import luckytrial.loot.Rarity;

public class BasicDropStrategy implements DropStrategy {
    private static final String[] WEAPON_NAMES = {"Rusty Dagger", "Training Sword", "Wooden Staff"};
    private static final String[] ARMOR_NAMES = {"Cloth Tunic", "Padded Armor", "Leather Wraps"};
    private final Random random = new Random();

    @Override
    public Loot generateLoot() {
        int stat = random.nextInt(3) + 1;
        LootBuilder builder = new LootBuilder()
                .setStatValue(stat)
                .setRarity(Rarity.COMMON);
        if (random.nextBoolean()) {
            return builder.setName(WEAPON_NAMES[random.nextInt(WEAPON_NAMES.length)]).buildWeapon();
        }
        return builder.setName(ARMOR_NAMES[random.nextInt(ARMOR_NAMES.length)]).buildArmor();
    }
}
