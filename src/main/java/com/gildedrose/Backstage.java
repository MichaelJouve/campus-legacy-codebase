package com.gildedrose;

public class Backstage extends Product {
    public Backstage(Item item) {
        super(item);
    }

    @Override
    protected void updateItem() {
        if (this.item.sellIn < 0 ) {
            this.item.quality = 0;
            return;
        }
        this.increaseQuality();
        if (this.item.sellIn < 10) {
            this.increaseQuality();
        }
        if (this.item.sellIn < 5) {
            this.increaseQuality();
        }
    }
}
