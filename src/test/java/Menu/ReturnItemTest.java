package Menu;

import Accounts.Account;
import Accounts.Knight;
import Ammunition.AmmunitionItem;
import Menu.Commands.GetAccountsFromFile;
import Menu.Commands.GetItemsFromFile;
import Menu.Commands.ReturnItem;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ReturnItemTest {
    @Test
    public void ReturnItem(){
        Knight knight = new Knight("1022","1022",406);

        new GetItemsFromFile(knight.getAmmunitionItems(),knight).execute();
        System.out.println(knight.getAmmunitionItems());

        List<AmmunitionItem> actual_ammunitionItems = new ArrayList<>();
        new GetItemsFromFile(actual_ammunitionItems).execute();

        List <AmmunitionItem> expected_ammunitionItems = new ArrayList<>(actual_ammunitionItems);
        expected_ammunitionItems.add(knight.getAmmunitionItems().get(0));

        List <Account> accounts = new ArrayList<>();
        new GetAccountsFromFile(accounts).execute();

        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        InputStream originalIn = System.in;
        System.setIn(in);

        new ReturnItem(knight, actual_ammunitionItems, accounts).execute();

        Assert.assertEquals(expected_ammunitionItems, actual_ammunitionItems);
        System.setIn(originalIn);
    }
}
