package Menu;

import Ammunition.AmmunitionItem;
import Ammunition.Armor.ChainMail;
import Ammunition.Armor.Helmet;
import Ammunition.Weapons.Sword;
import Menu.Commands.DeleteItem;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DeleteItemTest {
    Sword item2 = new Sword("Залізний меч",120,100,112,"Додає +10 до хоробрості в бою");
    Helmet item3 = new Helmet("Шолом",12,12,300,"Звичайний шолом");
    ChainMail item5 = new ChainMail("Кольчуга",265,500,453,"Опис5");

    @Test
    public void DeleteItem(){
        List<AmmunitionItem> actual_ammunitionItems = new ArrayList<>();
        actual_ammunitionItems.add(item2);
        actual_ammunitionItems.add(item3);
        actual_ammunitionItems.add(item5);

        List <AmmunitionItem> expected_ammunitionItems = new ArrayList<>(actual_ammunitionItems);
        expected_ammunitionItems.remove(item2);

        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        InputStream originalIn = System.in;
        System.setIn(in);

        new DeleteItem(actual_ammunitionItems).execute();

        Assert.assertEquals(expected_ammunitionItems, actual_ammunitionItems);
        System.setIn(originalIn);
    }
}
