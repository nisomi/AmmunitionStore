package Accounts;

public class Seller extends Account{
    public Seller(String login, String password, int money) {
        super(login, password);
        this.type = "Торговець";
        this.wallet = money;
    }
}
