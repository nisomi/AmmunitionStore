package Menu;

import Accounts.Knight;
import Ammunition.AmmunitionItem;
import Ammunition.Armor.*;
import Ammunition.Weapons.Dagger;
import Ammunition.Weapons.Longbow;
import Ammunition.Weapons.Sword;
import Menu.Commands.SearchItemsByCost;
import Menu.Commands.SearchItemsByWeight;
import Menu.Commands.SortItemsByCost;
import Menu.Commands.SortItemsByWeight;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class SortAndSearch {
    Knight knight = new Knight("1022","1022",406);
    Boots item1 = new Boots("Черевики",10,10,10,"Просто черевики");
    Sword item2 = new Sword("Залізний меч",120,100,112,"Додає +10 до хоробрості в бою");
    Helmet item3 = new Helmet("Шолом",12,12,300,"Звичайний шолом");
    Breastplate item4 = new Breastplate("Нагрудник",245,249,4353,"Опис4");
    ChainMail item5 = new ChainMail("Кольчуга",265,500,453,"Опис5");
    Cloak item6 = new Cloak("Плащ",100,200,40,"Опис6");
    Gauntlets item7 = new Gauntlets("Рукавиці",20,150,20,"Опис7");
    Shield item8 = new Shield("Щит",500,1500,2000,"Опис8");
    Longbow item9 = new Longbow("Довгий лук",500,2500,3000,"Опис9");
    Dagger item10 = new Dagger("Кинджал",200,500,500,"Опис10");

    @Test
    public void SortItemsByCost(){
        knight.addItem(item1);
        knight.addItem(item2);
        knight.addItem(item3);
        knight.addItem(item4);
        knight.addItem(item5);
        knight.addItem(item6);
        knight.addItem(item7);
        knight.addItem(item8);
        knight.addItem(item9);
        knight.addItem(item10);

        List<AmmunitionItem> expected = knight.getAmmunitionItems();
        List<AmmunitionItem> actual = new ArrayList<>();

        actual.add(item1);
        actual.add(item3);
        actual.add(item2);
        actual.add(item7);
        actual.add(item6);
        actual.add(item4);
        actual.add(item5);
        actual.add(item8);
        new SortItemsByCost(knight.getAmmunitionItems()).execute();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void SortItemsByWeight(){
        knight.addItem(item2);
        knight.addItem(item3);
        knight.addItem(item1);

        List<AmmunitionItem> expected = knight.getAmmunitionItems();
        List<AmmunitionItem> actual = new ArrayList<>();
        actual.add(item1);
        actual.add(item3);
        actual.add(item2);

        new SortItemsByWeight(knight.getAmmunitionItems()).execute();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void SearchItemsByCost(){
        String input = "100 200";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;
        System.setOut(new PrintStream(outContent));
        System.setIn(in);

        knight.addItem(item1);
        knight.addItem(item2);
        knight.addItem(item3);

        List<AmmunitionItem> actual = new ArrayList<>();
        actual.add(item2);

        new SearchItemsByCost(knight.getAmmunitionItems()).execute();

        String expected = "Введіть мінімальне та максимальне значення вартості:\r\n" +
                item2+"\r\n";

        //assertThat(outContent.toString(), is(expected));
        Assert.assertEquals(expected, outContent.toString());
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void SearchItemsByWeight(){
        String input = "0 20";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;
        System.setOut(new PrintStream(outContent));
        System.setIn(in);

        knight.addItem(item1);
        knight.addItem(item2);
        knight.addItem(item3);

        List<AmmunitionItem> actual = new ArrayList<>();
        actual.add(item1);
        actual.add(item3);

        //SearchItemsByWeight searchItemsByWeight = new SearchItemsByWeight(knight.getAmmunitionItems());
        //List<AmmunitionItem> expected = searchItemsByWeight.searchAmmunitionByWeight(0,20);
        new SearchItemsByWeight(knight.getAmmunitionItems()).execute();

        String expected = "Введіть мінімальне та максимальне значення ваги:\r\n" +
                item1 +"\r\n" + item3+"\r\n";

        //assertThat(outContent.toString(), is(expected));
        Assert.assertEquals(expected, outContent.toString());
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
}
