package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import map.Map;
import protagonist.MC;

import java.awt.*;

public class Ch0 extends Chapter {
    private Map worldMap = new Map();
    private MC mc = MC.getMC();

    Stage window;
    Label lblStory;

    public Ch0(Main menu) {
        super(menu);
    }

    @Override
    public void display() {
        window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Prologue");

        Button btnBackToMenu = new Button("Back To Menu");
        btnBackToMenu.setOnAction(e -> backToMenu());
        Button btnBack = new Button("Back");
        btnBack.setOnAction(e -> back());
        Button btnNext = new Button("Next");
        btnNext.setOnAction(e -> next());

        HBox bottomOptions = new HBox();
        bottomOptions.getChildren().addAll(btnBackToMenu, btnBack, btnNext);
        bottomOptions.setAlignment(Pos.CENTER);
        bottomOptions.setSpacing(10);

        Label lblTitle = new Label("Prologue - Setting Off");
        lblTitle.setAlignment(Pos.CENTER);
        Font titleFont = new Font("TimesRoman", 24);
        lblTitle.setFont(titleFont);
        lblStory = new Label("Test 1");
        lblStory.setAlignment(Pos.CENTER);

        BorderPane layout = new BorderPane();
        BorderPane.setAlignment(bottomOptions, Pos.CENTER);
        BorderPane.setMargin(bottomOptions, new Insets(12,12,12,12));
        layout.setBottom(bottomOptions);
        BorderPane.setAlignment(lblTitle, Pos.CENTER);
        BorderPane.setMargin(lblTitle, new Insets(12,12,12,12));
        layout.setTop(lblTitle);
        BorderPane.setAlignment(lblStory, Pos.CENTER);
        BorderPane.setMargin(lblStory, new Insets(12,12,12,12));
        layout.setCenter(lblStory);

        Scene scene = new Scene(layout, 600, 400);
        window.setScene(scene);
        window.show();
    }

    private void next() {

    }

    private void back() {

    }

    public void backToMenu() {
        menu.display();
        window.hide();
        //((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
