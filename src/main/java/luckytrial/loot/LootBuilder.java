package luckytrial.loot;

public class LootBuilder {
    private String name;
    private int statValue;
    private Rarity rarity = Rarity.COMMON;

    public LootBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public LootBuilder setStatValue(int statValue) {
        this.statValue = statValue;
        return this;
    }

    public LootBuilder setRarity(Rarity rarity) {
        this.rarity = rarity;
        return this;
    }

    public Weapon buildWeapon() {
        Weapon weapon = new Weapon(name, statValue, rarity);
        reset();
        return weapon;
    }

    public Armor buildArmor() {
        Armor armor = new Armor(name, statValue, rarity);
        reset();
        return armor;
    }

    private void reset() {
        name = null;
        statValue = 0;
        rarity = Rarity.COMMON;
    }
}
