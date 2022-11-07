package Menu;

import Accounts.Seller;
import Ammunition.AmmunitionItem;
import Ammunition.Armor.*;
import Ammunition.Weapons.Longbow;
import Ammunition.Weapons.Sword;
import Menu.Commands.AddItem;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AddItemsTest {
    Sword item2 = new Sword("Залізний меч",120,100,112,"Додає +10 до хоробрості в бою");
    Helmet item3 = new Helmet("Шолом",12,12,300,"Звичайний шолом");
    ChainMail item5 = new ChainMail("Кольчуга",265,500,453,"Опис5");

    @Test
    public void AddItem(){
        Seller seller = new Seller("1512","1212",10000);
        List<AmmunitionItem> actual_ammunitionItems = new ArrayList<>();
        actual_ammunitionItems.add(item2);
        actual_ammunitionItems.add(item3);
        actual_ammunitionItems.add(item5);

        List <AmmunitionItem> expected_ammunitionItems = new ArrayList<>(actual_ammunitionItems);
        expected_ammunitionItems.add(new Boots("Чоботи",100,200,300,"Просто чоботи"));
        String input = "1\nЧоботи\n100\n200\n300\nПросто чоботи\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        InputStream originalIn = System.in;
        System.setIn(in);

        new AddItem(seller, actual_ammunitionItems).execute();

        expected_ammunitionItems.add(new Sword("Меч",100,200,300,"Просто меч"));
        input = "11\nМеч\n100\n200\n300\nПросто меч\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        new AddItem(seller, actual_ammunitionItems).execute();

        expected_ammunitionItems.add(new Breastplate("Нагрудник",100,200,300,"Просто нагрудник"));
        input = "2\nНагрудник\n100\n200\n300\nПросто нагрудник\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        new AddItem(seller, actual_ammunitionItems).execute();

        expected_ammunitionItems.add(new ChainMail("Кольчуга",100,200,300,"Просто Кольчуга"));
        input = "3\nКольчуга\n100\n200\n300\nПросто Кольчуга\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        new AddItem(seller, actual_ammunitionItems).execute();

        expected_ammunitionItems.add(new Cloak("Плащ",100,200,40,"Опис6"));
        input = "4\nПлащ\n100\n200\n40\nОпис6\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        new AddItem(seller, actual_ammunitionItems).execute();

        expected_ammunitionItems.add(new Gauntlets("Рукавиці",20,150,20,"Опис7"));
        input = "5\nРукавиці\n20\n150\n20\nОпис7\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        new AddItem(seller, actual_ammunitionItems).execute();

        expected_ammunitionItems.add(new Shield("Щит",500,1500,2000,"Опис8"));

        input = "7\nЩит\n500\n1500\n2000\nОпис8\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        new AddItem(seller, actual_ammunitionItems).execute();

        expected_ammunitionItems.add(new Longbow("Довгий лук",500,2500,3000,"Опис9"));
        input = "9\nДовгий лук\n500\n2500\n3000\nОпис9\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        new AddItem(seller, actual_ammunitionItems).execute();
        Assert.assertEquals(expected_ammunitionItems, actual_ammunitionItems);
        System.setIn(originalIn);
    }
}
