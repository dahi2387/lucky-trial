package luckytrial.loot.chest;

public class RareChestFactory implements ChestFactory {
    @Override
    public Chest createChest() {
        return new RareChest();
    }
}
