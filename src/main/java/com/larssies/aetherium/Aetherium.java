package com.larssies.aetherium;

import com.larssies.aetherium.events.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Aetherium extends JavaPlugin {

    private static Aetherium instance;

    @Override
    public void onEnable() {
        instance = this;

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        getLogger().info("Aetherium has successfully loaded!");

    }

    @Override
    public void onDisable() {
        getLogger().info("Aetherium has successfully unloaded!");
    }

    public static Aetherium getInstance() {
        return instance;
    }
}
