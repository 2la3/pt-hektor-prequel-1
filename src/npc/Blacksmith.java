package npc;

import items.weapons.*;

import java.util.ArrayList;
import java.util.List;

public class Blacksmith extends Shopkeeper {

    private static Blacksmith blacksmith = null;
    private List<Weapon> weaponList = new ArrayList<>();

    private Blacksmith() {
        StoneAxe sa = new StoneAxe();
        BattleAxe ba = new BattleAxe();
        GreatAxe ga = new GreatAxe();
        RelicAxe ra = new RelicAxe();
        LegendAxe la = new LegendAxe();
        VerusAxe va = new VerusAxe();

        weaponList.add(sa);
        weaponList.add(ba);
        weaponList.add(ga);
        weaponList.add(ra);
        weaponList.add(la);
        weaponList.add(va);
    }

    public List<Weapon> getWeaponList() { return weaponList; }

    public static Blacksmith getBlacksmith() {
        if (blacksmith == null) {
            blacksmith = new Blacksmith();
        }
        return blacksmith;
    }

}
