package com.gildedrose;

public class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public Item[] getItems() {
        return items;
    }

    public void updateQuality() {
        for (Item item : items) {
            item.decreaseQuality();
            item.sellIn--;
            if (item.sellIn < 0) {
                item.decreaseQuality();
            }
        }
    }
}
