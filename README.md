# Espresso
Espresso is mainly used for my projects, but you can use it too!
It's a simple library that provides many hooks and functions for minecraft projects.

## Installation
To install Espresso, you need to add the following to your `pom.xml` file:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.flrping</groupId>
        <artifactId>Espresso</artifactId>
        <version>Tag</version>
    </dependency>
</dependencies>
```
## Functions
Espresso provides many functions for you to use in your plugin. These include:
- Loot Tables and Loot classes to build your own loot tables.
- Conditionals to link to loot tables or other classes.
- Integrations with other plugins, with providers, to easily hook into a plugin for support.
- Configuration classes to easily create and manage your plugin's configuration.
- Message builder to distribute messages to players or locations.
- Many different options of storage to use.
- Various utility classes to help with your plugin's development.

## Plugin Hook List & Usage
Most of these hook categories include a Provider class. This can be used in your plugin
to set a getter for a class of the provider. This is useful to allow your users to select
which plugin they wish to use primarily for your plugin functions. 

For example if you want to include multiple economies in your plugin, you can do the following:
```java
public class HookManager {
    
    public EconomyProvider economyProvider;
    
    public EconomyProvider getEconomyProvider() {
        return economyProvider;
    }
    
    // Make sure to include enable checks.
    public void setEconomyProvider() {
        String economySetting = plugin.getConfig().getString("economy-type");
        EconomyType economyType = EconomyType.getByName(economySetting);
        switch (economyType) {
            case VAULT:
                economyProvider = new VaultProvider();
                break;
            case PLAYER_POINTS:
                economyProvider = new PlayerPointsProvider();
                break;
            case TOKEN_MANAGER:
                economyProvider = new TokenManagerProvider();
                break;
            default:
                // handle whatever here.
        }
    }
}
```
If your plugin is more complex, it is recommended you use a dependency injection 
framework like [Guice](https://github.com/google/guice) or [Spring](https://spring.io/projects/spring-framework).

In another class you can reference this provider and use it to get the economy.

```java
public class MoneyOnJoin {

    private final Plugin plugin;

    public MoneyOnJoin(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Some plugins need to have an account created before you can deposit.
        // It may throw an exception the player does not have an account.
        if(!plugin.getHookManager().getEconomyProvider().hasAccount(player)) 
            plugin.getHookManager().getEconomyProvider().createAccount(player);
        
        // Deposit 1000 to the player.
        // Done!
        plugin.getHookManager().getEconomyProvider().deposit(player, 1000);
    }
}
```

The current plugins it adds hooks for are:
- Economies
  - Vault
  - PlayerPoints
  - TokenManager [3.2.8+]
- Entities
  - ItemsAdder
  - MythicMobs
  - InfernalMobs
  - LevelledMobs
- Holograms
  - DecentHolograms
- Items
  - ItemsAdder
  - Oraxen
  - MMOItems
  - Nexo
- Blocks
  - ItemsAdder
  - Oraxen
  - Nexo
- Spawners
  - EpicSpawners
  - UltimateStacker [3.0.0+]
  - RoseStacker
  - WildStacker
  - UpgradeableSpawners
- Stackers
  - WildStacker
  - StackMob 5
  - UltimateStacker [3.0.0+]
  - RoseStacker


