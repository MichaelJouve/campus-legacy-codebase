package com.gildedrose;
import com.google.common.collect.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GildedRose {
    // Item types
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED = "Conjured Mana Cake";
    public static final String WINE = "Aging Red Wine";

    // ranges all inclusive
    public static final Range<Integer> ZERO_INFINITY = Range.atLeast(0);
    public static final Range<Integer> INFINITY_TO_MINUS_ONE = Range.atMost(-1);
    public static final Range<Integer> ZERO_FOUR = Range.closed(0,4);
    public static final Range<Integer> FOUR_NINE = Range.closed(5,9);
    public static final Range<Integer> TEN_INFINITY = Range.atLeast(10);
    public static final Range<Integer> MINUS_HUNDRED_TO_MINUS_ONE = Range.closed(-100, -1);
    public static final Range<Integer> INFINITY_TO_MINUS_HUNDRED_AND_ONE = Range.atMost(-101);

    // rules all ranges all items
    public static final Rule DEFAULT_RULE_BY_ONE = new Rule(ZERO_INFINITY, item -> item.decreaseQuality(1));
    public static final Rule DEFAULT_RULE_BY_TWO = new Rule(INFINITY_TO_MINUS_ONE, item -> item.decreaseQuality(2));
    public static final Rule AGED_BRIE_ONE = new Rule(ZERO_INFINITY, item -> item.increaseQuality(1));
    public static final Rule AGED_BRIE_BY_TWO = new Rule(INFINITY_TO_MINUS_ONE, item -> item.increaseQuality(2));
    public static final Rule BACKSTAGE_RULE_BY_ONE = new Rule(TEN_INFINITY, item -> item.increaseQuality(1));
    public static final Rule BACKSTAGE_RULE_BY_TWO = new Rule(FOUR_NINE, item -> item.increaseQuality(2));
    public static final Rule BACKSTAGE_RULE_BY_THREE = new Rule(ZERO_FOUR, item -> item.increaseQuality(3));
    public static final Rule BACKSTAGE_RULE_SET_TO_ZERO = new Rule(INFINITY_TO_MINUS_ONE, item -> item.decreaseQuality(item.quality));
    public static final Rule CONJURED_RULE_BY_TWO = new Rule(ZERO_INFINITY, item -> item.decreaseQuality(2));
    public static final Rule CONJURED_RULE_BY_FOUR = new Rule(INFINITY_TO_MINUS_ONE, item -> item.decreaseQuality(4));
    public static final Rule WINE_RULE_UP_BY_ONE = new Rule(MINUS_HUNDRED_TO_MINUS_ONE, item -> item.increaseQuality(1));
    public static final Rule WINE_RULE_DOWN_BY_ONE = new Rule(INFINITY_TO_MINUS_HUNDRED_AND_ONE, item -> item.decreaseQuality(1));
    public static final Rule WINE_RULE_STABLE = new Rule(ZERO_INFINITY, item -> item.decreaseQuality(0));

    Item[] items;

    private Logger logger = LoggerFactory.getLogger(GildedRose.class);

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public Item[] getItems() {
        return items;
    }

    public void updateQuality() {
        for (Item item : items) {
            int oldQuality = item.quality;
            int oldSellIn = item.sellIn;
            coreWork(item);
            generateLogs(oldQuality, oldSellIn, item);
        }
    }

    private void coreWork(Item item) {
        Rules neededRules;
        List<Rule> tempList;
        if (item.name.equals(SULFURAS)) {
            return;
        }
        item.sellIn--;

        String name = item.name;
        if (item.name.startsWith("Conjured")) {
            name = CONJURED;
        }

        switch (name){
            case AGED_BRIE:
                neededRules = new Rules(AGED_BRIE_ONE, AGED_BRIE_BY_TWO);
                break;
            case BACKSTAGE:
                neededRules = new Rules(BACKSTAGE_RULE_BY_ONE, BACKSTAGE_RULE_BY_TWO, BACKSTAGE_RULE_BY_THREE, BACKSTAGE_RULE_SET_TO_ZERO);
                break;
            case CONJURED:
                neededRules = new Rules(CONJURED_RULE_BY_TWO, CONJURED_RULE_BY_FOUR);
                break;
            case WINE:
                neededRules = new Rules(WINE_RULE_UP_BY_ONE, WINE_RULE_DOWN_BY_ONE, WINE_RULE_STABLE);
                break;
            default:
                neededRules = new Rules(DEFAULT_RULE_BY_ONE, DEFAULT_RULE_BY_TWO);
        }
        neededRules.apply(item);
    }

    private void generateLogs(int oldQuality, int oldSellIn, Item item) {
        logger.info("Name is : " + item.name + "\n" +
                "Old quality was : " + oldQuality + "\n" +
                "Old sellIn was : " + oldSellIn + "\n" +
                "New quality is : " + item.quality + "\n" +
                "new sellIn is : " + item.sellIn);
    }
}
