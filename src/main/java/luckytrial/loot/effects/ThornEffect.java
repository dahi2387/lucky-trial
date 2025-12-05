package luckytrial.loot.effects;

import luckytrial.enemy.Enemy;
import luckytrial.loot.Loot;
import luckytrial.loot.LootType;
import luckytrial.squad.SquadMember;

public class ThornEffect extends EffectDecorator {
    private static final int THORN_DAMAGE = 5;

    public ThornEffect(Loot baseLoot) {
        super(baseLoot);
        if (baseLoot.getLootType() != LootType.ARMOR) {
            throw new IllegalArgumentException("Thorn effect can only decorate armor.");
        }
    }

    @Override
    public void apply(SquadMember target) {
        target.setArmorEffect(this);
    }

    @Override
    public void apply(Enemy enemy) {
        if (enemy != null) {
            enemy.receiveDamage(THORN_DAMAGE);
        }
    }
}
