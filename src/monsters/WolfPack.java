package monsters;

import java.util.concurrent.ThreadLocalRandom;

public class WolfPack extends Monster {

    public WolfPack() {
        name = "Wolf Pack";
        hp = 500;
        damage = 50;
        hitChance = 100;
        coinsDrop = 1000;
        experienceReward = 790;
    }

    @Override
    public int hit() {
        int random = ThreadLocalRandom.current().nextInt(0,100);
        int damage = this.damage;
        if (random < 33) damage = this.damage * 3;
        if (random > 66) hp = hp + 100;
        damageDealt = damage;
        return damage;
    }

}
