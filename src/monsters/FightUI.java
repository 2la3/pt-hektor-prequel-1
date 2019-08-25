package monsters;

import protagonist.MC;

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
        fight();
    }

    // REQUIRES :: mc.techniques.size >= 1
    private void fight() {
        MC mc = MC.getMC();
        while (status) {
            System.out.println("* Select your next move *");
            for (int i = 0; i < mc.techniques.size(); i++) {
                System.out.println((i+1) + "." + " " + mc.techniques.get(i).getName());
            }
            int opt = -1;
            while (opt < 0 || opt >= mc.techniques.size()) {
                Scanner sc1 = new Scanner(System.in);
                opt = sc1.nextInt() - 1;
                if (opt < 0 || opt >= mc.techniques.size()) {
                    System.out.println("Invalid option, please select again");
                }
            }
            mc.techniques.get(opt).use();
        }
    }

}
