package Menu;

import Accounts.Knight;
import Accounts.Account;
import Accounts.Seller;
import Ammunition.AmmunitionItem;
import Menu.Commands.*;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private Switcher switcher;
    private List<String> options = new ArrayList<>();
    public  Menu(List<Account> accounts){
        switcher = new Switcher();
        new GetAccountsFromFile(accounts).execute();
        switcher = new Switcher();
        options.add("1. Увійти");
        switcher.addCommand(new Login(accounts));
        options.add("2. Зареєстуватись");
        switcher.addCommand(new SignUp(accounts));
        options.add("3. Вийти");
        switcher.addCommand(new Exit());
        }

    public  Menu(Account account, List<AmmunitionItem> ammunition, List<Account> accounts){
        switcher = new Switcher();
        new GetItemsFromFile(ammunition).execute();
        if (account instanceof Knight){
            new GetItemsFromFile(((Knight) account).getAmmunitionItems(), account).execute();
            options.add("1. Купити амуніцію");
            switcher.addCommand(new BuyItem(((Knight) account), ammunition, accounts));
            options.add("2. Викинути амуніцію");
            switcher.addCommand(new TrowAwayItem((Knight) account, accounts));
            options.add("3. Повернути амуніцію");
            switcher.addCommand(new ReturnItem(((Knight) account), ammunition, accounts));
            options.add("4. Показати баланс ");
            switcher.addCommand(new ShowWalletBalance(account));
            options.add("5. Показати властивості придбаної амуніції");
            switcher.addCommand(new ShowAmmunitionProperties((Knight) account));
            options.add("6. Показати всю амуніцію");
            switcher.addCommand(new ShowAllAmmunition(ammunition));
            options.add("7. Показати куплену амуніцію");
            switcher.addCommand(new ShowAllAmmunition(((Knight) account).getAmmunitionItems()));
            options.add("8. Вийти");
            switcher.addCommand(new Exit(ammunition, (Knight) account, accounts));
        }
        else {
            options.add("1. Додати амуніцію");
            switcher.addCommand(new AddItem((Seller)account, ammunition));
            options.add("2. Видалити амуніцію");
            switcher.addCommand(new DeleteItem(ammunition));
            options.add("3. Показати баланс");
            switcher.addCommand(new ShowWalletBalance(account));
            options.add("4. Показати всю амуніцію");
            switcher.addCommand(new ShowAllAmmunition(ammunition));
            options.add("5. Вийти");
            switcher.addCommand(new Exit(ammunition, accounts));
        }
    }

    public void makeChoice(Integer number){
        switcher.execute(number);
    }

    public void printOptions(){
        for (String option : options) {
            System.out.println(option);
        }
    }

    public int getSize(){
        return options.size();
    }
}
