package luckytrial.loot.effects;

import luckytrial.enemy.Enemy;
import luckytrial.loot.Loot;
import luckytrial.squad.SquadMember;

public abstract class EffectDecorator extends Loot implements Effect {
    protected final Loot baseLoot;

    protected EffectDecorator(Loot baseLoot) {
        super(baseLoot.getName(), baseLoot.getStatValue(), baseLoot.getRarity(), baseLoot.getLootType());
        this.baseLoot = baseLoot;
    }

    @Override
    public void equip(SquadMember member) {
        baseLoot.equip(member);
        apply(member);
    }

    @Override
    public abstract void apply(SquadMember target);

    @Override
    public abstract void apply(Enemy enemy);
}
