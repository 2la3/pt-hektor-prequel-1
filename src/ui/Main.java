package ui;

import helpers.ReadTxt;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import map.Map;

import java.util.ArrayList;
import java.util.List;

import static javafx.application.Application.launch;

public class Main extends Application {
    private Stage pStage;

    private Label lblTitle;
    private Button btnStart;
    private List<Chapter> chapters = new ArrayList<Chapter>();

    public static void main(String[] args) {
//        // Test readTxt
//        String fileName = "src//story.txt";
//
//        ReadTxt readTxt = new ReadTxt(fileName);
//        readTxt.printLines();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        primaryStage.setTitle("Priston Tale - Hektor's Journey");
//        lblTitle = new Label("Priston Tale - Hektor's Journey");
//        btnStart = new Button("Start");
//
//        btnStart.setOnAction(e -> startCh0());
//
//        BorderPane layout = new BorderPane();
//        BorderPane.setAlignment(lblTitle, Pos.CENTER);
//        BorderPane.setAlignment(btnStart, Pos.CENTER);
//        layout.setTop(lblTitle);
//        layout.setCenter(btnStart);
//
//        Scene scene = new Scene(layout, 600, 400);
//        primaryStage.setScene(scene);
//        primaryStage.show();

        pStage = primaryStage;
        display();

        chapters.add(new Ch0(this));
    }

    public void startCh0() {
        Chapter ch0 = chapters.get(0);
        ch0.display();

        pStage.hide();

    }

    public void display() {
        pStage = new Stage();
        pStage.setTitle("Priston Tale - Hektor's Journey");
        lblTitle = new Label("Priston Tale - Hektor's Journey");
        btnStart = new Button("Start");

        btnStart.setOnAction(e -> startCh0());

        BorderPane layout = new BorderPane();
        BorderPane.setAlignment(lblTitle, Pos.CENTER);
        BorderPane.setAlignment(btnStart, Pos.CENTER);
        layout.setTop(lblTitle);
        layout.setCenter(btnStart);

        Scene scene = new Scene(layout, 600, 400);
        pStage.setScene(scene);
        pStage.show();
    }


}

