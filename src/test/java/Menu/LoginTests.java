package Menu;

import Accounts.Account;
import Accounts.Knight;
import Accounts.Seller;
import Ammunition.AmmunitionItem;
import Ammunition.Armor.Boots;
import Ammunition.Armor.Breastplate;
import Ammunition.Armor.Helmet;
import Ammunition.Weapons.Sword;
import Menu.Commands.Login;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class LoginTests {
    Knight knight = new Knight("1022","1022",406);
    Seller seller = new Seller("1111","1111",694);
    Boots item1 = new Boots("Черевики",10,10,10,"Просто черевики");
    Sword item2 = new Sword("Залізний меч",120,100,112,"Додає +10 до хоробрості в бою");
    Helmet item3 = new Helmet("Шолом",12,12,300,"Звичайний шолом");
    Breastplate item4 = new Breastplate("Нагрудник",245,249,4353,"Опис4");

    List<Account> accounts = new ArrayList<>();
    List<AmmunitionItem> ammunitionItems = new ArrayList<>();

    @Test
    public void LoginKnight(){
        String input = "1022\n1022\n8\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        InputStream originalIn = System.in;
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        String expected = "Введіть логін:\r\n" +
                "Введіть пароль:\r\n"+
                "1. Купити амуніцію\r\n" +
                "2. Викинути амуніцію\r\n" +
                "3. Повернути амуніцію\r\n" +
                "4. Показати баланс \r\n" +
                "5. Показати властивості придбаної амуніції\r\n" +
                "6. Показати всю амуніцію\r\n" +
                "7. Показати куплену амуніцію\r\n" +
                "8. Вийти з акаунту\r\n"+
                "Ваш вибір:";

        accounts.add(knight);
        accounts.add(seller);
        new Login(accounts).execute();
        Assert.assertEquals(expected,outContent.toString());
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
}
