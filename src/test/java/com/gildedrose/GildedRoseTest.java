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



}
