package Accounts;

import Ammunition.AmmunitionItem;
import Ammunition.Armor.Boots;
import Ammunition.Armor.Helmet;
import Ammunition.Weapons.Staff;
import Ammunition.Weapons.Sword;
import Menu.Commands.ShowWalletBalance;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class AccountTest {
    Knight knight = new Knight("1111","1111", 1000);
    Boots item1 = new Boots("Черевики",10,10,10,"Просто черевики");
    Sword item2 = new Sword("Залізний меч",120,100,112,"Додає +10 до хоробрості в бою");
    Helmet item3 = new Helmet("Шолом",12,12,300,"Звичайний шолом");
    Staff item4 = new Staff("Посох",120,140,1340,"Магічний посох");

    @Test
    void testGetLogin() {
        String actual = knight.getLogin();
        Assert.assertEquals("1111",actual);
    }

    @Test
    void testGetPassword() {
        String actual = knight.getPassword();
        Assert.assertEquals("1111",actual);
    }

    @Test
    public void getWallet(){
        Assert.assertEquals(1000,knight.getWallet());
    }

    @Test
    public void getType(){
        Assert.assertEquals("Лицар",knight.getType());
    }

    @Test
    public void setWallet(){
        knight.setWallet(100);
        Assert.assertEquals(100,knight.getWallet());
    }

    @Test
    public void equal(){
        Seller seller1 = new Seller("1111","1333",100);
        Seller seller2 = new Seller("1111","1333",100);
        Assert.assertEquals(seller1,seller2);
    }

    @Test
    public void AddAndGetAllItems(){
        knight.addItem(item1);
        knight.addItem(item2);
        List<AmmunitionItem> expected = knight.getAmmunitionItems();

        List<AmmunitionItem> actual = new ArrayList<>();
        actual.add(item1);
        actual.add(item2);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void checkIfAlreadySelected(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        knight.addItem(item1);
        knight.addItem(item2);
        knight.addItem(item3);
        knight.addItem(item4);
        String expected = "Помилка, елемент з цієї категорії вже обрано\r\n";
        Assert.assertEquals(expected,outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    public void checkIfAlreadySelected_true(){
        knight.addItem(item1);
        knight.addItem(item2);
        knight.addItem(item3);
        knight.addItem(item4);
        Assert.assertEquals(true,knight.checkIfAlreadySelected(item1));
    }

    @Test
    public void deleteItem(){
        knight.addItem(item1);
        knight.addItem(item2);
        knight.addItem(item3);
        List<AmmunitionItem> expected = knight.getAmmunitionItems();
        knight.deleteItem(2);

        List<AmmunitionItem> actual = new ArrayList<>();
        actual.add(item1);
        actual.add(item2);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void CreateItem(){
        Boots item2 = new Boots("Черевики",10,10,10,"Просто черевики");
        item2.setName(item1.getName());
        item2.setCost(item1.getCost());
        item2.setWeight(item1.getWeight());
        item2.setDescription(item1.getDescription());
        item2.setType(item1.getType());
        Assert.assertEquals(item2, item1);

    }

    @Test
    public void GetDamage(){
        Assert.assertEquals(112, item2.getDamage());
    }

    @Test
    public void String(){
        String expected = "Меч : \n" +
                "\tім'я = Залізний меч\n" +
                "\tвага = 120\n" +
                "\tвартість = 100\n" +
                "\tособливі характеристики = Додає +10 до хоробрості в бою";
        Assert.assertEquals(item2.toString(), expected);
    }

    @Test
    public void getAmmunitionCost(){
        knight.addItem(item1);
        Assert.assertEquals(knight.getAmmunitionCost(), item1.getCost());
    }

    @Test
    public void getAmmunitionWeight(){
        knight.addItem(item1);
        Assert.assertEquals(knight.getAmmunitionWeight(), item1.getWeight());
    }

    @Test
    public void getAmmunitionProtection(){
        knight.addItem(item1);
        Assert.assertEquals(knight.getAmmunitionProtection(), item1.getProtection());
    }

    @Test
    public void getAmmunitionDamage(){
        knight.addItem(item1);
        knight.addItem(item2);
        Assert.assertEquals(knight.getAmmunitionDamage(), item2.getDamage());
    }

    @Test
    public void getAmmunitionDamage0(){
        Assert.assertEquals(0, knight.getAmmunitionDamage());
    }

    @Test
    public void ShowWalletBalance(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        new ShowWalletBalance(knight).execute();

        String expected = "Ваш баланс: "+ knight.getWallet()+"\r\n";
        Assert.assertEquals(expected, outContent.toString());
        System.setOut(originalOut);
    }
}