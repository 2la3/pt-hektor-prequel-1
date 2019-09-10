package tests.ui.locations;

import javafx.application.Application;
import javafx.stage.Stage;
import map.WorldMap;
import map.enumLcn;
import org.junit.jupiter.api.Test;
import ui.Ch0;
import ui.Chapter;
import ui.Main;

public class GardenTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        WorldMap wm = WorldMap.getWorldMap();
        wm.travelTo(enumLcn.MARKET);
        wm.travelTo(enumLcn.T_GROUND);
        wm.travelTo(enumLcn.T_SQUARE);
        wm.travelTo(enumLcn.GARDEN);

        Chapter ch0 = new Ch0(new Main());
        ch0.display();
    }
}
