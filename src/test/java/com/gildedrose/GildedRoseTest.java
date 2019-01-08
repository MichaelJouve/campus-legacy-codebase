package com.gildedrose;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GildedRoseTest {

    @Test
    void shouldAddItemWithNamefoo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].name).isEqualTo("foo");
    }

    @Test
    void shouldHaveBasicQualityComportment() {
        Item[] items = new Item[] { new Item("toto Conjured", 0, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(1);
    }

    @Test
    void shouldReduceConjuredQualityWithPositifSellIn() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 5, 9) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(7);
    }

    @Test
    void conjuredQualityShouldNotBeNegative() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 5, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(0);
    }

    @Test
    void conjuredQualityShouldNotBeNegative2() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", -5, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(1);
    }

    @Test
    void shouldReduceTwiceConjuredQuality() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", -1, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(6);
    }

    @Test
    void conjuredQualityShouldNotBeNegatif() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", -1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(0);
    }

    @Test
    void shouldReduceConjuredSellIn() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 5, 9) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].sellIn).isEqualTo(4);
    }

    @Test
    void shouldUpgradeBackstageQualityBy2() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 6, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(12);
    }

    @Test
    void shouldUpgradeBackstageQualityBy3() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 2, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(13);
    }

    @Test
    void shouldReduceBackstageQualityToZero() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", -1, 45) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(0);
    }

    @Test
    void shouldHaveQualityTo80() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", -1, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(80);
    }

    @Test
    void shouldHaveSameQuality() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(80);
    }

    @Test
    void shouldHaveSameSellIn() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].sellIn).isEqualTo(10);
    }

    @Test
    void QualityBackstageShoulNotBeOver50() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(50);
    }

    @Test
    void QualityBrieShoulNotBeOver50() {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(50);
    }

    @Test
    void shouldNotHaveSpecificIncreaseQuality() {
        Item[] items = new Item[] { new Item("Aged Brie toto", 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].increaseQuality).isEqualTo(0);
    }
}
