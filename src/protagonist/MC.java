package protagonist;

public class MC {
    private static final int STRENGTH = 50;
    private static final int INTEL = 10;
    private static final int VITALITY = 20;

    private static MC mc = null;

    private int strength;
    private int intel;
    private int vitality;

    private int maxHP;
    private int maxMP;
    private int maxEnergy;
    private int maxExp;

    private int attackMin;
    private int attackMax;
    private int defenseMin;
    private int defenseMax;
    private int attackRate;
    private int defenseRate;

    public int hp;
    public int mp;
    public int energy;
    public int exp;



    private MC() {
        this.strength = STRENGTH;
        this.intel = INTEL;
        this.vitality = VITALITY;

        this.maxHP = 5 * this.vitality + 1 * this.strength;
        this.maxMP = 5 * this.intel;
        this.maxEnergy = 100;
        this.maxExp = 1000;

        this.attackMin = 5 * this.strength;
        this.attackMax = 7 * this.strength;
        this.defenseMin = 2 * this.strength + this.vitality;
        this.defenseMax = 4 * this.strength + 2* this.vitality;
        this.attackRate = 150 * this.intel;
        this.defenseRate = 100 * this.intel;

        this.hp = this.maxHP;
        this.mp = this.maxMP;
        this.energy = this.maxEnergy;
        this.exp = 0;


    }

    public static MC getMC() {
        if (mc == null)
            mc = new MC();
        return mc;
    }
}
