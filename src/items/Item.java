package items;

import java.util.Objects;

public abstract class Item {

    protected String name;
    protected int cost;
    protected int sellPrice = 0;

    public abstract void use();

    public int getCost() { return cost; }

    public String getName() { return name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
