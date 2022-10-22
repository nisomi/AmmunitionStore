package Ammunition.Armor;

public class Helmet extends Armor {

    public Helmet(String name, int weight, int cost, int protection, String description) {
        super(name, weight, cost, protection, description);
        this.type = "Шолом";
    }
}
