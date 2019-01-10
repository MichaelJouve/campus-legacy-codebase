package com.gildedrose;

public class Wine extends Product {
    public Wine(Item item) {
        super(item);
    }

    @Override
    protected void updateItem() {
        if (this.item.sellIn < 0 && this.item.sellIn >= -100) {
            this.increaseQuality();
        }
        if (this.item.sellIn < -100) {
            this.decreaseQuality();
        }
    }
}
