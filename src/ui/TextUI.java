package ui;

import items.Item;
import items.food.Food;
import items.weapons.Weapon;
import locations.Garden;
import locations.Home;
import map.Map;
import monsters.FightUI;
import monsters.Monster;
import npc.Ambulance;
import npc.Blacksmith;
import npc.Grocer;
import protagonist.Inventory;
import protagonist.MC;

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
        System.out.println("8. Travel");
        System.out.println("9. Character");
        Scanner sc1 = new Scanner(System.in);
        int opt = sc1.nextInt();
        if (opt == 1) Home.rest();
        else if (opt == 2) Home.eatSimpleMeal();
        else if (opt == 3) Home.eatExpensiveMeal();
        else if (opt == 8) travel();
        else if (opt == 9) viewCharInfo();
        else System.out.println("Invalid Selection");
    }

    private void market() {
        System.out.println("Market :: Activities");
        System.out.println("1. Shop at Blacksmith");
        System.out.println("2. Shop at Grocer");
        System.out.println("8. Travel");
        System.out.println("9. Character");
        Scanner sc1 = new Scanner(System.in);
        int opt = sc1.nextInt();
        if (opt == 1) shopBlacksmith();
        else if (opt == 2) shopGrocer();
        else if (opt == 8) travel();
        else if (opt == 9) viewCharInfo();
        else System.out.println("Invalid Selection");
    }

    private void trainingGround() {
        System.out.println("Training Ground :: Activities");
        System.out.println("1. Speak To Master");
        System.out.println("2. Train");
        System.out.println("8. Travel");
        System.out.println("9. Character");
        Scanner sc1 = new Scanner(System.in);
        int opt = sc1.nextInt();
        if (opt == 8) travel();
        else if (opt == 9) viewCharInfo();
        else System.out.println("Invalid Selection");
    }

    private void townSquare() {
        System.out.println("Town Square :: Activities");
        System.out.println("1. Speak to Mayor");
        System.out.println("8. Travel");
        System.out.println("9. Character");
        Scanner sc1 = new Scanner(System.in);
        int opt = sc1.nextInt();
        if (opt == 8) travel();
        else if (opt == 9) viewCharInfo();
        else System.out.println("Invalid Selection");
    }

    private void garden() {
        System.out.println("Garden :: Activities");
        System.out.println("1. Shallow Forest");
        System.out.println("2. Middle Forest");
        System.out.println("3. Deep Forest");
        System.out.println("8. Travel");
        System.out.println("9. Character");
        Scanner sc1 = new Scanner(System.in);
        int opt = sc1.nextInt();
        if (opt == 1 || opt == 2 || opt == 3) {
            Monster m;
            if (opt == 1) m = Garden.shallowForest();
            else if (opt == 2) m = Garden.middleForest();
            else m = Garden.deepForest();
            FightUI fightUI = new FightUI(m);
        }
        else if (opt == 8) travel();
        else if (opt == 9) viewCharInfo();
        else System.out.println("Invalid Selection");
    }

    private void travelTester() {
        List<String> adj = map.getAdjLocations();
        for (int i = 0; i < adj.size(); i++) {
            System.out.println((i + 1) + "." + " " + adj.get(i));
        }
        Scanner sc1 = new Scanner(System.in);
        int opt = sc1.nextInt();
        if (0 < opt && opt <= adj.size()) {
            map.travelTo(adj.get(opt - 1));
        }
        else System.out.println("Invalid Travel map.Location");
    }

    private void travel() {
        List<Map.Vertex> locs = map.getVertex();
        for (int i = 0; i < locs.size(); i++) {
            System.out.println((i + 1) + "." + " " + map.getNameByID(locs.get(i).locationID));
        }
        Scanner sc1 = new Scanner(System.in);
        int opt = sc1.nextInt();
        if (0 < opt && opt <= locs.size()) {
            if(!map.travelToAuto(map.getNameByID(locs.get(opt - 1).locationID))) {
                int cost = Ambulance.serveEnergy(map);
                System.out.println("Oh no! You ran out of energy. Someone called the ambulance to bring you home. The ambulance billed: C" + cost);
            }
        }
        else System.out.println("Invalid Travel map.Location");
    }

    public void shopBlacksmith() {
        List<Weapon> weapons = Blacksmith.getBlacksmith().getWeaponList();
        System.out.println("BLACKSMITH SHOP");
        for (int i = 0; i < weapons.size(); i ++) {
            System.out.println(i + 1 + "." + " " + weapons.get(i).getName() + " " + "C" + weapons.get(i).getCost() +
                    " " + "Level to Equip: " + weapons.get(i).getEquipLevel());
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

    public void shopGrocer() {
        List<Food> food = Grocer.getGrocer().getFoodList();
        System.out.println("GROCERY SHOP");
        for (int i = 0; i < food.size(); i ++) {
            System.out.println(i + 1 + "." + " " + food.get(i).getName() + " " + "C" + food.get(i).getCost());
        }
        Scanner sc1 = new Scanner(System.in);
        int opt = sc1.nextInt();
        if (Grocer.getGrocer().purchaseItem(food.get(opt - 1))) {
            System.out.println("Food Purchased!");
        }
        else {
            System.out.println("Could not purchase food, please check inventory space or money");
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
        System.out.println("ATTACK DMG:" + " " + mc.getAttackTotal());
        System.out.println(" --- INVENTORY --- ");
        System.out.println("SPACE USED:" + " " + mc.getInventory().getCurrentSlots() + "/" + mc.getInventory().getMaxSlots());
        System.out.println("COINS:" + " " + mc.getInventory().getCoins());
        System.out.println("INVENTORY ACTIONS:");
        List<Inventory.ModPair<Food,Integer>> food = mc.getInventory().food;
        List<Inventory.ModPair<Weapon,Integer>> weapon = mc.getInventory().weapon;
        List<Inventory.ModPair<Item, Integer>> other = mc.getInventory().other;
        for (int i = 0; i < food.size(); i++) {
            System.out.println(food.get(i).key.getName() + " -  Quantity: " + food.get(i).value + " " + "Heal:" + " " + food.get(i).key.getHeal()
                    + " " + "TO USE:" + " " + (i + 1));
        }
        int cons = food.size();
        for (int i = cons; i < weapon.size() + cons; i++) {
            System.out.println(weapon.get(i - cons).key.getName() + " -  Quantity: " + weapon.get(i - cons).value + " " + "TO EQUIP:" + " " + (i + 1));
        }
        int itemCons = food.size() + weapon.size();
        for (int i = itemCons; i < other.size() + itemCons; i++) {
            System.out.println(other.get(i - itemCons).key.getName() + " -  Quantity: " + other.get(i - itemCons).value + " " + "TO DROP:" + " " + (i + 1));
        }
        System.out.println("91. Check Equipped Weapon");
        System.out.println("92. Unequip Weapon");
        System.out.println("99. No Action");
        Scanner sc1 = new Scanner(System.in);
        int opt = sc1.nextInt();
        if (opt == 91) {
            Weapon w = mc.getEquipped();
            if (w == null) System.out.println("No weapon equipped");
            else System.out.println("EQUIPPED: " + w.getName() + " " + "DAMAGE: " + w.getDamage());
        }
        else if (opt == 92) {
            if (mc.unequipWeapon()) System.out.println("Weapon Unequipped.");
            else System.out.println("No weapon was equipped or inventory is full");
        }
        else if (opt == 99) return;
        else {
            if (0 < opt && opt <= food.size() + weapon.size() + other.size()) {
                if (opt <= food.size()) {
                    String name = food.get(opt - 1).key.getName();
                    if (mc.useFood(food.get(opt - 1).key)) System.out.println("Used a " + name);
                }
                else if (opt <= weapon.size() + food.size()) {
                    String name = weapon.get(opt - cons - 1).key.getName();
                    if (mc.equipWeapon(weapon.get(opt - cons - 1).key)) System.out.println("Equipped: " + name);
                    else System.out.println("You are not high enough level to equip this weapon." + " You need level: " + weapon.get(opt - cons - 1).key.getEquipLevel()
                    + " " + "You are level: " + MC.getMC().level);
                }
                else {
                    String name = other.get(opt - itemCons - 1).key.getName();
                    if (mc.getInventory().inventoryRemove(food.get(opt - itemCons - 1).key)) System.out.println("Removed " + name + " from inventory");
                }
            }
            else System.out.println("Invalid Option Selected");
        }
    }
}
