package Menu;

import Accounts.Knight;
import Ammunition.AmmunitionItem;
import Ammunition.Armor.Boots;
import Ammunition.Armor.Helmet;
import Ammunition.Weapons.Sword;
import Menu.Commands.SortItemsByCost;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SwitcherTest {
    Knight knight = new Knight("1022","1022",406);
    Boots item1 = new Boots("Черевики",10,10,10,"Просто черевики");
    Sword item2 = new Sword("Залізний меч",120,100,112,"Додає +10 до хоробрості в бою");
    Helmet item3 = new Helmet("Шолом",12,12,300,"Звичайний шолом");

    @Test
    public void Switcher() {
        knight.addItem(item1);
        knight.addItem(item2);
        knight.addItem(item3);

        List<AmmunitionItem> expected = knight.getAmmunitionItems();
        List<AmmunitionItem> actual = new ArrayList<>();

        actual.add(item1);
        actual.add(item3);
        actual.add(item2);

        Switcher switcher = new Switcher();
        switcher.addCommand(new SortItemsByCost(knight.getAmmunitionItems()));
        switcher.execute(1);
        Assert.assertEquals(expected, actual);
    }
}
