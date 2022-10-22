package Ammunition.Weapons;

public class Staff extends Weapon{
    public Staff(String name, int weight, int cost, int damage, String description) {
        super(name, weight, cost, damage, description);
        this.type = "Посох";
    }
}
