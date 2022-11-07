package Menu.Commands;

import Accounts.Knight;
import Menu.Command;
import Logger.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowAmmunitionProperties implements Command {
    Knight knight;
    private static final Logger logger = Logger.getLogger(ShowAmmunitionProperties.class.getName());

    public ShowAmmunitionProperties(Knight knight){
        this.knight = knight;
    }

    public void execute() {
        Log.setupLogger(logger);
        System.out.println("Загальна вартість всієї придбаної амуніції: "+knight.getAmmunitionCost());
        System.out.println("Вага: "+knight.getAmmunitionWeight());
        System.out.println("Захист броні: "+knight.getAmmunitionProtection());
        System.out.println("Сила атаки зброї: "+knight.getAmmunitionDamage());
        logger.log(Level.INFO, "Showed ammunition properties");
    }
}
