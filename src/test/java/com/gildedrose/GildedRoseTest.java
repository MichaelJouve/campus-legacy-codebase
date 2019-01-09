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
        assertThat(app.items[0].quality).isEqualTo(0);
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
        Item[] items = new Item[] { new Item("Conjured Mana Cake", -3, 16) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(12);
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
    void shouldUpgradeBackstageQuality3() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 6, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(13);
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
    void shouldQualityNeverNegative() {
        Item[] items = new Item[] { new Item("test", 6, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(0);
    }

    @Test
    void shouldDecreaseSellIn() {
        Item[] items = new Item[] { new Item("test", 6, 4) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].sellIn).isEqualTo(5);
    }

    @Test
    void shouldDecreaseQuality() {
        Item[] items = new Item[] { new Item("test", 6, 4) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(3);
    }

    @Test
    void shouldDecreaseTwiceQuality() {
        Item[] items = new Item[] { new Item("test", 0, 4) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(2);
    }

    @Test
    void shouldAgedBrieIncreaseQuality() {
        Item[] items = new Item[] { new Item("Aged Brie", 6, 4) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(5);
    }

    @Test
    void shouldAgedBrieIncreaseQualityTwice() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 4) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(6);
    }

    @Test
    void shouldQualityNeverHigher50() {
        Item[] items = new Item[] { new Item("Aged Brie", 6, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(50);
    }

    @Test
    void shouldSulfurasNeverChangeQuality() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 6, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(80);
    }

    @Test
    void shouldSulfurasNeverChangeSellIn() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 6, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].sellIn).isEqualTo(6);
    }

    @Test
    void shouldBackstageQualityIncreaseBy1() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 12, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(9);
    }

    @Test
    void shouldBackstageQualityIncreaseBy2() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 9, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(10);
    }

    @Test
    void shouldBackstageQualityIncreaseBy3() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 3, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(11);
    }

    @Test
    void shouldBackstageQualityDropTo0() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(0);
    }

    @Test
    void shouldConjuredQualityDecreaseTwice() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 3, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(6);
    }

    @Test
    void shouldConjuredQualityDecreaseBy4() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 0, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(4);
    }

    @Test
    void shouldLikeConjuredNotConjured() {
        Item[] items = new Item[] { new Item("Like Conjured But Cheaper Mana Cake", 5, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(7);
    }

    @Test
    void shouldLikeNewBrieNotWorkLikeBrie() {
        Item[] items = new Item[] { new Item("Brand New Brie", 5, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(7);
    }

    @Test
    void AgingTestSellPositive() {
        Item[] items = new Item[] { new Item("Aging Red Wine", 5, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(8);
    }

    @Test
    void AgingTestSellinZero() {
        Item[] items = new Item[] { new Item("Aging Red Wine", 0, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(9);
    }

    @Test
    void AgingTestNotAfter50() {
        Item[] items = new Item[] { new Item("Aging Red Wine", -2, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(50);
    }

    @Test
    void AgingTestNotAfterNegatif110() {
        Item[] items = new Item[] { new Item("Aging Red Wine", -110, 22) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(21);
    }
}
