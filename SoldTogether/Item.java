package SoldTogether;

public class Item implements Comparable<Item> {
    private String name;
    private int occurance;

    public String getName() {
        return name;
    }

    public int getOccurance() {
        return occurance;
    }

    @Override
    public int compareTo(Item item2) {
        return -(this.occurance-item2.occurance);
    }

    public Item(String name, int occurance) {
        this.name = name;
        this.occurance = occurance;
    }

    @Override
    public String toString() {
        return "Item [name=" + name + ", occurance=" + occurance + "]";
    }
}
