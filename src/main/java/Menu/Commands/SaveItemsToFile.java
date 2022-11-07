package Menu.Commands;

import Accounts.*;
import Ammunition.AmmunitionItem;
import Ammunition.Armor.Armor;
import Ammunition.Weapons.Weapon;
import Menu.Command;
import Logger.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Logger.ViaMail.sendMessage;

public class SaveItemsToFile implements Command {
    List<AmmunitionItem> ammunitionItems;
    private static final Logger logger = Logger.getLogger(SaveItemsToFile.class.getName());

    public SaveItemsToFile(List<AmmunitionItem> ammunitionItems) {
        this.ammunitionItems = ammunitionItems;
    }
    public void execute() {
        Log.setupLogger(logger);
        try {
            String FilePath = "E:\\2 курс\\1 сем\\ПП\\Knight.txt";
            File file = new File(FilePath);
            PrintWriter pw = new PrintWriter(file);
            for (AmmunitionItem ammunition: ammunitionItems) {
                pw.print(ammunition.getType() + "_" + ammunition.getName() + "_" + ammunition.getWeight() + "_" + ammunition.getCost() + "_");
                if (ammunition instanceof Weapon) {
                    pw.println(((Weapon) ammunition).getDamage()+ "_" + ammunition.getDescription());
                }
                else {
                    pw.println(((Armor) ammunition).getProtection()+ "_" + ammunition.getDescription());

                }
            }
            logger.log(Level.INFO, "all items successfully saved");
            pw.close();
        }
        catch (IOException e){
            System.out.println("Файл не знайдено");
            logger.log(Level.SEVERE, "file not found");
            sendMessage("Critical error occurred: " + e + "\nFile with ammunition items not found");
            new Exit().execute();
        }
    }
}
