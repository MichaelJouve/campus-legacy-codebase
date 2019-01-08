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
    private boolean atSellInZero = false;

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

    /**
     * Set the attributes of each products.
     * @param item
     * @return
     */
    public void buildAttributes( Item item) {
        switch (item.name) {
            case "Aged Brie": agedBrieSetAttributes(item); break;
            case "Sulfuras, Hand of Ragnaros": sulfurasSetAttributes(item);
                break;
            case "Backstage passes to a TAFKAL80ETC concert":
                backstageSetAttributes(item); break;
            case "Conjured Mana Cake": conjuredSetAttributes(item); break;
        }
    }

    public void increaseQuality(Item item) {
        item.quality = item.quality + item.increaseQuality;

        // increase at specific date before the end date (Map)
        if (!item.dateQuality.isEmpty()) {
            for(Map.Entry<Integer, Integer> entry: item.dateQuality.entrySet()){
                if (item.sellIn <= entry.getKey()) {
                    item.quality = item.quality + entry.getValue();
                }
            }
        }
    }

    public void reduiceQuality(Item item){
        item.quality = item.quality + item.reduiceQuality;
        // Set to zero the quality if it was negative
        if (item.quality < 0) {
            item.quality = 0;
        }
        // reduce at zero the quality if atSellInZero is true
        if (item.atSellInZero && item.sellIn < 0) {
            item.quality = 0;
        }
    }

    public void reduiceSellIn(Item item) {
        item.sellIn = item.sellIn + item.reduiceSellIn;
    }

    public void CheckDepassment(Item item) {
        if ("Sulfuras, Hand of Ragnaros".equals(item.name)) {
            if (item.quality > 80) {
                item.quality = 80;
            }
        }
        if(item.quality > 50 && !item.name.equals("Sulfuras, Hand of Ragnaros"))
        {
            item.quality = 50;
        }
    }

    private void agedBrieSetAttributes(Item item) {
        item.increaseQuality = 1;
        item.reduiceQuality = 0;
        item.reduiceSellIn = -1;
        item.dateQuality.put(0,1);
    }
    private void sulfurasSetAttributes(Item item){
        item.increaseQuality = 1;
        item.reduiceQuality = 0;
        item.reduiceSellIn = 0;
        item.dateQuality.clear();
    }

    private void backstageSetAttributes(Item item) {
        item.increaseQuality = 1;
        item.reduiceQuality = 0;
        item.reduiceSellIn = -1;
        item.atSellInZero = true;
        item.dateQuality.put(10,1);
        item.dateQuality.put(5,1);
    }

    private void conjuredSetAttributes(Item item) {
        item.increaseQuality = 0;
        item.reduiceQuality = -2;
        item.reduiceSellIn = -1;
        item.dateQuality.put(-1,-2);
    }

}
