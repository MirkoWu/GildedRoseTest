package com.mirkowu.gildedrose.refactor;


import com.mirkowu.gildedrose.origin.Item;

/**
 * @author: mirko
 * @date: 19-11-4
 */
public class GildedRoseRefactor {
    public static final String TAG = "GildedRoseRefactor";

    public static final String A = "Aged Brie";
    public static final String B = "Backstage passes to a TAFKAL80ETC concert";
    public static final String C = "Conjured";
    public static final String S = "Sulfuras, Hand of Ragnaros";


    Item[] items;

    public GildedRoseRefactor(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];

            IGoods iGoods = null;
            switch (item.name) {
                case A:
                    iGoods = new AGoods();
                    break;
                case B:
                    iGoods = new BGoods();
                    break;
                case C:
                    iGoods = new CGoods();
                    break;
                case S:
                    iGoods = new SGoods();
                    break;
                default:
                    iGoods = new BaseGoods();
            }

            iGoods.updateQualityDailyAll(item);
        }


    }


    interface IGoods {
        void updateSellInDaily(Item item);

        void updateQualityDailyAll(Item item);

        void updateQualityDailyOther(Item item);
    }

    class BaseGoods implements IGoods {


        @Override
        public void updateSellInDaily(Item item) {
            item.sellIn -= 1;
        }

        @Override
        public void updateQualityDailyAll(Item item) {
            updateQualityDailyOther(item);

            if (item.quality < 0) item.quality = 0;
            if (item.quality > 50) item.quality = 50;

            updateSellInDaily(item);

            if (item.quality > 0 && item.sellIn < 0) {
                item.quality -= 1;
            }
        }


        @Override
        public void updateQualityDailyOther(Item item) {
            if (item.quality > 0) {
                item.quality -= 1;
            }
        }
    }

    class AGoods extends BaseGoods {
        @Override
        public void updateQualityDailyAll(Item item) {
            updateQualityDailyOther(item);

            if (item.quality < 0) item.quality = 0;
            if (item.quality > 50) item.quality = 50;

        }

        @Override
        public void updateQualityDailyOther(Item item) {
            if (item.quality < 50) {
                item.quality += 1;
            }

            updateSellInDaily(item);

            //这里有点疑问，源代码那里是 sellIn<0 还有再加一次，文档那里没说
            if (item.sellIn < 0 && item.quality < 50) {
                item.quality += 1;
            }

        }
    }

    /**
     * B商品的疑惑，sellIn  竟然不是一开始就减少的，而是当品质增减完一波才开始计算的
     */
    class BGoods extends BaseGoods {
        @Override
        public void updateQualityDailyAll(Item item) {
            updateQualityDailyOther(item);
            if (item.quality < 0) item.quality = 0;
            if (item.quality > 50) item.quality = 50;

        }

        @Override
        public void updateQualityDailyOther(Item item) {

            if (item.quality < 50) {
                item.quality += 1;
            }

            if (item.sellIn < 11) {
                item.quality += 1;
            }

            if (item.sellIn < 6) {
                item.quality += 1;
            }

            updateSellInDaily(item);

            if (item.sellIn < 0) {
                item.quality = 0;
            }
        }
    }


    class SGoods extends BaseGoods {
        @Override
        public void updateQualityDailyAll(Item item) {

        }

    }

    class CGoods extends BaseGoods {

    }
}


