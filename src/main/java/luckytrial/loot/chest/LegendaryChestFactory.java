package luckytrial.loot.chest;

public class LegendaryChestFactory implements ChestFactory {
    @Override
    public Chest createChest() {
        return new LegendaryChest();
    }
}
