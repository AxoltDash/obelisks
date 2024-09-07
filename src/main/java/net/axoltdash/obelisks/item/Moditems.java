package net.axoltdash.obelisks.item;

import net.axoltdash.obelisks.Obelisks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class Moditems {

    // public static final Item ZENITHITE = registerItem("zenithite", new Item(new Item.Settings()));
    public static final Item ZENITHITE = registerItem("zenithite", new ZenithiteItem(new Item.Settings()));

    public static Item registerItem(String name, Item item) {
        return Registry.register( Registries.ITEM, Identifier.of(Obelisks.MOD_ID, name), item);
    }

    public static void registerItems() {
        Obelisks.LOGGER.info("Registering Obelisk items... ("+ Obelisks.MOD_ID +")");
        
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {entries.add(ZENITHITE);});
    }
}