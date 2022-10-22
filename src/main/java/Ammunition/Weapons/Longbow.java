package Ammunition.Weapons;

public class Longbow extends Weapon{
    public Longbow(String name, int weight, int cost, int damage, String description) {
        super(name, weight, cost, damage, description);
        this.type = "Довгий лук";
    }
}
