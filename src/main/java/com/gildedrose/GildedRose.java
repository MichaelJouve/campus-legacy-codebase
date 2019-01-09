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
            coreWork(item);
        }
    }

    private void coreWork(Item item) {
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn--;
            if (item.name.equals("Aged Brie")) {
                item.agedBrieMethod();
            } else {
                item.defaultMethod();
            }
        }
    }

}
