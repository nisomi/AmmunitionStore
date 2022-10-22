package Ammunition.Weapons;

import Ammunition.AmmunitionItem;

public abstract class Weapon extends AmmunitionItem {
    protected int damage;
    public Weapon(String name, int weight, int cost,int damage, String description) {
        super(name, weight, cost, description);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
