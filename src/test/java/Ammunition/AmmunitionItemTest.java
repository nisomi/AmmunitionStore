package Ammunition;

import Ammunition.Armor.*;
import Ammunition.Weapons.Dagger;
import Ammunition.Weapons.Longbow;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;

import static org.junit.Assert.assertEquals;

class AmmunitionItemTest {

    @Test
    void costComparator() {
        Breastplate item1 = new Breastplate("Нагрудник",245,249,4353,"Опис4");
        ChainMail item2 = new ChainMail("Кольчуга",265,500,453,"Опис5");
        List<AmmunitionItem> ammunitionItems = new ArrayList<>();
        ammunitionItems.add(item1);
        ammunitionItems.add(item2);
        ammunitionItems.sort(new AmmunitionItem.CostComparator());
        assertEquals(item1, Collections.min(ammunitionItems, new AmmunitionItem.CostComparator()));  // For example.
    }

    @Test
    void weightComparator() {
        Breastplate item1 = new Breastplate("Нагрудник",245,249,4353,"Опис4");
        ChainMail item2 = new ChainMail("Кольчуга",265,500,453,"Опис5");
        List<AmmunitionItem> ammunitionItems = new ArrayList<>();
        ammunitionItems.add(item1);
        ammunitionItems.add(item2);
        ammunitionItems.sort(new AmmunitionItem.WeightComparator());
        assertEquals(item1, Collections.min(ammunitionItems, new AmmunitionItem.WeightComparator()));  // For example.
    }

    @Test
    public void Breastplate(){
        Breastplate item4 = new Breastplate("Нагрудник",245,249,4353,"Опис4");
        assertEquals(245,item4.getWeight());
    }

    @Test
    public void ChainMail(){
        ChainMail item5 = new ChainMail("Кольчуга",265,500,453,"Опис5");
        assertEquals(265,item5.getWeight());
    }

    @Test
    public void Cloak(){
        Cloak item6 = new Cloak("Плащ",100,200,40,"Опис6");
        assertEquals(100,item6.getWeight());
    }

    @Test
    public void Gauntlets(){
        Gauntlets item7 = new Gauntlets("Рукавиці",20,150,20,"Опис7");
        assertEquals(20,item7.getWeight());
    }

    @Test
    public void Longbow(){
        Longbow item9 = new Longbow("Довгий лук",500,2500,3000,"Опис9");
        assertEquals(500,item9.getWeight());
    }

    @Test
    public void Dagger(){
        Dagger item10 = new Dagger("Кинджал",200,500,500,"Опис10");
        assertEquals(500,item10.getCost());
    }

    @Test
    public void Shield(){
        Shield item8 = new Shield("Щит",500,1500,2000,"Опис8");
        assertEquals(1500,item8.getCost());
    }

}