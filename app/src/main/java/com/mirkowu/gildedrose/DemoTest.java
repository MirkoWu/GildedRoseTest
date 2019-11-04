package com.mirkowu.gildedrose;

import java.util.ArrayList;
import java.util.Arrays;

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

        String[] names = new String[]{GildedRose2.A, GildedRose2.B, GildedRose2.S, "x"};
        int[] sellIns = new int[]{12, 11, 10, 7, 6, 5, 2, 1, 0, -1};
        int[] qualitys = new int[]{-1, 0, 1, 47, 48, 48, 50, 51};


        ArrayList<Item> list = new ArrayList<>();
        ArrayList<Item> list2 = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < sellIns.length; j++) {
                for (int k = 0; k < qualitys.length; k++) {
                    Item item = new Item(names[i], sellIns[j], qualitys[k]);
                    list.add(item);
                    list2.add(item);
                }
            }
        }
        Item[] items = list.toArray(new Item[list.size()]);
        Item[] items2 = list2.toArray(new Item[list2.size()]);
        test(items, items2);

    }

    public static void test(Item[] items, Item[] items2) {
        System.out.println(" test ");

        boolean isEqual = Arrays.asList(items).toString().equals(Arrays.asList(items2).toString());
        System.out.println(" before  isEqual = [" + isEqual + "]");

        GildedRose gildedRose = new GildedRose(items);
        GildedRose2 gildedRose2 = new GildedRose2(items2);

        gildedRose.updateQuality();
        gildedRose2.updateQuality();


        isEqual = Arrays.asList(items).toString().equals(Arrays.asList(items2).toString());
        System.out.println("after isEqual = [" + isEqual + "]");
        if (!isEqual) {
            System.out.println(" items= " + Arrays.asList(items).toString());
            System.out.println(" items2= " + Arrays.asList(items2).toString());
        }
        System.out.println(" finish ");

    }

}
