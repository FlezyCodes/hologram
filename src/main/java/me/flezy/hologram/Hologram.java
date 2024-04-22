package me.flezy.hologram;

import me.flezy.Manager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;

public class Hologram {
    private final Location location;
    private final String text;
    private ArmorStand armorStand;

    public Hologram(Location location, String text) {
        this.location = location;
        this.text = text;
    }
    public void spawn() {
        armorStand = location.getWorld().spawn(location, ArmorStand.class);
        armorStand.setCustomName(text);
        armorStand.setCustomNameVisible(true);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
    }
    public void createHologram(){

        Hologram hologram = new Hologram(Manager.config.getLocation("world"), "§aHolograma animado!");
        hologram.spawn();

    }

}
