package luckytrial.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import luckytrial.loot.Loot;
import luckytrial.squad.SquadMember;

public final class GameState {
    private static final GameState INSTANCE = new GameState();

    private final List<SquadMember> squad = new ArrayList<>();
    private final List<Loot> inventory = new ArrayList<>();
    private int waveNumber = 1;

    private GameState() {
    }

    public static GameState getInstance() {
        return INSTANCE;
    }

    public void reset() {
        squad.clear();
        inventory.clear();
        waveNumber = 1;
    }

    public List<SquadMember> getSquad() {
        return Collections.unmodifiableList(squad);
    }

    public List<Loot> getInventory() {
        return Collections.unmodifiableList(inventory);
    }

    public void addToInventory(Loot loot) {
        if (loot != null) {
            inventory.add(loot);
        }
    }

    public void addSquadMember(SquadMember member) {
        if (member != null && squad.size() < 3) {
            squad.add(member);
        }
    }

    public void removeFallenMembers() {
        squad.removeIf(member -> !member.isAlive());
    }

    public void replaceSquadMember(int index, SquadMember member) {
        if (member == null || index < 0 || index >= squad.size()) {
            return;
        }
        squad.set(index, member);
    }

    public void setWaveNumber(int waveNumber) {
        this.waveNumber = waveNumber;
    }

    public int getWaveNumber() {
        return waveNumber;
    }

    public void incrementWave() {
        waveNumber++;
    }

    public boolean isSquadAlive() {
        for (SquadMember member : squad) {
            if (member.isAlive()) {
                return true;
            }
        }
        return false;
    }
}
