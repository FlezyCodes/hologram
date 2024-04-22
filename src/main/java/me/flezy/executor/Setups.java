package me.flezy.executor;

import me.flezy.Manager;
import me.flezy.hologram.CustomHead;
import me.flezy.hologram.Hologram;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Setups implements CommandExecutor {

    public static List<Entity> headHologrm = new ArrayList<>();
    public static List<ArmorStand> holograms = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return false;

        Player player = (((Player) commandSender).getPlayer());
        if (player.hasPermission("hologram.setup")) {
            if (strings.length == 0) {
                player.sendMessage("§aUso correto /holo <hologram>");
                return false;
            }
            if (strings[0].equalsIgnoreCase("world")) {
                Manager.config.setLocation(player.getLocation(), strings[0]);
                Hologram hologram = new Hologram(Manager.config.getLocation(strings[0]), "§aHolograma animado.");
                CustomHead head = new CustomHead(player.getLocation());
                head.spawn();
                hologram.createHologram();
                player.sendMessage("§aHolograma spawnado:");
                return false;
            }
            if (strings[0].equalsIgnoreCase("clear")) {
                int removed = clearHolograms();
                player.sendMessage(removed + "§aForam removido");
                return false;
            }
        }
        return false;

    }

            // TODO - Methodo bugado.
//    private void spawnHead(Location location, String playerName) {
//        ArmorStand armorStand = (ArmorStand) location.getWorld().spawn(location, ArmorStand.class);
//        armorStand.setVisible(false);
//        armorStand.setGravity(false);
//
//
//        ItemStack playerHead = getPlayerHead(playerName);
//        armorStand.setHelmet(playerHead);
//    }
//
//    private ItemStack getPlayerHead(String playerName) {
//
//        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
//        SkullMeta meta = (SkullMeta) skull.getItemMeta();
//        meta.setOwner(playerName);
//        skull.setItemMeta(meta);
//        return skull;
//    }
//            private ItemStack getPlayerHeaddd(String playerName) {
//                ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
//                SkullMeta meta = (SkullMeta) skull.getItemMeta();
//                GameProfile profile = new GameProfile(UUID.randomUUID(), null);
//                profile.getProperties().put("textures", new Property("textures", getTextureValue(playerName)));
//                try {
//                    Field profileField = meta.getClass().getDeclaredField("profile");
//                    profileField.setAccessible(true);
//                    profileField.set(meta, profile);
//                } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//                skull.setItemMeta(meta);
//                return skull;
//            }


    private int clearHolograms() {
        int removed = 0;
        for (Entity entity : headHologrm) {
            entity.remove();
            removed++;
        }
        for (ArmorStand entity : holograms) {
            entity.remove();
            removed++;
        }
        holograms.clear();
        headHologrm.clear();
        return removed;
    }
}
