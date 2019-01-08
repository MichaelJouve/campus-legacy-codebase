package com.gildedrose;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {this.items = items;}
    private Logger logger = LoggerFactory.getLogger(GildedRose.class);

    /**
     * Update quality and reduce sellIn.
     */
    public void updateQuality() {

            logger.info("Start updateQuality");

            // iterate each items
            for (int i = 0; i < items.length; i++) {

                //Build attributes before using them to change the item values.
                ItemAttributs itemAttributs = buildAttributs(items[i]);

                // functions who change the different value depending on the attributes
                increaseQuality(itemAttributs, i);
                reduiceQuality(itemAttributs, i);
                reduiceSellIn(itemAttributs, i);

                logger.info("End iteration {}", this.items[i]);
            }
    }

    // List the different attributes
    public class ItemAttributs {
        int reduiceSellIn =0;
        int reduiceQuality = 0;
        int increaseQuality = 0;

        // true if quality null after the SellIn date
        boolean atSellInZero = false;

        // map<nb days, to increase x quality>
        Map<Integer, Integer> dateQuality = new HashMap<>();
    }

    /**
     * Use to build the specifics attributs of each products.
     * Those attributs are used by the others functions to
     * @param item
     * @return
     */
    private ItemAttributs buildAttributs( Item item) {
        ItemAttributs itemAttributs = new ItemAttributs();

        itemAttributs.increaseQuality = 0;
        itemAttributs.reduiceQuality = 1;
        itemAttributs.reduiceSellIn = 1;
        itemAttributs.atSellInZero = false;
        itemAttributs.dateQuality.put(0,-1);

        switch (item.name) {
            case "Aged Brie":
                itemAttributs.increaseQuality = 1;
                itemAttributs.reduiceQuality = 0;
                itemAttributs.reduiceSellIn = 1;
                itemAttributs.dateQuality.put(0,1);
                break;
            case "Sulfuras, Hand of Ragnaros":
                itemAttributs.increaseQuality = 1;
                itemAttributs.reduiceQuality = 0;
                itemAttributs.reduiceSellIn = 0;
                itemAttributs.dateQuality.clear();
                break;
            case "Backstage passes to a TAFKAL80ETC concert":
                itemAttributs.increaseQuality = 1;
                itemAttributs.reduiceQuality = 0;
                itemAttributs.reduiceSellIn = 1;
                itemAttributs.atSellInZero = true;
                itemAttributs.dateQuality.put(10,1);
                itemAttributs.dateQuality.put(3,1);

                break;
            case "Conjured Mana Cake":
                itemAttributs.increaseQuality = 0;
                itemAttributs.reduiceQuality = 2;
                itemAttributs.reduiceSellIn = 1;
                itemAttributs.dateQuality.put(0,-2);
                break;
        }
        logger.info("fin buildAttributes {}", itemAttributs);
        return itemAttributs ;
    }

    private void reduiceQuality(ItemAttributs itemAttributs, int i){

        this.items[i].quality = this.items[i].quality - itemAttributs.reduiceQuality;

        // Set to zero the quality if it was negative
        if (this.items[i].quality < 0) {
            this.items[i].quality = 0;
        }
        // reduce at zero the quality if atSellInZero is true
        if (itemAttributs.atSellInZero && this.items[i].sellIn <= -1) {
            this.items[i].quality = 0;
        }
        logger.info("fin reduiceQuality {}", this.items[i]);
    }

    private void increaseQuality(ItemAttributs itemAttributs, int i) {
        this.items[i].quality = this.items[i].quality + itemAttributs.increaseQuality;

        // increase at specific date before the end date (Map)
        if (!itemAttributs.dateQuality.isEmpty()) {
           for(Map.Entry<Integer, Integer> entry: itemAttributs.dateQuality.entrySet()){
                if (this.items[i].sellIn <= entry.getKey()) {
                    this.items[i].quality = this.items[i].quality + entry.getValue();
                }
            }
        }

        switch (this.items[i].name) {
            case "Sulfuras, Hand of Ragnaros":
                if (this.items[i].quality > 80) {
                    this.items[i].quality = 80;
                }
                break;
        }

        if (this.items[i].quality > 50 && !this.items[i].name.contains("Sulfuras")) {
            this.items[i].quality = 50;
        }
        logger.info("fin increaseQuality {}", this.items[i]);
    }

    private void reduiceSellIn(ItemAttributs intemAttributs, int i) {
        this.items[i].sellIn = this.items[i].sellIn - intemAttributs.reduiceSellIn;
        logger.info("fin reduiceSellIn {}", this.items[i]);
    }

    public Item[] getItems() {
        return items;
    }
}