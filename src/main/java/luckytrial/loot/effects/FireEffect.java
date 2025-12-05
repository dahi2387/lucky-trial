package luckytrial.loot.effects;

import luckytrial.enemy.Enemy;
import luckytrial.loot.Loot;
import luckytrial.loot.LootType;
import luckytrial.squad.SquadMember;

public class FireEffect extends EffectDecorator {
    private static final int FIRE_DAMAGE = 5;

    public FireEffect(Loot baseLoot) {
        super(baseLoot);
        if (baseLoot.getLootType() != LootType.WEAPON) {
            throw new IllegalArgumentException("Fire effect can only decorate weapons.");
        }
    }

    @Override
    public void apply(SquadMember target) {
        target.setWeaponEffect(this);
    }

    @Override
    public void apply(Enemy enemy) {
        if (enemy != null) {
            enemy.receiveDamage(FIRE_DAMAGE);
        }
    }
}
