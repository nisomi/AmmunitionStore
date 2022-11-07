package Menu;

import Accounts.Account;
import Accounts.Knight;
import Accounts.Seller;
import Ammunition.AmmunitionItem;
import Ammunition.Armor.ChainMail;
import Ammunition.Armor.Helmet;
import Ammunition.Weapons.Sword;
import Menu.Commands.BuyItem;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BuyItemTest {
    Knight knight = new Knight("1022","1022",406);
    Sword item2 = new Sword("Залізний меч",120,100,112,"Додає +10 до хоробрості в бою");
    Helmet item3 = new Helmet("Шолом",12,12,300,"Звичайний шолом");
    ChainMail item5 = new ChainMail("Кольчуга",265,500,453,"Опис5");

    @Test
    public void BuyItem(){

        List<Account> accounts = new ArrayList<>();
        accounts.add(new Knight("1022","1022",406));
        accounts.add(new Seller("1111","1111",694));

        List<AmmunitionItem> ammunitionItems = new ArrayList<>();
        ammunitionItems.add(item2);
        ammunitionItems.add(item3);
        ammunitionItems.add(item5);

        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        InputStream originalIn = System.in;
        System.setIn(in);

        List<AmmunitionItem> expected = new ArrayList<>();
        expected.add(item2);

        new BuyItem(knight,ammunitionItems,accounts).execute();

        Assert.assertEquals(expected,knight.getAmmunitionItems());
        System.setIn(originalIn);
    }
}
