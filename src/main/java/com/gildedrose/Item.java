package com.gildedrose;

public class Item {

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    public int methodChoice() {
        String name = this.name;
        int answer = 0;
        if (name.equals("Sulfuras, Hand of Ragnaros")) { answer = 1; }
        if (name.equals("Backstage passes to a TAFKAL80ETC concert")) { answer = 2; }
        if (name.contains("Aged Brie") && !name.contains("New")) { answer = 3; }
        if (name.startsWith("Conjured")) { answer = 4; }
        if (name.equals("Aging Red Wine")) {answer = 5;}
        return answer;
    }

    public void increaseQuality(int adding) {
        this.quality += adding;
        if (this.quality > 50) {
            this.quality = 50;
        }
    }

    public void decreaseQuality(int diff) {
        this.quality -= diff;
        if (this.quality < 0) {
            this.quality = 0;
        }
    }
}