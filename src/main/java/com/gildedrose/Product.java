package com.gildedrose;

public class Product {
    public Item item;

    public Product(Item item) {
        this.item = item;
    }

    protected void updateItem() {

    }

    public void decreaseQuality() {
        if  (item.quality > 0) {
            item.quality--;
        }
    }

    public void increaseQuality() {
        if (item.quality < 50) {
            item.quality++;
        }
    }
}
