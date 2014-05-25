

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;

public class PlayerBlockMoveEvent extends PlayerEvent implements Listener{

	private static final HandlerList handlers = new HandlerList();
	private Location from, to;
	
	public PlayerBlockMoveEvent(Player p, Location from, Location to) {
		super(p);
		this.from = from;
		this.to = to;
		}
	
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}

	public Location getFrom() {
		return from;
	}

	public Location getTo() {
		return to;
	}

	public void setTo(Location to) {
		this.to = to;
	}

}
