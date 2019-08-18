package locations;

import npc.Blacksmith;
import npc.Grocer;

public class Market {

    private Blacksmith b;
    private Grocer g;

    public Market() {
        b = new Blacksmith();
        g = new Grocer();
    }

}
