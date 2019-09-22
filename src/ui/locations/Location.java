package ui.locations;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import map.WorldMap;
import map.enumLcn;
import ui.Ch0;

import java.util.ArrayList;
import java.util.List;

public abstract class Location {
    protected BorderPane bpMainLayout;
    protected Button btnBack;
    protected Button btnNext;
    protected Label lblStory;
    protected List<String> storyParts;
    private int storyIndex;

    public Location(BorderPane bpMainLayout, Button btnBack, Button btnNext, Label lblStory) {
        this.bpMainLayout = bpMainLayout;
        this.btnBack = btnBack;
        this.btnNext = btnNext;
        this.lblStory = lblStory;
        this.storyParts = new ArrayList<>();
    }

    abstract void loadStoryParts();

    public void display() {
        btnBack.setDisable(false);
        btnNext.setDisable(false);
        btnBack.setOnAction(e -> back());
        btnNext.setOnAction(e -> next());

        loadStoryParts();

        if (!storyParts.isEmpty()) {
            storyIndex = 0;
            displayStoryPart();
        }
        else displayPlayOptions();
    }

    private void travelTo(enumLcn loc) {
        WorldMap.getWorldMap().travelTo(loc);
        // somehow go to the screen for that location?
        if (loc == enumLcn.HOME) Ch0.getCh0().getHome().display();
        if (loc == enumLcn.MARKET) Ch0.getCh0().getMarket().display();
        if (loc == enumLcn.T_GROUND) Ch0.getCh0().gettGround().display();
        if (loc == enumLcn.T_SQUARE) Ch0.getCh0().gettSquare().display();
        if (loc == enumLcn.GARDEN) Ch0.getCh0().getGarden().display();
    }

    List<Button> getTravelOptions() {
        List<enumLcn> adjLocations = WorldMap.getWorldMap().getAdjLocations();
        ArrayList<Button> buttons = new ArrayList<Button>();
        for (enumLcn loc : adjLocations) {
            Button b = new Button("Travel to: " + loc.name);
            b.setOnAction(e -> travelTo(loc));
            buttons.add(b);
        }
        return buttons;
    }

    private void displayStoryPart() {
        lblStory.setText(storyParts.get(storyIndex));
    }

    abstract void displayPlayOptions();

    private void back() {
        if (storyIndex - 1 >= 0) {
            storyIndex--;
            displayStoryPart();
        }
    }

    private void next() {
        if (storyIndex + 1 < storyParts.size()) {
            storyIndex++;
            displayStoryPart();
        }
        else {
            displayPlayOptions();
        }
    }
}