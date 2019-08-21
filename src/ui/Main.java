package ui;

import helpers.ReadTxt;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Main extends Application {

    Label lblTitle;
    Button btnStart;

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
        primaryStage.setTitle("Priston Tale - Hektor's Journey");
        lblTitle = new Label("Priston Tale - Hektor's Journey");
        btnStart = new Button("Start");

        BorderPane layout = new BorderPane();
        BorderPane.setAlignment(lblTitle, Pos.CENTER);
        BorderPane.setAlignment(btnStart, Pos.CENTER);
        layout.setTop(lblTitle);
        layout.setCenter(btnStart);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

