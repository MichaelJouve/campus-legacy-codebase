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
            item.sellIn--;
            if (item.name.equals("Aged Brie")){
                item.quality++;
            }
            else {
                item.decreaseQuality();
                if (item.sellIn < 0) {
                    item.decreaseQuality();
                }
            }
        }
    }
}
