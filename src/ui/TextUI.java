package ui;

import items.food.Food;
import items.weapons.Weapon;
import locations.Home;
import map.Map;
import npc.Blacksmith;
import protagonist.Inventory;
import protagonist.MC;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextUI {

    private Map map = new Map();

    public static void main(String[] args) {
        System.out.println("Welcome To Hektor's Journey");
        TextUI ui = new TextUI();
        ui.menu();
    }

    private void menu() {
        while (true) {
            if (map.getCurrentLocationID() == 0) home();
            if (map.getCurrentLocationID() == 1) market();
            if (map.getCurrentLocationID() == 2) trainingGround();
            if (map.getCurrentLocationID() == 3) townSquare();
            if (map.getCurrentLocationID() == 4) garden();
        }

    }

    private void home() {
        System.out.println("Home :: Activities");
        System.out.println("1. Rest");
        System.out.println("2. Eat Simple Meal");
        System.out.println("3. Eat Expensive Meal");
        System.out.println("4. Travel");
        System.out.println("9. Character");
        Scanner sc1 = new Scanner(System.in);
        int opt = sc1.nextInt();
        if (opt == 1) Home.rest();
        if (opt == 2) Home.eatSimpleMeal();
        if (opt == 3) Home.eatExpensiveMeal();
        if (opt == 4) travel();
        if (opt == 9) viewCharInfo();
    }

    private void market() {
        System.out.println("Market :: Activities");
        System.out.println("1. Shop at Blacksmith");
        System.out.println("2. Shop at Grocer");
        System.out.println("3. Travel");
        Scanner sc1 = new Scanner(System.in);
        int opt = sc1.nextInt();
        if (opt == 1) shopBlacksmith();
        if (opt == 3) travel();
    }

    private void trainingGround() {

    }

    private void townSquare() {

    }

    private void garden() {

    }

    private void travel() {
        List<String> adj = map.getAdjLocations();
        for (int i = 0; i < adj.size(); i++) {
            System.out.println((i + 1) + "." + " " + adj.get(i));
        }
        Scanner sc1 = new Scanner(System.in);
        int opt = sc1.nextInt();
        if (0 < opt && opt <= adj.size()) {
            map.travelTo(adj.get(opt - 1));
        }
        else System.out.println("Invalid Travel Location");
    }

    public void shopBlacksmith() {
        List<Weapon> weapons = Blacksmith.getBlacksmith().getWeaponList();
        System.out.println("BLACKSMITH SHOP");
        for (int i = 0; i < weapons.size(); i ++) {
            System.out.println(i + 1 + "." + " " + weapons.get(i).getName() + " " + "C" + weapons.get(i).getCost());
        }
        Scanner sc1 = new Scanner(System.in);
        int opt = sc1.nextInt();
        if (Blacksmith.getBlacksmith().purchaseItem(weapons.get(opt - 1))) {
            System.out.println("Weapon Purchased!");
        }
        else {
            System.out.println("Could not purchase weapon, please check inventory space or money");
        }
    }

    private void viewCharInfo() {
        MC mc = MC.getMC();
        System.out.println(" --- ATTRIBUTES ---");
        System.out.println("STRENGTH:" + " " + mc.getStrength());
        System.out.println("VITALITY:" + " " + mc.getVitality());
        System.out.println("INTEL:" + " " + mc.getIntel());
        System.out.println(" --- COMBAT --- ");
        System.out.println("ENERGY:" + " " + mc.energy + "/" + mc.getMaxEnergy());
        System.out.println("HEALTH:" + " " + mc.hp + "/" + mc.getMaxHP());
        System.out.println("ATTACK DMG:" + " " + mc.getAttackMax());
        System.out.println(" --- INVENTORY --- ");
        System.out.println("SPACE USED:" + " " + mc.getInventory().getCurrentSlots() + "/" + mc.getInventory().getMaxSlots());
        System.out.println("COINS:" + " " + mc.getInventory().getCoins());
        System.out.println("INVENTORY ACTIONS:");
        List<Inventory.ModPair<Food,Integer>> food = mc.getInventory().food;
        List<Inventory.ModPair<Weapon,Integer>> weapon = mc.getInventory().weapon;
        for (int i = 0; i < food.size(); i++) {
            System.out.println(food.get(i).key.getName() + " -  Quantity: " + food.get(i).value + " " + "Heal:" + " " + food.get(i).key.getHeal()
                    + " " + "TO USE:" + " " + (i + 1));
        }
        int cons = food.size();
        for (int i = cons; i < weapon.size() + cons; i++) {
            System.out.println(weapon.get(i - cons).key.getName() + " -  Quantity: " + weapon.get(i - cons).value + " " + "TO EQUIP:" + " " + (i + 1));
        }
        System.out.println("99. No Action");
        Scanner sc1 = new Scanner(System.in);
        int opt = sc1.nextInt();
        if (opt == 99) return;
        else {
            if (0 < opt && opt <= food.size() + weapon.size()) {
                if (opt < food.size()) {
                    String name = food.get(opt - 1).key.getName();
                    if (mc.useFood(food.get(opt - 1).key)) System.out.println("Used a " + name);
                }
                else {
                    String name = weapon.get(opt - cons - 1).key.getName();
                    mc.equipWeapon(weapon.get(opt - cons - 1).key);
                    System.out.println("Equipped: " + name);
                }
            }
        }
    }
}
