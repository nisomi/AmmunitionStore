package Ammunition.Armor;

public class Shield extends Armor{
    public Shield(String name, int weight, int cost, int protection, String description) {
        super(name, weight, cost, protection, description);
        this.type ="Щит";
    }
}
