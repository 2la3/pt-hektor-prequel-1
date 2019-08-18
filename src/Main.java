import protagonist.MC;

public class Main {
    public static void main(String[] args) {
        MC hektor = MC.getMC(50, 10, 20);
        System.out.println(hektor.hp);
        System.out.println(hektor.mp);
        System.out.println(hektor.energy);
        System.out.println(hektor.exp);
    }
}

