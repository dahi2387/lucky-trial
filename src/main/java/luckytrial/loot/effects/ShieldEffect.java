package luckytrial.loot.effects;

import luckytrial.enemy.Enemy;
import luckytrial.loot.Loot;
import luckytrial.loot.LootType;
import luckytrial.squad.SquadMember;

public class ShieldEffect extends EffectDecorator {
    public ShieldEffect(Loot baseLoot) {
        super(baseLoot);
        if (baseLoot.getLootType() != LootType.ARMOR) {
            throw new IllegalArgumentException("Shield effect can only decorate armor.");
        }
    }

    @Override
    public void apply(SquadMember target) {
        target.setArmorEffect(this);
        target.addShieldCharge();
    }

    @Override
    public void apply(Enemy enemy) {
        // Shield only blocks hits, it does not damage attackers directly.
    }
}
