package Menu;

import Accounts.Knight;
import Ammunition.Armor.*;
import Ammunition.Weapons.Dagger;
import Ammunition.Weapons.Longbow;
import Ammunition.Weapons.Sword;
import Menu.Commands.ShowAllAmmunition;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class ShowAmmunition {
    Knight knight = new Knight("1022","1022",406);
    Boots item1 = new Boots("Черевики",10,10,10,"Просто черевики");
    Sword item2 = new Sword("Залізний меч",120,100,112,"Додає +10 до хоробрості в бою");
    Helmet item3 = new Helmet("Шолом",12,12,300,"Звичайний шолом");

    @Test
    public void ShowAllAmmunition1(){
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;
        System.setOut(new PrintStream(outContent));
        System.setIn(in);
        knight.addItem(item1);
        knight.addItem(item2);
        knight.addItem(item3);

        new ShowAllAmmunition(knight.getAmmunitionItems()).execute();

        String expected = "1. Просто показати амуніцію" +
                "\r\n"+"2. Відсортувати обрану амуніцію за вартістю"+
                "\r\n"+"3. Відсортувати обрану амуніцію за вагою"+
                "\r\n"+"4. Знайти елементи амуніції, які відповідають заданому діапазону цін"+
                "\r\n"+"5. Знайти елементи амуніції, які відповідають заданому діапазону ваги"+
                "\r\n" + item1 + "\r\n"+ item2+"\r\n"+item3 +"\r\n";

        Assert.assertEquals(expected, outContent.toString());
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void ShowAllAmmunition2(){
        String input = "2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;
        System.setOut(new PrintStream(outContent));
        System.setIn(in);
        knight.addItem(item1);
        knight.addItem(item2);
        knight.addItem(item3);

        new ShowAllAmmunition(knight.getAmmunitionItems()).execute();

        String expected = "1. Просто показати амуніцію" +
                "\r\n"+"2. Відсортувати обрану амуніцію за вартістю"+
                "\r\n"+"3. Відсортувати обрану амуніцію за вагою"+
                "\r\n"+"4. Знайти елементи амуніції, які відповідають заданому діапазону цін"+
                "\r\n"+"5. Знайти елементи амуніції, які відповідають заданому діапазону ваги"+
                "\r\n" + item1 + "\r\n"+ item3+"\r\n"+item2 +"\r\n";

        Assert.assertEquals(expected, outContent.toString());
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void ShowAllAmmunition3(){
        String input = "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;
        System.setOut(new PrintStream(outContent));
        System.setIn(in);
        knight.addItem(item1);
        knight.addItem(item2);
        knight.addItem(item3);

        new ShowAllAmmunition(knight.getAmmunitionItems()).execute();

        String expected = "1. Просто показати амуніцію" +
                "\r\n"+"2. Відсортувати обрану амуніцію за вартістю"+
                "\r\n"+"3. Відсортувати обрану амуніцію за вагою"+
                "\r\n"+"4. Знайти елементи амуніції, які відповідають заданому діапазону цін"+
                "\r\n"+"5. Знайти елементи амуніції, які відповідають заданому діапазону ваги"+
                "\r\n" + item1 + "\r\n"+ item3+"\r\n"+item2 +"\r\n";

        Assert.assertEquals(expected, outContent.toString());
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

}
