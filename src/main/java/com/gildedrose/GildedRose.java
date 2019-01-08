package com.gildedrose;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {this.items = items;}
    private Logger logger = LoggerFactory.getLogger(GildedRose.class);

    /**
     * Update products values
     */
    public void updateQuality() {

        // iterate each items
        for (int i = 0; i < items.length; i++) {
            int oldQuality = items[i].quality;
            int oldSellIn = items[i].sellIn;

            //Build attributes before using them to change the item values.
            buildAttributs(items[i]);

            // functions who change the different value depending on the attributes
            increaseQuality(items[i]);
            reduiceQuality(items[i]);
            reduiceSellIn(items[i]);

            logger.info("Name is : " + items[i].name + "\n" +
                    "Old quality was : " + oldQuality + "\n" +
                    "Old sellIn was : " + oldSellIn + "\n" +
                    "New quality is : " + items[i].quality + "\n" +
                    "new sellIn is : " + items[i].sellIn
            );
        }
    }

    /**
     * Use to set the attributes of each products.
     * @param item
     * @return
     */
    private void buildAttributs( Item item) {

        switch (item.name) {
            case "Aged Brie":
                item.increaseQuality = 1;
                item.reduiceQuality = 0;
                item.reduiceSellIn = -1;
                item.dateQuality.put(0,1);
                break;
            case "Sulfuras, Hand of Ragnaros":
                item.increaseQuality = 1;
                item.reduiceQuality = 0;
                item.reduiceSellIn = 0;
                item.dateQuality.clear();
                break;
            case "Backstage passes to a TAFKAL80ETC concert":
                item.increaseQuality = 1;
                item.reduiceQuality = 0;
                item.reduiceSellIn = -1;
                item.atSellInZero = true;
                item.dateQuality.put(10,1);
                item.dateQuality.put(3,1);

                break;
            case "Conjured Mana Cake":
                item.increaseQuality = 0;
                item.reduiceQuality = -2;
                item.reduiceSellIn = -1;
                item.dateQuality.put(-1,-2);
                break;
        }
    }

    private void increaseQuality(Item item) {
        item.quality = item.quality + item.increaseQuality;

        // increase at specific date before the end date (Map)
        if (!item.dateQuality.isEmpty()) {
            for(Map.Entry<Integer, Integer> entry: item.dateQuality.entrySet()){
                if (item.sellIn <= entry.getKey()) {
                    item.quality = item.quality + entry.getValue();
                }
            }
        }

        switch (item.name) {
            case "Sulfuras, Hand of Ragnaros":
                if (item.quality > 80) {
                    item.quality = 80;
                }
                break;
        }
        if (item.quality > 50 && !item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.quality = 50;
        }
    }

    private void reduiceQuality(Item item){

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

    private void reduiceSellIn(Item item) {
        item.sellIn = item.sellIn + item.reduiceSellIn;
    }

    public Item[] getItems() {
        return items;
    }
}