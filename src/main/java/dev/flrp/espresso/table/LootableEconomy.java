package dev.flrp.espresso.table;

import dev.flrp.espresso.hook.economy.EconomyProvider;

public interface LootableEconomy extends Lootable {

    EconomyProvider getEconomyProvider();

    void setEconomyProvider(EconomyProvider economyProvider);

}
