package monsters;

import items.Item;
import npc.Ambulance;
import protagonist.MC;

import java.util.List;
import java.util.Observable;
import java.util.Scanner;

public class FightUI extends Observable {

    public boolean status;
    private Monster monster;

    public FightUI(Monster monster) {
        addObserver(MC.getMC());
        addObserver(monster);
        if (monster.hp > 0 && MC.getMC().hp > 0) {
            status = true;
        }
        else status = false;
        this.monster = monster;
        System.out.println("You are fighting" + " " + monster.getName());
        fight();
    }

    // REQUIRES :: mc.techniques.size >= 1
    private void fight() {
        MC mc = MC.getMC();
        while (status) {
            setChanged();
            notifyObservers(true);
            System.out.println("* Select your next move *");
            for (int i = 0; i < mc.techniques.size(); i++) {
                System.out.println((i+1) + "." + " " + mc.techniques.get(i).getName() + " " + "Mana Cost: " + mc.techniques.get(i).getMana());
            }
            int opt = -1;
            while (opt < 0 || opt >= mc.techniques.size()) {
                Scanner sc1 = new Scanner(System.in);
                opt = sc1.nextInt() - 1;
                if (opt < 0 || opt >= mc.techniques.size()) {
                    System.out.println("Invalid option, please select again");
                }
            }
            mc.techniques.get(opt).use(monster);
            System.out.println("You did " + monster.getDamageDoneTo() + " damage. " + "You have " + mc.mp + " mana remaining.");
            System.out.println("The " + monster.getName() + " has " + monster.hp + " health remaining.");
            if (monster.getAlive()) {
                if (!mc.hpAdjustment(-monster.hit())) {
                    System.out.println(monster.getName() + " did " + monster.getDamageDealt() + " damage to you.");
                    System.out.println("You lost the fight to the " + monster.getName() + "!");
                    System.out.println("A passerby found you on the side of a path and called the ambulance");
                    System.out.println("You were treated and taken home and charged C" + Ambulance.serveHPCost() + " for the service.");
                    status = false;
                }
                else {
                    System.out.println(monster.getName() + " did " + monster.getDamageDealt() + " damage to you.");
                    System.out.println("You have " + mc.hp + " health remaining");
                }
            }
            else {
                System.out.println("You have defeated " + monster.getName());
                List<Item> loot = monster.getLoot();
                if (loot.size() > 0) {
                    System.out.println("Please select the items you would like to pick up.");
                    while (loot.size() > 0) {
                        for (int i = 0; i < loot.size(); i++) {
                            System.out.println((i + 1) + "." + " " + loot.get(i).getName());
                        }
                        System.out.println("99. Done Looting");
                        Scanner sc1 = new Scanner(System.in);
                        int lootOpt = sc1.nextInt();
                        if (0 < lootOpt && lootOpt <= loot.size()) {
                            if (mc.getInventory().inventoryAdd(loot.get(lootOpt - 1))) {
                                System.out.println(loot.get((lootOpt-1)) + " " + "added to inventory.");
                            }
                            else System.out.println("Not enough space to loot this item");
                        }
                        else if (lootOpt == 99) break;
                        else System.out.println("Invalid Option");
                    }
                }
                status = false;
            }
        }
        setChanged();
        notifyObservers(false);
    }

}
