package com.mirkowu.gildedrose.origin;

import java.io.Serializable;

/**
 * @author: mirko
 * @date: 19-11-4
 */
public class Item implements Serializable {

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

}