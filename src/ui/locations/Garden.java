package ui.locations;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import monsters.*;

import java.util.Random;

public class Garden extends Location {

    public Garden(BorderPane bpMainLayout, Button btnBack, Button btnNext, Label lblStory) {
        super(bpMainLayout, btnBack, btnNext, lblStory);
    }

    @Override
    void loadStoryParts() {

    }

    @Override
    void displayPlayOptions() {

    }

    public static Monster shallowForest() {
        Random rand = new Random();
        int random = rand.nextInt(4);
        System.out.println(random);
        if (random == 1)  {
            return new Rabbit();
        }
        else if (random == 2) {
            return new Squirrel();
        }
        else {
            return new Beaver();
        }
    }

    public static Monster middleForest() {
        Random rand = new Random();
        int random = rand.nextInt(4);
        if (random == 1) {
            return new WaspNest();
        }
        if (random == 2) {
            return new Hobgoblin();
        }
        else return new EvilTreeSpirit();
    }

    public static Monster deepForest() {
        Random rand = new Random();
        int random = rand.nextInt(4);
        if (random == 1) {
            return new Hopy();
        }
        if (random == 2) {
            return new WolfPack();
        }
        else return new BabyDragon();
    }
}
