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
import Menu.Commands.SignUp;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class SignUpTests {
    Knight knight = new Knight("1022","1022",406);
    Seller seller = new Seller("1111","1111",694);
    Boots item1 = new Boots("Черевики",10,10,10,"Просто черевики");
    Sword item2 = new Sword("Залізний меч",120,100,112,"Додає +10 до хоробрості в бою");
    Helmet item3 = new Helmet("Шолом",12,12,300,"Звичайний шолом");
    Breastplate item4 = new Breastplate("Нагрудник",245,249,4353,"Опис4");

    List<Account> accounts = new ArrayList<>();
    List<AmmunitionItem> ammunitionItems = new ArrayList<>();

    @Test
    public void SignUpKnight_Exist(){
        String input = "1022\n1022\n8\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        InputStream originalIn = System.in;
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        String expected =
                "Введіть логін:\r\n" +
                "Такий користувач вже існує, спробуйте ще раз\r\n";

        accounts.add(knight);
        accounts.add(seller);
        new SignUp(accounts).execute();
        Assert.assertEquals(expected,outContent.toString());
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void SignUpKnight_NotExist(){
        String input = "1023\n1023\n800\n4\n8\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        InputStream originalIn = System.in;
        System.setIn(in);
        List<Account> expected = new ArrayList<>();
        expected.add(knight);
        expected.add(seller);
        expected.add(new Knight("1023","1023",800));
        accounts.add(knight);
        accounts.add(seller);
        new SignUp(accounts).execute();
        Assert.assertEquals(accounts,expected);
        System.setIn(originalIn);
    }
}
