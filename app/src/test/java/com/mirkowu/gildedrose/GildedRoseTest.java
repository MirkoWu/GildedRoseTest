package com.mirkowu.gildedrose;

import com.mirkowu.gildedrose.origin.GildedRose;
import com.mirkowu.gildedrose.origin.Item;
import com.mirkowu.gildedrose.refactor.GildedRoseRefactor;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GildedRoseTest {

    @Test
    public void resultEqual() {

        String[] names = new String[]{GildedRoseRefactor.A, GildedRoseRefactor.B, GildedRoseRefactor.S, "Conjured"};
        int[] sellIns = new int[]{12, 11, 10, 7, 6, 5, 2, 1, 0,-1 };
        int[] qualities = new int[]{  0, 1, 47, 48, 49, 50};


        ArrayList<Item> list = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < sellIns.length; j++) {
                for (int k = 0; k < qualities.length; k++) {
                    Item item = new Item(names[i], sellIns[j], qualities[k]);
                    list.add(item);
                }
            }
        }

        ArrayList<Item> list2 = (ArrayList<Item>) deepCopy(list);
        Item[] items = list.toArray(new Item[list.size()]);
        Item[] items2 = list2.toArray(new Item[list2.size()]);


        //before
        Assert.assertEquals(Arrays.asList(items).toString(), Arrays.asList(items2).toString());

        GildedRose gildedRose = new GildedRose(items);
        GildedRoseRefactor gildedRose2 = new GildedRoseRefactor(items2);

        gildedRose.updateQuality();
        gildedRose2.updateQuality();

        //after
        Assert.assertEquals(Arrays.asList(items).toString(), Arrays.asList(items2).toString());


//        for (int i = 0; i < list.size(); i++) {
//            Assert.assertEquals(items[i].toString(),items2[i].toString());
//        }



    }

    public static List deepCopy(List src) {

        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            List dest = (List) in.readObject();
            return dest;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
