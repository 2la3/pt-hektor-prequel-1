package ui.locations;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import protagonist.MC;

import java.util.ArrayList;
import java.util.List;

public class Home extends Location {

    public Home(BorderPane bpMainLayout, Button btnBack, Button btnNext, Label lblStory) {
        super(bpMainLayout, btnBack, btnNext, lblStory);
    }

    // REQUIRES :: location on Map is at home; i.e. location id == 0.
    // MODIFIES :: MC
    // EFFECTS  :: restores energy to 100
    public static void rest() {
        MC mc = MC.getMC();
        mc.energy = 100;
    }

    // REQUIRES :: location on Map is at home; i.e. location id == 0.
    // MODIFIES :: MC
    // EFFECTS  :: restores hp to 1/3 of max
    public static void eatSimpleMeal() {
        MC mc = MC.getMC();
        mc.hp = (mc.getMaxHP()/3);
    }

    // MODIFIES :: MC, MC.inv
    // EFFECTS  :: if funds are available, make the appropriate adjustment, restore hp to max and return true; otherwise return false
    public static boolean eatExpensiveMeal() {
        MC mc = MC.getMC();
        if (mc.getInventory().coinsModifier(-100)) {
            mc.hp = mc.getMaxHP();
            return true;
        }
        return false;
    }

    public static void ambulanceRest() {
        MC.getMC().energy = 20;
        MC.getMC().hp = MC.getMC().getMaxHP()/5;
    }

    @Override
    void loadStoryParts() {
        if (storyParts.isEmpty()) {
            storyParts.add("Home Part 1");
            storyParts.add("Home Part 2");
        }
    }

    @Override
    void displayPlayOptions() {
        btnBack.setDisable(true);
        btnNext.setDisable(true);
        storyParts.clear();

        VBox vb = new VBox();
        List<Button> buttons = new ArrayList<>();
        buttons = getTravelOptions();
        for (Button b : buttons) {
            vb.getChildren().add(b);
        }
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);
        bpMainLayout.setCenter(vb);

        System.out.println("Showing play options - HOME");
    }
}

