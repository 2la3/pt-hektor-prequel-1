package monsters;

import protagonist.MC;

import java.util.concurrent.ThreadLocalRandom;

public class EvilTreeSpirit extends Monster {

    public EvilTreeSpirit() {
        name = "Evil Tree Spirit";
        hp = 100;
        damage = 5;
        hitChance = 100;
        coinsDrop = 750;
        experienceReward = 400;
    }

    @Override
    public int hit() {
        int random = ThreadLocalRandom.current().nextInt(0,100);
        int damage = this.damage;
        if (random > 75) damage = 50 + MC.getMC().energy;
        damageDealt = damage;
        return damage;
    }


}
