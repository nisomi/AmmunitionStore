package Ammunition.Weapons;

public class Sword extends Weapon {

    public Sword(String name, int weight, int cost, int damage, String description) {
        super(name, weight, cost, damage, description);
        this. type = "Меч";
    }
}
