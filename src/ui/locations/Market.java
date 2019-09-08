package ui.locations;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Market extends Location {
    public Market(BorderPane bpMainLayout, Button btnBack, Button btnNext, Label lblStory) {
        super(bpMainLayout, btnBack, btnNext, lblStory);
    }

    @Override
    void loadStoryParts() {

    }

    @Override
    void displayPlayOptions() {

    }
}
