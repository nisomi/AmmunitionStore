package Accounts;

import Ammunition.AmmunitionItem;
import Ammunition.Armor.Armor;
import Ammunition.Weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Account {

    private List<AmmunitionItem> ammunitionItems = new ArrayList<>();

    public Knight(String login, String password, int money) {
        super(login, password);
        this.wallet = money;
        this.type = "Лицар";
    }

    public List<AmmunitionItem> getAmmunitionItems() {
        return ammunitionItems;
    }

    public void addItem(AmmunitionItem item){
        if (!checkIfAlreadySelected(item))
            ammunitionItems.add(item);
        else{
            System.out.println("Помилка, елемент з цієї категорії вже обрано");
        }
    }

    public boolean checkIfAlreadySelected(AmmunitionItem item){
        for (AmmunitionItem ammunition:ammunitionItems){
            if (ammunition.getClass() == item.getClass()){
                return true;
            }
            else if(ammunition instanceof Weapon && item instanceof Weapon){
                return true;
            }
        }
        return false;
    }

    public void deleteItem(int number){
        ammunitionItems.remove(ammunitionItems.get(number));
    }

    public int getAmmunitionCost() {
        int sumCost = 0;
        for (AmmunitionItem value : ammunitionItems) {
            sumCost += value.getCost();
        }
        return sumCost;
    }

    public int getAmmunitionWeight() {
        int sumWeight = 0;
        for (AmmunitionItem value : ammunitionItems) {
            sumWeight += value.getWeight();
        }
        return sumWeight;
    }

    public int getAmmunitionDamage() {
        for (AmmunitionItem item : ammunitionItems) {
            if(item instanceof Weapon){
                return ((Weapon)item).getDamage();
            }
        }
        return 0;
    }

    public int getAmmunitionProtection() {
        int sumProtection = 0;
        for (AmmunitionItem item : ammunitionItems) {
            if(item instanceof Armor){
                sumProtection += ((Armor) item).getProtection();
            }
        }
        return sumProtection;
    }

}
