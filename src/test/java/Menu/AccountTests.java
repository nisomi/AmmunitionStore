package Menu;

import Accounts.Account;
import Accounts.Knight;
import Accounts.Seller;
import Menu.Commands.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class AccountTests {
    @Test
    public void GetAccountsFromFile(){
        List<Account> actual = new ArrayList<>();
        new GetAccountsFromFile(actual).execute();

        List<Account> expected = new ArrayList<>();
        expected.add(new Knight("1022","1022",406));
        expected.add(new Seller("1111","1111",694));

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void SaveAccountsToFile(){
        List<Account> actual = new ArrayList<>();
        Knight knight = new Knight("1022","1022",406);
        Seller seller = new Seller("1111","1111",694);
        actual.add(knight);
        actual.add(seller);
        new SaveAccountsToFile(actual).execute();

        List<Account> expected = new ArrayList<>();
        new GetAccountsFromFile(expected).execute();

        Assert.assertEquals(expected, actual);
    }

}
