

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerLookAtBlockEvent extends PlayerEvent{


	private static final HandlerList handlers = new HandlerList();
	private Block block;
	
	public PlayerLookAtBlockEvent(Player player, Block block) {
		super(player);
		this.block = block;
	}
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}
	
	public Block getBlock(){
		return block;
	}
	
}
