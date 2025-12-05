package luckytrial.enemy;

import java.util.List;

import luckytrial.enemy.strategy.AttackStrategy;
import luckytrial.squad.SquadMember;

public abstract class Enemy {
    private final String name;
    private final int maxHealth;
    private int currentHealth;
    private final int damage;
    private AttackStrategy strategy;

    protected Enemy(String name, int health, int damage) {
        this.name = name;
        this.maxHealth = health;
        this.currentHealth = health;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isAlive() {
        return currentHealth > 0;
    }

    public void attack(List<SquadMember> squad) {
        if (isAlive() && strategy != null) {
            strategy.attack(squad);
        }
    }

    public void receiveDamage(int amount) {
        if (amount <= 0 || !isAlive()) {
            return;
        }
        currentHealth -= amount;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
    }

    protected void setStrategy(AttackStrategy strategy) {
        this.strategy = strategy;
    }
}
