package me.flezy;

import me.flezy.configuration.Config;
import me.flezy.executor.Setups;
import me.flezy.hologram.listener.Join;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Manager extends JavaPlugin {


    public static Manager instance;
    public static Config config;

    @Override
    public void onEnable() {
        config = new Config(this);
        instance = this;
        register();
    }

    @Override
    public void onDisable() {

    }

    void register() {
        getCommand("holo").setExecutor(new Setups());
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new Join(), this);
    }
}
