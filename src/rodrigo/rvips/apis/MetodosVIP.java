package rodrigo.rvips.apis;

import java.util.Date;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import rodrigo.rvips.Main;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class MetodosVIP {

	public static HashMap<Integer, Integer> comemoracao = new HashMap<Integer, Integer>();
	private static FileConfiguration vipsconfig = Main.instance.vipsf;

	public static void salvarVIPs() {
		Main.logTransacoes("Os vips foram salvos com sucesso.");
		for (String player : Main.vip.keySet()) {
			vipsconfig.set("cache.vip." + player, Main.vip.get(player));
			Main.instance.saveVips();
		}

		for (String player : Main.vipm.keySet()) {
			vipsconfig.set("cache.vip+." + player, Main.vipm.get(player));
			Main.instance.saveVips();
		}

		for (String player : Main.mvp.keySet()) {
			vipsconfig.set("cache.mvp." + player, Main.mvp.get(player));
			Main.instance.saveVips();
		}

		for (String player : Main.mvpm.keySet()) {
			vipsconfig.set("cache.mvp+." + player, Main.mvpm.get(player));
			Main.instance.saveVips();
		}
	}

	public static void carregarVIPs() {
		Main.logTransacoes("Os vips foram carregados com sucesso.");
		if (vipsconfig.getConfigurationSection("cache.vip") != null) {
			for (String player : vipsconfig.getConfigurationSection("cache.vip").getKeys(false)) {
				Main.vip.put(player, vipsconfig.getLong("cache.vip." + player));
				Main.instance.saveVips();
			}
		}

		if (vipsconfig.getConfigurationSection("cache.vip+") != null) {
			for (String player : vipsconfig.getConfigurationSection("cache.vip+").getKeys(false)) {
				Main.vipm.put(player, vipsconfig.getLong("cache.vip+." + player));
				Main.instance.saveVips();
			}
		}

		if (vipsconfig.getConfigurationSection("cache.mvp") != null) {
			for (String player : vipsconfig.getConfigurationSection("cache.mvp").getKeys(false)) {
				Main.mvp.put(player, vipsconfig.getLong("cache.mvp." + player));
				Main.instance.saveVips();
			}
		}

		if (vipsconfig.getConfigurationSection("cache.mvp+") != null) {
			for (String player : vipsconfig.getConfigurationSection("cache.mvp+").getKeys(false)) {
				Main.mvpm.put(player, vipsconfig.getLong("cache.mvp+." + player));
				Main.instance.saveVips();
			}
		}
	}

	public static boolean hasVIP(String VIP, String player) {
		player = player.toUpperCase();
		if (VIP.equalsIgnoreCase("VIP")) {
			if (Main.vip.containsKey(player)) {
				if (Main.ofensivelogs == true) Main.logTransacoes("O método hasVIP do VIP retornou o valor 'true'.");
				return true;
			} else {
				if (Main.ofensivelogs == true) Main.logTransacoes("O método hasVIP do VIP retornou o valor 'false'.");
				return false;
			}
		} else if (VIP.equalsIgnoreCase("VIP+")) {
			if (Main.vipm.containsKey(player)) {
				if (Main.ofensivelogs == true) Main.logTransacoes("O método hasVIP do VIP+ retornou o valor 'true'.");
				return true;
			} else {
				if (Main.ofensivelogs == true) Main.logTransacoes("O método hasVIP do VIP+ retornou o valor 'false'.");
				return false;
			}
		} else if (VIP.equalsIgnoreCase("MVP")) {
			if (Main.mvp.containsKey(player)) {
				if (Main.ofensivelogs == true) Main.logTransacoes("O método hasVIP do MVP retornou o valor 'true'.");
				return true;
			} else {
				Main.logTransacoes("O método hasVIP do MVP retornou o valor 'false'.");
				return false;
			}
		} else if (VIP.equalsIgnoreCase("MVP+")) {
			if (Main.mvpm.containsKey(player)) {
				if (Main.ofensivelogs == true) Main.logTransacoes("O método hasVIP do MVP+ retornou o valor 'true'.");
				return true;
			} else {
				if (Main.ofensivelogs == true) Main.logTransacoes("O método hasVIP do MVP+ retornou o valor 'false'.");
				return false;
			}
		} else {
			if (Main.ofensivelogs == true) Main.logTransacoes("O método hasVIP não encontrou o VIP portanto retornou o valor 'false'.");
			return false;
		}
	}

	public static boolean expirouVIP(String VIP, String player) {
		player = player.toUpperCase();
		if (VIP.equalsIgnoreCase("VIP")) {
			Date vencimento = new Date(Main.vip.get(player));
			Date atual = new Date();
			if (atual.after(vencimento)) {
				if (Main.ofensivelogs == true) Main.logTransacoes("O método expirouVIP do VIP retornou o valor 'true'.");
				return true;
			} else {
				if (Main.ofensivelogs == true) Main.logTransacoes("O método expirouVIP do VIP retornou o valor 'false'.");
				return false;
			}
		} else if (VIP.equalsIgnoreCase("VIP+")) {
			Date vencimento = new Date(Main.vipm.get(player));
			Date atual = new Date();
			if (atual.after(vencimento)) {
				if (Main.ofensivelogs == true) Main.logTransacoes("O método expirouVIP do VIP+ retornou o valor 'true'.");
				return true;
			} else {
				if (Main.ofensivelogs == true) Main.logTransacoes("O método expirouVIP do VIP+ retornou o valor 'false'.");
				return false;
			}
		} else if (VIP.equalsIgnoreCase("MVP")) {
			Date vencimento = new Date(Main.mvp.get(player));
			Date atual = new Date();
			if (atual.after(vencimento)) {
				if (Main.ofensivelogs == true) Main.logTransacoes("O método expirouVIP do MVP retornou o valor 'true'.");
				return true;
			} else {
				if (Main.ofensivelogs == true) Main.logTransacoes("O método expirouVIP do MVP retornou o valor 'false'.");
				return false;
			}
		} else if (VIP.equalsIgnoreCase("MVP+")) {
			Date vencimento = new Date(Main.mvpm.get(player));
			Date atual = new Date();
			if (atual.after(vencimento)) {
				if (Main.ofensivelogs == true) Main.logTransacoes("O método expirouVIP do MVP+ retornou o valor 'true'.");
				return true;
			} else {
				if (Main.ofensivelogs == true) Main.logTransacoes("O método expirouVIP do MVP+ retornou o valor 'false'.");
				return false;
			}
		} else {
			if (Main.ofensivelogs == true) Main.logTransacoes("O método expirouVIP não encontrou o VIP portanto retornou 'false'.");
			return false;
		}
	}

	public static void removerVIPs() {
		new BukkitRunnable() {

			@Override
			public void run() {
				for (String player : Main.vip.keySet()) {
					if (expirouVIP("VIP", player)) {
						Main.logTransacoes("O VIP do jogador " + player + " expirou.");
						PermissionsEx.getUser(player).removeGroup("VIP");
						Main.vip.remove(player);
						vipsconfig.set("cache.vip." + player, null);
						Main.instance.saveVips();
						Player p = Bukkit.getPlayer(player);
						if (p != null) {
							p.sendMessage("§cSeu VIP expirou, acesse §eloja.redewise.com §cpara adquirir um novo.");
						}
					}
				}

				for (String player : Main.vipm.keySet()) {
					if (expirouVIP("VIP+", player)) {
						Main.logTransacoes("O VIP+ do jogador " + player + " expirou.");
						PermissionsEx.getUser(player).removeGroup("VIP+");
						Main.vipm.remove(player);
						vipsconfig.set("cache.vip+." + player, null);
						Main.instance.saveVips();
						Player p = Bukkit.getPlayer(player);
						if (p != null) {
							p.sendMessage("§cSeu VIP expirou, acesse §eloja.redewise.com §cpara adquirir um novo.");
						}
					}
				}

				for (String player : Main.mvp.keySet()) {
					if (expirouVIP("MVP", player)) {
						Main.logTransacoes("O MVP do jogador " + player + " expirou.");
						PermissionsEx.getUser(player).removeGroup("MVP");
						Main.mvp.remove(player);
						vipsconfig.set("cache.mvp." + player, null);
						Main.instance.saveVips();
						Player p = Bukkit.getPlayer(player);
						if (p != null) {
							p.sendMessage("§cSeu VIP expirou, acesse §eloja.redewise.com §cpara adquirir um novo.");
						}
					}
				}

				for (String player : Main.mvpm.keySet()) {
					if (expirouVIP("MVP+", player)) {
						Main.logTransacoes("O MVP+ do jogador " + player + " expirou.");
						PermissionsEx.getUser(player).removeGroup("MVP+");
						Main.mvpm.remove(player);
						vipsconfig.set("cache.mvp+." + player, null);
						Main.instance.saveVips();
						Player p = Bukkit.getPlayer(player);
						if (p != null) {
							p.sendMessage("§cSeu VIP expirou, acesse §eloja.redewise.com §cpara adquirir um novo.");
						}
					}
				}
			}
		}.runTaskTimerAsynchronously(Main.getPlugin(Main.class), 0, 20 * 60 * 5);
	}

	public static long getTempoVIP(String VIP, String player) {
		player = player.toUpperCase();
		if (VIP.equalsIgnoreCase("VIP")) {
			Date vencimento = new Date(Main.vip.get(player));
			if (Main.ofensivelogs == true) Main.logTransacoes("O método getTempoVIP retornou o valor '" + vencimento.getTime() + "'");
			return vencimento.getTime();
		} else if (VIP.equalsIgnoreCase("VIP+")) {
			Date vencimento = new Date(Main.vipm.get(player));
			if (Main.ofensivelogs == true) Main.logTransacoes("O método getTempoVIP retornou o valor '" + vencimento.getTime() + "'");
			return vencimento.getTime();
		} else if (VIP.equalsIgnoreCase("MVP")) {
			Date vencimento = new Date(Main.mvp.get(player));
			if (Main.ofensivelogs == true) Main.logTransacoes("O método getTempoVIP retornou o valor '" + vencimento.getTime() + "'");
			return vencimento.getTime();
		} else if (VIP.equalsIgnoreCase("MVP+")) {
			Date vencimento = new Date(Main.mvpm.get(player));
			if (Main.ofensivelogs == true) Main.logTransacoes("O método getTempoVIP retornou o valor '" + vencimento.getTime() + "'");
			return vencimento.getTime();
		} else {
			if (Main.ofensivelogs == true) Main.logTransacoes("O método getTempoVIP não encontrou o VIP portanto retornou o valor '" + 0 + "'");
			return 0;
		}
	}

	public static void adicionarVIP(String VIP, String player, Long tempo) {
		String playeru = player.toUpperCase();
		switch (VIP.toUpperCase()) {
		case "MVP+":
			if (Main.ofensivelogs == true) Main.logTransacoes("O método adicionarVIP adicionou o " + VIP + " para o jogador " + player + ".");
			comemorarVIP("MVP§6+", "§b", player);
			if (hasVIP("MVP+", playeru)) {
				Date vencimento = new Date(Main.mvpm.get(playeru));
				Date atual = new Date();

				long variacao = vencimento.getTime() - atual.getTime();

				Main.mvpm.put(playeru, variacao + tempo);
				PermissionsEx.getUser(player).addGroup("MVP+");
			} else {
				Main.mvpm.put(playeru, tempo);
				PermissionsEx.getUser(player).addGroup("MVP+");
			}
			break;
		case "MVP":
			if (Main.ofensivelogs == true) Main.logTransacoes("O método adicionarVIP adicionou o " + VIP + " para o jogador " + player + ".");
			comemorarVIP("MVP", "§b", player);
			if (hasVIP("MVP", playeru)) {
				Date vencimento = new Date(Main.mvp.get(playeru));
				Date atual = new Date();

				long variacao = vencimento.getTime() - atual.getTime();

				Main.mvp.put(playeru, variacao + tempo);
				PermissionsEx.getUser(player).addGroup("MVP");
			} else {
				Main.mvp.put(playeru, tempo);
				PermissionsEx.getUser(player).addGroup("MVP");
			}
			break;
		case "VIP+":
			if (Main.ofensivelogs == true) Main.logTransacoes("O método adicionarVIP adicionou o " + VIP + " para o jogador " + player + ".");
			comemorarVIP("VIP§b+", "§6", player);
			if (hasVIP("VIP+", playeru)) {
				Date vencimento = new Date(Main.vipm.get(playeru));
				Date atual = new Date();

				long variacao = vencimento.getTime() - atual.getTime();

				Main.vipm.put(playeru, variacao + tempo);
				PermissionsEx.getUser(player).addGroup("VIP+");
			} else {
				Main.vipm.put(playeru, tempo);
				PermissionsEx.getUser(player).addGroup("VIP+");
			}
			break;
		case "VIP":
			if (Main.ofensivelogs == true) Main.logTransacoes("O método adicionarVIP adicionou o " + VIP + " para o jogador " + player + ".");
			comemorarVIP("VIP", "§6", player);
			if (hasVIP("VIP", playeru)) {
				Date vencimento = new Date(Main.vip.get(playeru));
				Date atual = new Date();

				long variacao = vencimento.getTime() - atual.getTime();

				Main.vip.put(playeru, variacao + tempo);
				PermissionsEx.getUser(player).addGroup("VIP");
			} else {
				Main.vip.put(playeru, tempo);
				PermissionsEx.getUser(player).addGroup("VIP");
			}
			break;
		}
	}

	public static void setarVIP(String VIP, String player, Long tempo) {
		String playeru = player.toUpperCase();
		if (!hasVIP("VIP", playeru) && !hasVIP("VIP+", playeru) && !hasVIP("MVP", playeru) && !hasVIP("MVP+", playeru)) {
			adicionarVIP(VIP, player, tempo);
			return;
		} else {
			if (VIP.equalsIgnoreCase("VIP")) {
				if (Main.ofensivelogs == true) Main.logTransacoes("O método setarVIP setou o " + VIP + " para o jogador " + player + ".");
				Main.vip.put(playeru, tempo);
				PermissionsEx.getUser(player).addGroup("VIP");
			} else if (VIP.equalsIgnoreCase("VIP+")) {
				if (Main.ofensivelogs == true) Main.logTransacoes("O método setarVIP setou o " + VIP + " para o jogador " + player + ".");
				Main.vipm.put(playeru, tempo);
				PermissionsEx.getUser(player).addGroup("VIP+");
			} else if (VIP.equalsIgnoreCase("MVP")) {
				if (Main.ofensivelogs == true) Main.logTransacoes("O método setarVIP setou o " + VIP + " para o jogador " + player + ".");
				Main.mvp.put(playeru, tempo);
				PermissionsEx.getUser(player).addGroup("MVP");
			} else if (VIP.equalsIgnoreCase("MVP+")) {
				if (Main.ofensivelogs == true) Main.logTransacoes("O método setarVIP setou o " + VIP + " para o jogador " + player + ".");
				Main.mvpm.put(playeru, tempo);
				PermissionsEx.getUser(player).addGroup("MVP+");
			}
		}
	}

	public static void removerVIP(String VIP, String player) {
		player = player.toUpperCase();
		if (VIP.equalsIgnoreCase("VIP")) {
			if (Main.ofensivelogs == true) Main.logTransacoes("O método removerVIP removeu o " + VIP + " do o jogador " + player + ".");
			PermissionsEx.getUser(player).removeGroup("VIP");
			Main.vip.remove(player);
			vipsconfig.set("cache.vip." + player, null);
			Main.instance.saveVips();
		} else if (VIP.equalsIgnoreCase("VIP+")) {
			if (Main.ofensivelogs == true) Main.logTransacoes("O método removerVIP removeu o " + VIP + " do o jogador " + player + ".");
			PermissionsEx.getUser(player).removeGroup("VIP+");
			Main.vipm.remove(player);
			vipsconfig.set("cache.vip+." + player, null);
			Main.instance.saveVips();
		} else if (VIP.equalsIgnoreCase("MVP")) {
			if (Main.ofensivelogs == true) Main.logTransacoes("O método removerVIP removeu o " + VIP + " do o jogador " + player + ".");
			PermissionsEx.getUser(player).removeGroup("MVP");
			Main.mvp.remove(player);
			vipsconfig.set("cache.mvp." + player, null);
			Main.instance.saveVips();
		} else if (VIP.equalsIgnoreCase("MVP+")) {
			if (Main.ofensivelogs == true) Main.logTransacoes("O método removerVIP removeu o " + VIP + " do o jogador " + player + ".");
			PermissionsEx.getUser(player).removeGroup("MVP+");
			Main.mvpm.remove(player);
			vipsconfig.set("cache.mvp+." + player, null);
			Main.instance.saveVips();
		}
	}

	static int x = 8;
	static int contagem = 5;
	static int task = 0;

	@SuppressWarnings("deprecation")
	public static void comemorarVIP(String VIP, String cor, String player) {
		Bukkit.getScheduler().cancelTask(task);
		if (Main.ofensivelogs == true) Main.logTransacoes("Uma comemoração do " + VIP + " do jogador " + player + " foi iniciada.");
		x = 8;
		contagem = 5;
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
			@Override
			public void run() {
				if (x == 1) {
					Bukkit.broadcastMessage("");
					Bukkit.broadcastMessage(" §e§l[NOVO VIP] §r" + cor + player + " §ftornou-se " + cor + VIP + "§f.");
					Bukkit.broadcastMessage("");
				}
				for (Player pl : Bukkit.getOnlinePlayers()) {
					pl.resetTitle();
					String color = "§c";
					if (x == 8 || x == 7) {
						pl.sendTitle(color + contagem, "");
						pl.playSound(pl.getLocation(), Sound.LEVEL_UP, 1F, 10F);
					} else if (x == 6 || x == 5) {
						color = "§e";
						pl.sendTitle(color + contagem, "");
						pl.playSound(pl.getLocation(), Sound.LEVEL_UP, 1F, 7F);
					} else if (x == 4 || x == 3) {
						color = "§a";
						pl.sendTitle(color + contagem, "");
						pl.playSound(pl.getLocation(), Sound.LEVEL_UP, 1F, 5F);
					} else if (x == 2) {
						pl.resetTitle();
						pl.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 49, 9));
						pl.playSound(pl.getLocation(), Sound.WITHER_SPAWN, 10F, 1F);
					} else if (x == 1) {
						pl.sendTitle(cor + player, "§fTornou-se " + cor + "[" + VIP + cor + "]");
						pl.playSound(pl.getLocation(), Sound.ENDERDRAGON_GROWL, 10F, 1F);
						comemoracao.remove(1);
						Bukkit.getScheduler().cancelTask(task);
					}
				}
				x--;
				contagem--;
			}
		}, 0, 20);
	}
}
