package com.gildedrose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GildedRose {
    Item[] items;
    final Logger logger = LoggerFactory.getLogger(GildedRose.class);

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            int choice = item.methodChoice();
            if (choice != 1) {
                int quality = item.quality;
                item.sellIn--;
                boolean dayPast = item.sellIn < 0;
                String method = switchMethod(choice, item, dayPast);
                myLogger(item, method, quality);
            }
        }
    }

    public Item[] getItems() {
        return items;
    }

    private String defaultMethod(Item item, Boolean dayPast) {
        item.decreaseQuality(dayPast ? 2 : 1);
        return "Default method";
    }

    private String backstageMethod(Item item) {
        if (item.sellIn < 0) {
            item.quality = 0;
        } else {
            item.increaseQuality(1);
            if (item.sellIn < 11) { item.increaseQuality(1); }
            if (item.sellIn < 6) { item.increaseQuality(1); }
        }
        return "Backstage method";
    }

    private String brieMethod(Item item, Boolean dayPast) {
        item.increaseQuality(dayPast ? 2 : 1);
        return "Brie method";
    }

    private String conjuredMethod(Item item, Boolean dayPast) {
        item.decreaseQuality(dayPast ? 4 : 2);
        return "Conjured method";
    }

    private String agedRedWineMethode(Item item, Boolean dayPast){
        if (dayPast) {
            if (item.sellIn < -100) {
                item.decreaseQuality(1);
            } else {
                item.increaseQuality(1);
            }
        }
        return "Aging Red Wine Methode";
    }

    private String switchMethod(int choice, Item item, Boolean dayPast) {
        switch (choice) {
            case 0: return defaultMethod(item, dayPast);
            case 2: return backstageMethod(item);
            case 3: return brieMethod(item, dayPast);
            case 4: return conjuredMethod(item, dayPast);
            case 5: return agedRedWineMethode(item, dayPast);
            default: return "Error method";
        }
    }

    private void myLogger(Item item, String method, int oldQuality) {
        logger.debug("The name of the item is {} \n"
                        + "SellIn is {} \n"
                        + "Method is {} \n"
                        + "Old Quality was {}, new Quality is {}."
                , item.name, item.sellIn, method, oldQuality, item.quality);

    }

}
