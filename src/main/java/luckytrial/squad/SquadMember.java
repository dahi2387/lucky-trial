package luckytrial.squad;

import luckytrial.enemy.Enemy;
import luckytrial.loot.Armor;
import luckytrial.loot.Weapon;
import luckytrial.loot.effects.Effect;

public abstract class SquadMember {
    private final String name;
    private final int baseHealth;
    private final int baseDamage;
    private int currentHealth;
    private int armorBonus;
    private Weapon weapon;
    private Armor armor;
    private Effect weaponEffect;
    private Effect armorEffect;
    private int shieldCharges;

    protected SquadMember(String name, int health, int damage) {
        this.name = name;
        this.baseHealth = health;
        this.baseDamage = damage;
        this.currentHealth = health;
    }

    public String getName() {
        return name;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return baseHealth + armorBonus;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public boolean isAlive() {
        return currentHealth > 0;
    }

    public int attack(Enemy enemy) {
        if (!isAlive() || enemy == null || !enemy.isAlive()) {
            return 0;
        }
        int totalDamage = baseDamage;
        if (weapon != null) {
            totalDamage += weapon.getStatValue();
        }
        enemy.receiveDamage(totalDamage);
        if (weaponEffect != null) {
            weaponEffect.apply(enemy);
        }
        return totalDamage;
    }

    public void receiveDamage(int amount, Enemy attacker) {
        if (!isAlive() || amount <= 0) {
            return;
        }
        if (shieldCharges > 0) {
            shieldCharges--;
            System.out.println(name + "'s shield blocks the hit!");
            return;
        }
        boolean wasAlive = isAlive();
        currentHealth -= amount;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
        if (wasAlive && !isAlive()) {
            System.out.println(name + " has fallen!");
        }
        if (armorEffect != null && attacker != null) {
            armorEffect.apply(attacker);
        }
    }

    public void healToFull() {
        currentHealth = getMaxHealth();
    }

    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
        this.weaponEffect = null;
        if (weapon != null) {
            System.out.println(name + " equips weapon: " + weapon.getName());
        }
    }

    public void equipArmor(Armor armor) {
        if (this.armor != null) {
            armorBonus -= this.armor.getStatValue();
            if (armorBonus < 0) {
                armorBonus = 0;
            }
            if (currentHealth > getMaxHealth()) {
                currentHealth = getMaxHealth();
            }
        }
        this.armor = armor;
        this.armorEffect = null;
        shieldCharges = 0;
        if (armor != null) {
            armorBonus += armor.getStatValue();
            currentHealth = Math.min(getMaxHealth(), currentHealth + armor.getStatValue());
            System.out.println(name + " equips armor: " + armor.getName());
        }
    }

    public void setWeaponEffect(Effect effect) {
        this.weaponEffect = effect;
    }

    public void setArmorEffect(Effect effect) {
        this.armorEffect = effect;
    }

    public void addShieldCharge() {
        shieldCharges++;
    }

    public void revive() {
        currentHealth = getMaxHealth();
    }
}
