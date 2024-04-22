package me.flezy.hologram;

import me.flezy.Manager;
import me.flezy.executor.Setups;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

public class CustomHead {


    private final Location location;
    private ArmorStand armorStand;
    private float rotation = 0;
    public CustomHead(Location location) {
        this.location = location;
    }

    public void spawn() {
        armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setHelmet(new ItemStack(Material.SKULL_ITEM, 1, (short) 3));
        armorStand.setGravity(false);
        armorStand.setVisible(false);
        armorStand.setSmall(true);
        armorStand.setMarker(true);

        Setups.holograms.add(armorStand);
        Bukkit.getScheduler().runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("Hologram"), new Runnable() {
            @Override
            public void run() {

                armorStand.setHeadPose(new EulerAngle(0, Math.toRadians(rotation), 0));
                rotation += 1;
                if (rotation >= 360) {
                    rotation -= 360;
                }
            }
        }, 0L, 1L);
    }

}
