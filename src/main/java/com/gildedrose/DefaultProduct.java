package com.gildedrose;

public class DefaultProduct extends Product {
    public DefaultProduct(Item item) {
        super(item);
    }

    @Override
    protected void updateItem() {
        this.decreaseQuality();
        if (this.item.sellIn < 0) {
            this.decreaseQuality();
        }
    }
}
