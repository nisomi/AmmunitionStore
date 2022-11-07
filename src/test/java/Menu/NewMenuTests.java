package Menu;

import Accounts.Account;
import Accounts.Knight;
import Accounts.Seller;
import Ammunition.AmmunitionItem;
import Ammunition.Armor.Boots;
import Ammunition.Armor.Breastplate;
import Ammunition.Armor.Helmet;
import Ammunition.Weapons.Sword;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.image.Kernel;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class NewMenuTests {
    Knight knight = new Knight("1022","1022",406);
    Seller seller = new Seller("1111","1111",694);
    Boots item1 = new Boots("Черевики",10,10,10,"Просто черевики");
    Sword item2 = new Sword("Залізний меч",120,100,112,"Додає +10 до хоробрості в бою");
    Helmet item3 = new Helmet("Шолом",12,12,300,"Звичайний шолом");
    Breastplate item4 = new Breastplate("Нагрудник",245,249,4353,"Опис4");

    List<Account> accounts = new ArrayList<>();
    List<AmmunitionItem> ammunitionItems = new ArrayList<>();

    @Test
    public void StartMenu(){
        accounts.add(knight);
        accounts.add(seller);
        Menu menu = new Menu(accounts);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        menu.printOptions();
        String expected = "1. Увійти\r\n" +
                "2. Зареєстуватись\r\n" +
                "3. Вийти\r\n";
        System.setOut(originalOut);
        Assert.assertEquals(expected,outContent.toString());
    }

    @Test
    public void StartMenuKnight(){
        ammunitionItems.add(item2);
        ammunitionItems.add(item3);
        ammunitionItems.add(item4);

        accounts.add(knight);
        accounts.add(seller);
        Menu menu = new Menu(knight,ammunitionItems,accounts);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        menu.printOptions();
        String expected = "1. Купити амуніцію\r\n" +
                "2. Викинути амуніцію\r\n" +
                "3. Повернути амуніцію\r\n" +
                "4. Показати баланс \r\n" +
                "5. Показати властивості придбаної амуніції\r\n" +
                "6. Показати всю амуніцію\r\n" +
                "7. Показати куплену амуніцію\r\n" +
                "8. Вийти з акаунту\r\n";
        System.setOut(originalOut);
        Assert.assertEquals(expected,outContent.toString());
    }

    @Test
    public void StartMenuSeller(){
        ammunitionItems.add(item2);
        ammunitionItems.add(item3);
        ammunitionItems.add(item4);

        accounts.add(knight);
        accounts.add(seller);
        Menu menu = new Menu(seller,ammunitionItems,accounts);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        menu.printOptions();
        String expected = "1. Додати амуніцію\r\n" +
                "2. Видалити амуніцію\r\n" +
                "3. Показати баланс\r\n" +
                "4. Показати всю амуніцію\r\n" +
                "5. Вийти з акаунту\r\n";
        System.setOut(originalOut);
        Assert.assertEquals(expected,outContent.toString());
    }

    @Test
    public void GetMenuSize(){
        accounts.add(knight);
        accounts.add(seller);
        Menu menu = new Menu(accounts);
        Assert.assertEquals(3,menu.getSize());
    }
}
