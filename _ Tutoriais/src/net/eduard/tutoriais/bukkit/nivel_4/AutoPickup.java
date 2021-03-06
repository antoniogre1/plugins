package net.eduard.tutoriais.bukkit.nivel_4;

import java.util.Collection;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoPickup implements Listener{

	@EventHandler
	 public void Quebrar(BlockBreakEvent e) {
	  Player p = e.getPlayer();
	  Collection<ItemStack> Lista = e.getBlock().getDrops(p.getItemInHand());
	        for(ItemStack item : Lista) {
	         p.getInventory().addItem(item);
	        }
	  new BukkitRunnable() {
	   
	   @Override
	   public void run() {
	    Collection<Entity> drops = e.getBlock().getLocation().getWorld().getNearbyEntities(e.getBlock().getLocation(), 1, 1, 1);
	   for(Entity entidade : drops) {
	    if(entidade instanceof org.bukkit.entity.Item) {
	     org.bukkit.entity.Item drop = (org.bukkit.entity.Item) entidade;
	     drop.setPickupDelay(300);
	     drop.remove();
	     
	    }
	   }
	    
	   }
	  }.runTaskLater(JavaPlugin.getProvidingPlugin(getClass()), 1);

	 }
}
