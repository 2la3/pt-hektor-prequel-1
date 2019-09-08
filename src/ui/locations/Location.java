package ui.locations;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

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
