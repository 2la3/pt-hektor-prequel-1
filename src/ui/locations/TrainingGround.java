package ui.locations;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class TrainingGround extends Location {

    public TrainingGround(BorderPane bpMainLayout, Button btnBack, Button btnNext, Label lblStory) {
        super(bpMainLayout, btnBack, btnNext, lblStory);
    }

    @Override
    void loadStoryParts() {

    }

    @Override
    void displayPlayOptions() {

    }
}
