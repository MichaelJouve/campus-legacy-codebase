package com.gildedrose;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {this.items = items;}
    private Logger logger = LoggerFactory.getLogger(GildedRose.class);

    /**
     * Update products values
     */
    public void updateQuality() {
        for (Item item : items) {
            int oldQuality = item.quality;
            int oldSellIn = item.sellIn;
            item.buildAttributes(item); //Set product attributes
            item.increaseQuality(item);
            item.reduiceQuality(item);
            item.reduiceSellIn(item);
            item.CheckDepassment(item);
            generateLogs(oldQuality, oldSellIn, item);
        }
    }

    private void generateLogs(int oldQuality, int oldSellIn, Item item) {
        logger.info("Name is : " + item.name + "\n" +
                "Old quality was : " + oldQuality + "\n" +
                "Old sellIn was : " + oldSellIn + "\n" +
                "New quality is : " + item.quality + "\n" +
                "new sellIn is : " + item.sellIn);
    }

    public Item[] getItems() {
        return items;
    }
}