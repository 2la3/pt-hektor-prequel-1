package npc;

import protagonist.Inventory;
import protagonist.MC;
import protagonist.techniques.InnerStrength;
import protagonist.techniques.Technique;

import java.util.ArrayList;
import java.util.List;

public class Master {

    private static Master master = new Master();

    private List<Technique> techs = new ArrayList<Technique>();

    private Master() {
        techs.add(new InnerStrength());
    }

    public boolean skillLearn(Technique tech) {
        if (techs.contains(tech) && MC.getMC().level >= tech.getLevel()) {
            MC.getMC().addTechnique(tech);
            techs.remove(tech);
            return true;
        }
        return false;
    }

    public List<Technique> getTechs() {
        return techs;
    }

    public static Master getMaster() {
        return master;
    }
}
