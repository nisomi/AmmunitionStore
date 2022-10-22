import Accounts.Account;
import Accounts.Knight;
import Ammunition.AmmunitionItem;
import Ammunition.Armor.Boots;
import Ammunition.Weapons.Sword;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AmmunutionTests {
    Knight knight = new Knight("1111","1111", 1000);
    List<AmmunitionItem> actual = new ArrayList<>();
    @Test
    public void GetAllItems(){
        Boots item1 = new Boots("Черевики",10,10,10,"Просто черевики");
        Sword item2 = new Sword("Залізний меч",120,100,112,"Додає +10 до хоробрості в бою");
        knight.addItem(item1);
        knight.addItem(item2);
        List<AmmunitionItem> expected = knight.getAmmunitionItems();

        actual.add(item1);
        actual.add(item2);

        Assert.assertEquals(expected, actual);
    }
}
