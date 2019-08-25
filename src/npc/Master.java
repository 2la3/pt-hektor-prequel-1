package npc;

public class Master {

    private static Master master = new Master();

    private Master() {
    }


    public static Master getMaster() {
        return master;
    }
}
