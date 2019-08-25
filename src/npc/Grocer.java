package npc;

import items.food.*;

import java.util.ArrayList;
import java.util.List;

public class Grocer extends Shopkeeper {

    private static Grocer grocer = null;
    private List<Food> foodList = new ArrayList<>();

    private Grocer() {
        Blueberries b = new Blueberries();
        Strawberries s = new Strawberries();
        Bread br = new Bread();
        Stew st = new Stew();
        Turkey t = new Turkey();

        foodList.add(b);
        foodList.add(s);
        foodList.add(br);
        foodList.add(st);
        foodList.add(t);
    }

    public List<Food> getFoodList() { return foodList; }

    public static Grocer getGrocer() {
        if (grocer == null) {
            grocer = new Grocer();
        }
        return grocer;
    }

}
