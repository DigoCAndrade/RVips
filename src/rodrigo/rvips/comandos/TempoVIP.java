package rodrigo.rvips.comandos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import rodrigo.rvips.apis.ItemBuilder;
import rodrigo.rvips.apis.MetodosVIP;

public class TempoVIP implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String arg2, String[] arg3) {
		if (!(s instanceof Player)) {
			s.sendMessage("§cApenas jogadores podem executar este comando.");
			return true;
		}

		Player p = (Player) s;

		Inventory tempovip = Bukkit.createInventory(null, 3 * 9, "Informações de seu VIP");
		if (!MetodosVIP.hasVIP("VIP", p.getName()) && !MetodosVIP.hasVIP("VIP+", p.getName()) && !MetodosVIP.hasVIP("MVP", p.getName()) && !MetodosVIP.hasVIP("MVP+", p.getName())) {
			ItemStack nenhum = new ItemBuilder(Material.BARRIER).name("§cVocê não possui nenhum VIP.").build();
			tempovip.setItem(13, nenhum);
		}else {
			ArrayList<String> lore = new ArrayList<String>();
			if (MetodosVIP.hasVIP("MVP+", p.getName())) lore.add("§b[MVP§6+§b] §8- §7Expira em: §f" + formatDate(MetodosVIP.getTempoVIP("MVP+", p.getName())) + "   §7");
			if (MetodosVIP.hasVIP("MVP", p.getName())) lore.add("§b[MVP] §8- §7Expira em: §f" + formatDate(MetodosVIP.getTempoVIP("MVP", p.getName())) + "   §7");
			if (MetodosVIP.hasVIP("VIP+", p.getName())) lore.add("§6[VIP§b+§6] §8- §7Expira em: §f" + formatDate(MetodosVIP.getTempoVIP("VIP+", p.getName())) + "   §7");
			if (MetodosVIP.hasVIP("VIP", p.getName())) lore.add("§6[VIP] §8- §7Expira em: §f" + formatDate(MetodosVIP.getTempoVIP("VIP", p.getName())) + "   §7");
			ItemStack tempos = new ItemBuilder(Material.SKULL_ITEM, 1, (short) 3).name("§eTempo(s) VIP").owner(p.getName()).listLore(lore).build();
			tempovip.setItem(13, tempos);
		}

		p.openInventory(tempovip);
		return false;
	}

	@SuppressWarnings("deprecation")
	public String formatDate(long date) {
		Date now = new Date(date);
		now.setHours(now.getHours() - 3);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return format.format(now);
	}
}
