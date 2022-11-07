package Accounts;

import java.util.Objects;

public class Account {
    String login;
    String password;
    String type;
    int wallet;

    public Account(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public String getType() {
        return type;
    }
    public int getWallet() {
        return wallet;
    }
    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return  Objects.equals(login, account.login) &&
                Objects.equals(password, account.password) &&
                wallet == account.wallet;
    }

}
