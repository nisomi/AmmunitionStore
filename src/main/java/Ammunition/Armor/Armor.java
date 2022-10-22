package Ammunition.Armor;

import Ammunition.AmmunitionItem;

public abstract class Armor extends AmmunitionItem {
    protected int protection;
    public Armor(String name, int weight, int cost, int protection, String description) {
        super(name, weight, cost, description);
        this.protection = protection;
    }

    public int getProtection() {
        return protection;
    }
}
