package com.mirkowu.gildedrose;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;


public class GildedRoseTest {

    @Test
    public void resultEqual() {

        String[] names = new String[]{GildedRose2.A, GildedRose2.B, GildedRose2.S, "x"};
        int[] sellIns = new int[]{12, 11, 10, 7, 6, 5, 2, 1, 0, -1};
        int[] qualities = new int[]{-1, 0, 1, 47, 48, 49, 50, 51, 52};


        ArrayList<Item> list = new ArrayList<>();
        ArrayList<Item> list2 = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < sellIns.length; j++) {
                for (int k = 0; k < qualities.length; k++) {
                    Item item = new Item(names[i], sellIns[j], qualities[k]);
                    list.add(item);
                    list2.add(item);
                }
            }
        }
        Item[] items = list.toArray(new Item[list.size()]);
        Item[] items2 = list2.toArray(new Item[list2.size()]);


        //before
        Assert.assertEquals(Arrays.asList(items).toString(), Arrays.asList(items2).toString());

        GildedRose gildedRose = new GildedRose(items);
        GildedRose2 gildedRose2 = new GildedRose2(items2);

        gildedRose.updateQuality();
        gildedRose2.updateQuality();

        //after
        Assert.assertEquals(Arrays.asList(items).toString(), Arrays.asList(items2).toString());

    }

}
