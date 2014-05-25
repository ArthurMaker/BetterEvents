
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerSwordBlockEvent extends PlayerEvent {

	ItemStack item;
	
	public PlayerSwordBlockEvent(Player p, ItemStack item) {
		super(p);
		this.item = item;
	}

	private static final HandlerList handlers = new HandlerList();
	
	public ItemStack getSword(){
		return item;
	}
	
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}
}
