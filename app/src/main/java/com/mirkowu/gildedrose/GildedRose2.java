package com.mirkowu.gildedrose;


/**
 * @author: mirko
 * @date: 19-11-4
 */
class GildedRose2 {
    public static final String TAG = "GildedRose2";

    public static final String A = "Aged Brie";
    public static final String B = "Backstage passes to a TAFKAL80ETC concert";
    public static final String S = "Sulfuras, Hand of Ragnaros";


    Item[] items;

    public GildedRose2(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];

            if (!A.equals(item.name) && !B.equals(item.name)) {
                if (!S.equals(item.name) && item.quality > 0) {
                    item.quality -= 1;
                }
            } else if (item.quality < 50) {
                item.quality += 1;
                if (B.equals(item.name)) {
                    if (item.sellIn < 11 && item.quality < 50) {
                        item.quality += 1;
                    }

                    if (item.sellIn < 6 && item.quality < 50) {
                        item.quality += 1;
                    }
                }
            }

            if (!S.equals(item.name)) {
                item.sellIn -= 1;
            }

            if (item.sellIn < 0) {
                if (!A.equals(item.name)) {
                    if (!B.equals(item.name)
                            && !S.equals(item.name)
                            && item.quality > 0) {
                        item.quality -= 1;
                    }
                } else if (item.quality < 50) {
                    item.quality += 1;
                }
            }
        }
    }


}