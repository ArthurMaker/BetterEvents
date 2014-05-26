package me.ErezCS.BetterEvents;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class BetterEvents implements Listener {

	private static BetterEvents instance;
	ArrayList<Player> blockers = new ArrayList<Player>();

	private Plugin plugin;

	public static BetterEvents getBetterEvents() {
		if (instance == null)
			instance = new BetterEvents();
		return instance;
	}

	public void registerEvents(Listener listener, Plugin plugin) {
		Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerInteract(final PlayerInteractEvent e) {

		final Player p = e.getPlayer();
		if (e.getItem().getType() == Material.DIAMOND_SWORD || e.getItem().getType() == Material.IRON_SWORD || e.getItem().getType() == Material.STONE_SWORD || e.getItem().getType() == Material.WOOD_SWORD
				|| e.getItem().getType() == Material.GOLD_SWORD) {

			if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
				Bukkit.getServer().getPluginManager().callEvent(new PlayerSwordBlockEvent(p, e.getItem()));
				blockers.add(p);
			} else
				return;
			
			int i = 0;
			final int[] tasks = { i };
			tasks[0] = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

				@Override
				public void run() {
					if (!p.isBlocking()) {
						blockers.remove(p);
						Bukkit.getServer().getPluginManager().callEvent(new PlayerReleaseSwordBlockEvent(e.getPlayer(), e.getItem()));
						Bukkit.getScheduler().cancelTask(tasks[0]);
					}
				}
			}, 1, 1);

		}
		return;
	}

	@EventHandler
	private void onPlayerMove(PlayerMoveEvent e) {
		int fromX = (int) e.getFrom().getX();
		int fromY = (int) e.getFrom().getY();
		int fromZ = (int) e.getFrom().getZ();
		int toX = (int) e.getTo().getX();
		int toY = (int) e.getTo().getY();
		int toZ = (int) e.getTo().getZ();

		if (fromX != toX || fromZ != toZ || fromY != toY) {
			Bukkit.getServer().getPluginManager().callEvent(new PlayerBlockMoveEvent(e.getPlayer(), e.getFrom(), e.getTo()));
			return;
		}
	}

}
