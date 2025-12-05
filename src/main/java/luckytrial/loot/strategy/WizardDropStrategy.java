package luckytrial.loot.strategy;

import luckytrial.loot.Loot;
import luckytrial.loot.Rarity;

public class WizardDropStrategy implements DropStrategy {
    private final DropStrategy delegate;

    public WizardDropStrategy(DropStrategy delegate) {
        this.delegate = delegate;
    }

    @Override
    public Loot generateLoot() {
        Loot first = delegate.generateLoot();
        Loot second = delegate.generateLoot();
        return isBetter(second, first) ? second : first;
    }

    private boolean isBetter(Loot candidate, Loot current) {
        if (candidate == null) {
            return false;
        }
        if (current == null) {
            return true;
        }
        Rarity candidateRarity = candidate.getRarity();
        Rarity currentRarity = current.getRarity();
        if (candidateRarity != currentRarity) {
            return candidateRarity.ordinal() > currentRarity.ordinal();
        }
        return candidate.getStatValue() >= current.getStatValue();
    }
}
