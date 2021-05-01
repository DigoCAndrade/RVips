package rodrigo.rvips.eventos;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryBlock implements Listener{
	
	@EventHandler
	public void aoClicar(InventoryClickEvent e) {
		if (e.getCurrentItem() == null) return;
		if (!(e.getWhoClicked() instanceof Player)) return;
		if (e.getView().getTitle().equalsIgnoreCase("Informações de seu VIP")) {
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			p.updateInventory();
		}
	}
}
