package net.eduard.tutoriais.kits;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

import net.eduard.api.lib.click.PlayerClick;
import net.eduard.api.lib.click.PlayerClickEffect;
import net.eduard.api.lib.game.KitAbility;

public class Bomber extends KitAbility {

	public Bomber() {
		setIcon(Material.TNT, "�fAtire bombas nos inimigos");
		add(Material.TNT);
		setClick(new PlayerClick(Material.TNT, new PlayerClickEffect() {
			
			@Override
			public void onClick(Player player, Block block, ItemStack item) {
				// TODO Auto-generated method stub
				if (hasKit(player)) {
					if (cooldown(player)) {
						TNTPrimed tnt = player.getWorld()
								.spawn(player.getLocation(), TNTPrimed.class);
						tnt.setVelocity(player.getEyeLocation().getDirection()
								.multiply(2.5D));
						tnt.setFuseTicks(40);
						player.getLocation().getWorld()
								.createExplosion(player.getLocation(), 4.0F);
					}
				}
			}
		}));
	}

	@EventHandler
	public void event(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (hasKit(p)) {
				if (e.getCause() == DamageCause.BLOCK_EXPLOSION) {
					e.setCancelled(true);
				}
			}

		}
	}

}
