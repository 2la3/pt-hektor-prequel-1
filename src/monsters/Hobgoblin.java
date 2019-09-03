package monsters;

import protagonist.MC;

import java.util.concurrent.ThreadLocalRandom;

public class Hobgoblin extends Monster {

    public Hobgoblin() {
        name = "Hobgoblin";
        hp = 125;
        damage = 25;
        hitChance = 100;
        coinsDrop = 600;
        experienceReward = 350;
    }

    @Override
    public int hit() {
        int random = ThreadLocalRandom.current().nextInt(0,100);
        int damage = this.damage;
        if (random < 33) damage = 60;
        else if (random > 67) damage = 1;
        damageDealt = damage;
        return damage;
    }

}
