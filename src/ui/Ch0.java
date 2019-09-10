package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import map.enumLcn;
import map.WorldMap;
import protagonist.MC;

import ui.locations.*;

public class Ch0 extends Chapter {
    private WorldMap wm = WorldMap.getWorldMap();
    //private MC mc = MC.getMC();

    Stage window;
    Button btnBackToMenu;
    Button btnBack;
    Button btnNext;
    Label lblTitle;
    Label lblStory;
    HBox hbBottomOptions;
    BorderPane bpMainLayout;
    Scene scene;

    Home home;
    Garden garden;
    Market market;
    TownSquare tSquare;
    TrainingGround tGround;

    public Ch0(Main menu) {
        super(menu);
        window = new Stage();
        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Prologue");

        btnBackToMenu = new Button("Back To Menu");
        btnBackToMenu.setOnAction(e -> backToMenu());

        btnBack = new Button("Back");
        btnNext = new Button("Next");

        lblTitle = new Label("Prologue - Setting Off");
        lblTitle.setAlignment(Pos.CENTER);
        Font titleFont = new Font("TimesRoman", 24);
        lblTitle.setFont(titleFont);

        lblStory = new Label();
        lblStory.setAlignment(Pos.CENTER);

        hbBottomOptions = new HBox();
        hbBottomOptions.getChildren().addAll(btnBackToMenu, btnBack, btnNext);
        hbBottomOptions.setAlignment(Pos.CENTER);
        hbBottomOptions.setSpacing(10);

        bpMainLayout = new BorderPane();
        BorderPane.setAlignment(hbBottomOptions, Pos.CENTER);
        BorderPane.setMargin(hbBottomOptions, new Insets(12,12,12,12));
        bpMainLayout.setBottom(hbBottomOptions);
        BorderPane.setAlignment(lblTitle, Pos.CENTER);
        BorderPane.setMargin(lblTitle, new Insets(12,12,12,12));
        bpMainLayout.setTop(lblTitle);
        BorderPane.setAlignment(lblStory, Pos.CENTER);
        BorderPane.setMargin(lblStory, new Insets(12,12,12,12));
        bpMainLayout.setCenter(lblStory);

        scene = new Scene(bpMainLayout, 600, 400);
        window.setScene(scene);

        home = new Home(bpMainLayout, btnBack, btnNext, lblStory);
        garden = new Garden(bpMainLayout, btnBack, btnNext, lblStory);
        market = new Market(bpMainLayout, btnBack, btnNext, lblStory);
        tGround = new TrainingGround(bpMainLayout, btnBack, btnNext, lblStory);
        tSquare = new TownSquare(bpMainLayout, btnBack, btnNext, lblStory);
    }

    @Override
    public void display() {
        window.show();
        /* CHANGED THIS */
        wm.setLocation(enumLcn.GARDEN);
        /* END CHANGES */
        enumLcn currL = wm.getCurrL();
        displayCurrL(currL);
    }

    private void displayCurrL(enumLcn currL) {
        if (currL.equals(enumLcn.HOME)) {
            home.display();
        }
        else if (currL.equals(enumLcn.GARDEN)) {
            garden.display();
        }
        else if (currL.equals(enumLcn.MARKET)) {
            market.display();
        }
        else if (currL.equals(enumLcn.T_GROUND)) {
            tGround.display();
        }
        else if (currL.equals(enumLcn.T_SQUARE)) {
            tSquare.display();
        }
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
