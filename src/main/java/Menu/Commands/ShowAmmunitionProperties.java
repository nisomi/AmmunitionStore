package Menu.Commands;

import Accounts.Knight;
import Menu.Command;

public class ShowAmmunitionProperties implements Command {
    Knight knight;

    public ShowAmmunitionProperties(Knight knight){
        this.knight = knight;
    }

    public void execute() {
        System.out.println("Загальна вартість всієї придбаної амуніції: "+knight.getAmmunitionCost());
        System.out.println("Вага: "+knight.getAmmunitionWeight());
        System.out.println("Захист броні: "+knight.getAmmunitionProtection());
        System.out.println("Сила атаки зброї: "+knight.getAmmunitionDamage());
    }
}
