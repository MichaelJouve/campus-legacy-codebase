package com.gildedrose;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED = "Conjured Mana Cake";
    public static final String WINE = "Aging Red Wine";
    Item[] items;

    private Logger logger = LoggerFactory.getLogger(GildedRose.class);

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public Item[] getItems() {
        return items;
    }

    public void updateQuality() {
        for (Item item : items) {
            int oldQuality = item.quality;
            int oldSellIn = item.sellIn;
            coreWork(item);
            generateLogs(oldQuality, oldSellIn, item);
        }
    }

    private void coreWork(Item item) {
        if (item.name.equals(SULFURAS)) {
            return;
        }
        item.sellIn--;
        String name = item.name;
        if (item.name.startsWith("Conjured")) {
            name = CONJURED;
        }
        Product product = buildProduct(item, name);
        product.updateItem();
    }

    private Product buildProduct(Item item, String name) {
        switch (name){
            case AGED_BRIE:
                return new Brie(item);
            case BACKSTAGE:
                return new Backstage(item);
            case CONJURED:
                return new Conjured(item);
            case WINE:
                return new Wine(item);
            default:
                return new DefaultProduct(item);
        }
    }

    private void generateLogs(int oldQuality, int oldSellIn, Item item) {
        logger.info("Name is : " + item.name + "\n" +
                "Old quality was : " + oldQuality + "\n" +
                "Old sellIn was : " + oldSellIn + "\n" +
                "New quality is : " + item.quality + "\n" +
                "new sellIn is : " + item.sellIn);
    }
}
