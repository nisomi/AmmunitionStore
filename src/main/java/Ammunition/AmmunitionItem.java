package Ammunition;

import java.util.Comparator;
import java.util.Objects;

public abstract class AmmunitionItem {
    protected String name;
    protected int weight;
    protected int cost;
    protected String description;
    protected String type;

    public AmmunitionItem(String name, int weight, int cost, String description) {
        this.weight = weight;
        this.cost = cost;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return  type + " : " + "\n\t" +
                "ім'я = " + name + "\n\t" +
                "вага = " + weight + "\n\t" +
                "вартість = " + cost + "\n\t" +
                "особливі характеристики = " + description;
    }

    public static class CostComparator implements Comparator<AmmunitionItem>{
        @Override
        public int compare(AmmunitionItem item1, AmmunitionItem item2){
            return Integer.compare(item1.cost, item2.cost);
        }
    }

    public static class WeightComparator implements Comparator<AmmunitionItem>{
        @Override
        public int compare(AmmunitionItem item1, AmmunitionItem item2){
            return Integer.compare(item1.weight, item2.weight);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AmmunitionItem item = (AmmunitionItem) o;
        return  Objects.equals(name, item.name) &&
                Objects.equals(description, item.description) &&
                cost == item.cost &&
                weight == item.weight;
    }

}
