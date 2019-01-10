package com.gildedrose;

public class Conjured extends Product {
    public Conjured(Item item) {
        super(item);
    }

    @Override
    protected void updateItem() {
        this.decreaseQuality();
        if (this.item.sellIn < 0) {
            this.decreaseQuality();
        }
        this.decreaseQuality();
        if (this.item.sellIn < 0) {
            this.decreaseQuality();
        }
    }
}
