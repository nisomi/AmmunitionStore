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

public class SaveBoughtItems implements Command {
    Knight knight;

    public SaveBoughtItems(Knight knight) {
        this.knight = knight;
    }
    public void execute() {
        try {
            String FilePath = "E:\\2 курс\\1 сем\\ПП\\Ammunition\\ammunitionFor" + knight.getLogin() + ".txt";
            File file = new File(FilePath);
            if(!file.exists()){
                file.createNewFile();
            }
            PrintWriter pw = new PrintWriter(file);
            for (AmmunitionItem ammunition: knight.getAmmunitionItems()) {
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
