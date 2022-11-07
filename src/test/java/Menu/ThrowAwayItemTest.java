package Menu;

import Accounts.Account;
import Accounts.Knight;
import Ammunition.AmmunitionItem;
import Ammunition.Armor.*;
import Ammunition.Weapons.Dagger;
import Ammunition.Weapons.Longbow;
import Ammunition.Weapons.Sword;
import Menu.Commands.GetAccountsFromFile;
import Menu.Commands.GetItemsFromFile;
import Menu.Commands.ReturnItem;
import Menu.Commands.ThrowAwayItem;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ThrowAwayItemTest {
    Boots item1 = new Boots("Черевики",10,10,10,"Просто черевики");
    Sword item2 = new Sword("Залізний меч",120,100,112,"Додає +10 до хоробрості в бою");
    Helmet item3 = new Helmet("Шолом",12,12,300,"Звичайний шолом");
    Cloak item6 = new Cloak("Плащ",100,200,40,"Опис6");
    Shield item8 = new Shield("Щит",500,1500,2000,"Опис8");
    Longbow item9 = new Longbow("Довгий лук",500,2500,3000,"Опис9");
    Dagger item10 = new Dagger("Кинджал",200,500,500,"Опис10");


    @Test
    public void ThrowAwayItem(){
        Knight knight = new Knight("1022","1022",406);
        knight.addItem(item1);
        knight.addItem(item2);
        knight.addItem(item3);
        knight.addItem(item8);
        knight.addItem(item6);
        knight.addItem(item9);
        knight.addItem(item10);
        List<AmmunitionItem> expected = new ArrayList<>(knight.getAmmunitionItems());
        expected.remove(item1);

        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        InputStream originalIn = System.in;
        System.setIn(in);

        new ThrowAwayItem(knight).execute();

        Assert.assertEquals(expected, knight.getAmmunitionItems());
        System.setIn(originalIn);
    }

}
