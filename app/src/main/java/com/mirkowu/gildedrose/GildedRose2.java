package com.mirkowu.gildedrose;


/**
 * @author: mirko
 * @date: 19-11-4
 */
class GildedRose2 {
    public static final String TAG = "GildedRose2";

    public static final String A = "Aged Brie";
    public static final String B = "Backstage passes to a TAFKAL80ETC concert";
    public static final String C = "Conjured";
    public static final String S = "Sulfuras, Hand of Ragnaros";


    Item[] items;

    public GildedRose2(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
//
//            if (!A.equals(item.name) && !B.equals(item.name)) {
//                if (!S.equals(item.name) && item.quality > 0) {
//                    item.quality -= 1;
//                }
//            } else if (item.quality < 50) {
//                item.quality += 1;
//                if (B.equals(item.name)) {
//                    if (item.sellIn < 11 && item.quality < 50) {
//                        item.quality += 1;
//                    }
//
//                    if (item.sellIn < 6 && item.quality < 50) {
//                        item.quality += 1;
//                    }
//                }
//            }
//
//            if (!S.equals(item.name)) {
//                item.sellIn -= 1;
//            }
//
//            if (item.sellIn < 0) {
//                if (!A.equals(item.name)) {
//                    if (!B.equals(item.name)
//                            && !S.equals(item.name)
//                            && item.quality > 0) {
//                        item.quality -= 1;
//                    }
//                } else if (item.quality < 50) {
//                    item.quality += 1;
//                }
//            }


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

            iGoods.updateSellInDaily(item);
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
        }


        @Override
        public void updateQualityDailyOther(Item item) {
            if (item.sellIn < 0) {
                item.quality -= 2;
            } else {
                item.quality -= 1;
            }
        }
    }

    class AGoods extends BaseGoods {
        @Override
        public void updateQualityDailyOther(Item item) {
            if (item.quality < 50) {
                item.quality += 1;
            }
        }
    }

    class BGoods extends AGoods {
        @Override
        public void updateQualityDailyOther(Item item) {
            super.updateQualityDailyOther(item);

            if (item.sellIn < 0) {
                item.quality = 0;
                return;
            }

            if (item.sellIn < 11) {
                super.updateQualityDailyOther(item);
            }

            if (item.sellIn < 6) {
                super.updateQualityDailyOther(item);
            }

        }
    }

    class CGoods extends BaseGoods {
        @Override
        public void updateQualityDailyOther(Item item) {
            super.updateQualityDailyOther(item);
            super.updateQualityDailyOther(item);
        }
    }

    class SGoods extends BaseGoods {
        @Override
        public void updateQualityDailyOther(Item item) {

        }
    }

}


