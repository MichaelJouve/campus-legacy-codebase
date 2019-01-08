package com.gildedrose;

import java.util.HashMap;
import java.util.Map;

public class Item {

    public String name;

    public int sellIn;

    public int quality;

    // attributes who defined product comportment during time.
    public int increaseQuality = 0;
    public int reduiceQuality = -1;
    public int reduiceSellIn = -1;

    // true if quality null after the SellIn date
    boolean atSellInZero = false;

    // At this sellIn we increase or reduce quality by x
    // map<SellIn (integer), increase x quality>
    public Map<Integer, Integer> dateQuality = new HashMap<>();


    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.dateQuality.put(-1,-1);
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}
