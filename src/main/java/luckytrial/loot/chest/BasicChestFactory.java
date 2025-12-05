package luckytrial.loot.chest;

public class BasicChestFactory implements ChestFactory {
    @Override
    public Chest createChest() {
        return new BasicChest();
    }
}
