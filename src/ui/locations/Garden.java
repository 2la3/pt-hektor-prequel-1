package ui.locations;

import items.Item;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import map.WorldMap;
import map.enumLcn;
import monsters.*;
import npc.Ambulance;
import protagonist.MC;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Garden extends Location {

    public Garden(BorderPane bpMainLayout, Button btnBack, Button btnNext, Label lblStory) {
        super(bpMainLayout, btnBack, btnNext, lblStory);
    }

    @Override
    void loadStoryParts() {
        if (storyParts.isEmpty()) {
            storyParts.add("Garden Part 1");
            storyParts.add("Garden Part 2");
        }
    }

    @Override
    void displayPlayOptions() {
        btnBack.setDisable(true);
        btnNext.setDisable(true);
        storyParts.clear();

        VBox vb = new VBox();
        List<enumLcn> adjLocations = WorldMap.getWorldMap().getAdjLocations();
        List<Button> buttons = new ArrayList<>();
        Button sForest = new Button("Shallow Forest");
        Button mForest = new Button("Middle Forest");
        Button dForest = new Button("Deep Forest");
        sForest.setOnAction(e ->sForest());
        mForest.setOnAction(e ->mForest());
        dForest.setOnAction(e ->dForest());
        for (enumLcn loc : adjLocations) {
            Button b = new Button("Travel to: " + loc.name);
            b.setOnAction(e -> travelTo(loc));
            buttons.add(b);
        }
        vb.getChildren().addAll(sForest,mForest,dForest);
        for (Button b : buttons) {
            vb.getChildren().add(b);
        }
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);
        bpMainLayout.setCenter(vb);

        System.out.println("Showing play options - Garden");
    }

    private void travelTo(enumLcn loc) {
        WorldMap.getWorldMap().travelTo(loc);
        // somehow go to the screen for that location?
    }

    private void fight(Monster m) {
    }

    private void sForest() {
        fight(shallowForest());
    }

    private void mForest() {
        fight(middleForest());
    }

    private void dForest() {
        fight(deepForest());
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
