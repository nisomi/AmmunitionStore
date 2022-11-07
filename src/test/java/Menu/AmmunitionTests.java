package Menu;

import Accounts.Knight;
import Ammunition.AmmunitionItem;
import Ammunition.Armor.*;
import Ammunition.Weapons.Dagger;
import Ammunition.Weapons.Longbow;
import Ammunition.Weapons.Sword;
import Menu.Commands.GetItemsFromFile;
import Menu.Commands.SaveBoughtItems;
import Menu.Commands.SaveItemsToFile;
import Menu.Commands.ShowAmmunitionProperties;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class AmmunitionTests {

    Knight knight = new Knight("1022","1022",406);
    Sword item2 = new Sword("Залізний меч",120,100,112,"Додає +10 до хоробрості в бою");
    Breastplate item4 = new Breastplate("Нагрудник",245,249,4353,"Опис4");
    Cloak item6 = new Cloak("Плащ",100,200,40,"Опис6");
    Gauntlets item7 = new Gauntlets("Рукавиці",20,150,20,"Опис7");


    @Test
    public void GetItemsFromFile(){
        new GetItemsFromFile(knight.getAmmunitionItems(),knight).execute();
        List<AmmunitionItem> actual = knight.getAmmunitionItems();

        List<AmmunitionItem> expected = new ArrayList<>();
        Helmet item1 = new Helmet("Магічний шолом",100,99,445,"Захищає від злих чар");
        Boots item2 = new Boots("Чоботи-скороходи",100,999,144,"В них ти будеш ходити вдесятеро швидше");
        Dagger item3 = new Dagger("Кинджал",100,200,309,"Опис3");
        Longbow item4 = new Longbow("Лук",12,234,123,"Опис4");
        ChainMail item5 = new ChainMail("Кольчуга", 1222,1300,2000,"Опис5");

        expected.add(item1);
        expected.add(item2);
        expected.add(item3);
        expected.add(item4);
        expected.add(item5);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void ShowAmmunitionProperties() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        new GetItemsFromFile(knight.getAmmunitionItems(),knight).execute();

        String expected = "Загальна вартість всієї придбаної амуніції: "+knight.getAmmunitionCost() +
                "\r\n" +"Вага: "+knight.getAmmunitionWeight()+ "\r\n"+"Захист броні: "+knight.getAmmunitionProtection()+
                "\r\n"+"Сила атаки зброї: "+knight.getAmmunitionDamage()+"\r\n";

        new ShowAmmunitionProperties(knight).execute();
        Assert.assertEquals(expected, outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    public void SaveItemsToFile(){
        List <AmmunitionItem> expected_ammunitionItems = new ArrayList<>();
        new GetItemsFromFile(expected_ammunitionItems).execute();

        expected_ammunitionItems.add(item7);
        new SaveItemsToFile(expected_ammunitionItems).execute();

        List <AmmunitionItem> actual_ammunitionItems = new ArrayList<>();
        new GetItemsFromFile(actual_ammunitionItems).execute();

        Assert.assertEquals(expected_ammunitionItems, actual_ammunitionItems);
    }

    @Test
    public void SaveBoughtItems(){
        Knight knight = new Knight("1212","1212",1000);
        knight.addItem(item4);
        knight.addItem(item2);
        knight.addItem(item6);

        new SaveBoughtItems(knight).execute();

        List <AmmunitionItem> actual = new ArrayList<>();
        new GetItemsFromFile(actual, knight).execute();

        Assert.assertEquals(knight.getAmmunitionItems(),actual);
    }
}
