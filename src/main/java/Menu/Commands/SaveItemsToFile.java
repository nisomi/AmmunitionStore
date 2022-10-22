package Menu.Commands;

import Accounts.*;
import Ammunition.AmmunitionItem;
import Ammunition.Armor.Armor;
import Ammunition.Weapons.Weapon;
import Menu.Command;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SaveItemsToFile implements Command {
    List<AmmunitionItem> ammunitionItems;
    public SaveItemsToFile(List<AmmunitionItem> ammunitionItems) {
        this.ammunitionItems = ammunitionItems;
    }
    public void execute() {
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
            pw.close();
        }
        catch (IOException e){
            System.out.println("Файл не знайдено");
        }
    }
}
