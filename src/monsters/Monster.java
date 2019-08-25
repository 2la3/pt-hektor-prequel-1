package monsters;

import items.Item;

import java.util.List;
import java.util.Observer;

public abstract class Monster implements Observer {

    public int hp;
    int damage;
    int hitChance;
    int coinsDrop;
    int experienceReward;
    List<Item> dropTable;

}
