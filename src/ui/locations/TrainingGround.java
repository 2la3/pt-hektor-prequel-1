package ui.locations;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class TrainingGround extends Location {

    public TrainingGround(BorderPane bpMainLayout, Button btnBack, Button btnNext, Label lblStory) {
        super(bpMainLayout, btnBack, btnNext, lblStory);
    }

    @Override
    void loadStoryParts() {

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

        System.out.println("Showing play options - TGround");
    }
}
