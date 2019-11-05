package com.mirkowu.gildedrose;

import com.mirkowu.gildedrose.origin.GildedRose;
import com.mirkowu.gildedrose.origin.Item;
import com.mirkowu.gildedrose.refactor.GildedRoseRefactor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: mirko
 * @date: 19-11-4
 */
public class DemoTest {
    public static void main(String[] args) {
        startTest();
    }

    public static void startTest() {
        System.out.println(" startTest ");

        String[] names = new String[]{GildedRoseRefactor.A, GildedRoseRefactor.B, GildedRoseRefactor.S, "x"};
        int[] sellIns = new int[]{12, 11, 10, 7, 6, 5, 2, 1, 0, -1};
        int[] qualities = new int[]{0, 1, 47, 48, 49, 50};


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
        Item[] items2 = list2.toArray(new Item[list.size()]);


        test(items, items2);

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

    public static void test(Item[] items, Item[] items2) {
        System.out.println(" test ");

        boolean isEqual = Arrays.asList(items).toString().equals(Arrays.asList(items2).toString());
        System.out.println(" before  isEqual = [" + isEqual + "]");

        GildedRose gildedRose = new GildedRose(items);
        GildedRoseRefactor gildedRose2 = new GildedRoseRefactor(items2);

        gildedRose.updateQuality();
        gildedRose2.updateQuality();


        isEqual = Arrays.asList(items).toString().equals(Arrays.asList(items2).toString());
        System.out.println("after isEqual = [" + isEqual + "]");
        if (!isEqual) {
            System.out.println(" items= " + Arrays.asList(items).toString());
            System.out.println(" items2= " + Arrays.asList(items2).toString());


            for (int i = 0; i < items.length; i++) {
                isEqual = items[i].toString().equals(items2[i].toString());
                if (!isEqual) {
                    System.out.println(" items = " + items[i].toString() + "  " + items2[i].toString());
                }
            }
        }
        System.out.println(" finish ");

    }

}
