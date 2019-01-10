package com.gildedrose;

public class Brie extends Product {

    public Brie(Item item) {
        super(item);
    }

    @Override
    protected void updateItem() {
        this.increaseQuality();
        if (this.item.sellIn < 0) {
            this.increaseQuality();
        }
    }
}
