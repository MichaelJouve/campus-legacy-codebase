package com.gildedrose;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GildedRoseTest {

    @Test
    void shouldDecreaseItemQualityByOne() {
        Item item = new Item("toto", 5, 10);
        GildedRose gildedRose = new GildedRose( new Item[]{item} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(9);
    }

    @Test
    void shouldDecreaseItemSellInByOne() {
        Item item = new Item("toto", 5, 10);
        GildedRose gildedRose = new GildedRose( new Item[]{item} );
        gildedRose.updateQuality();
        assertThat(item.sellIn).isEqualTo(4);
    }

    @Test
    void qualityShouldNotBeNegative() {
        Item item = new Item("toto", 5, 0);
        GildedRose gildedRose = new GildedRose( new Item[]{item} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(0);
    }

    @Test
    void qualityShouldDecreaseDoubleWhenExpired() {
        Item item = new Item("toto", -1, 10);
        GildedRose gildedRose = new GildedRose( new Item[]{item} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(8);
    }

    @Test
    void shouldIncreaseQualityAgedBrie() {
        Item item = new Item("Aged Brie", 10, 10);
        GildedRose gildedRose = new GildedRose( new Item[]{item} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(11);
    }

    @Test
    void qualityShouldNeverBeMoreThanFifty() {
        Item item = new Item("Aged Brie", 10, 50);
        GildedRose gildedRose = new GildedRose( new Item[]{item} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(50);
    }

    @Test
    void shouldIncreaseTwiceAgedBrieQuality() {
        Item item = new Item("Aged Brie", -2, 30);
        GildedRose gildedRose = new GildedRose( new Item[]{item} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(32);
    }

    @Test
    void sulfuraShouldNeverChange() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 15, 30);
        GildedRose gildedRose = new GildedRose( new Item[]{item} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(30);
        assertThat(item.sellIn).isEqualTo(15);
    }

    @Test
    void backstageShouldIncreaseByOne() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 30);
        GildedRose gildedRose = new GildedRose( new Item[]{item} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(31);
    }

    @Test
    void backstageShouldIncreaseByTwo() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 8, 30);
        GildedRose gildedRose = new GildedRose( new Item[]{item} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(32);
    }

    @Test
    void backstageShouldIncreaseByThree() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 30);
        GildedRose gildedRose = new GildedRose( new Item[]{item} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(33);
    }

    @Test
    void backstageQualityDropsToZeroWhenExpired() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 30);
        GildedRose gildedRose = new GildedRose( new Item[]{item} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(0);
    }
}
