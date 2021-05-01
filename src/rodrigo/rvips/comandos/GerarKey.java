package rodrigo.rvips.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import rodrigo.rvips.Main;
import rodrigo.rvips.apis.MetodosKeys;

public class GerarKey implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (!s.hasPermission("rvips.gerarkey")) {
			s.sendMessage("§cApenas membros da equipe podem executar este comando.");
			return false;
		}
		
		if (args.length < 2) {
			s.sendMessage("§cSintaxe incorreta, use /gerarkey <vip> <dias>.");
			return false;
		}
		
		if (!args[0].equalsIgnoreCase("VIP") && !args[0].equalsIgnoreCase("VIP+") && !args[0].equalsIgnoreCase("MVP") && !args[0].equalsIgnoreCase("MVP+")) {
			s.sendMessage("§cEste VIP não foi encontrado.");
			return false;
		}
		
		int dias = 0;
		try {
			dias = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			s.sendMessage("§cO número de dias não é valido.");
			return false;
		}
		
		if (dias < 1) {
			s.sendMessage("§cO número de dias deve ser maior que 0.");
			return false;
		}
		
		String vipcolor = "";
		if (args[0].equalsIgnoreCase("VIP")) vipcolor = "§6VIP";
		if (args[0].equalsIgnoreCase("VIP+")) vipcolor = "§6VIP§b+";
		if (args[0].equalsIgnoreCase("MVP")) vipcolor = "§bMVP";
		if (args[0].equalsIgnoreCase("MVP+")) vipcolor = "§bMVP§6+";
		String key = MetodosKeys.generateKey();
		MetodosKeys.gerarKeyVIP(args[0].toUpperCase(), dias, key);
		Main.logTransacoes(s.getName() + " gerou a key " + key + " do "  + args[0].toUpperCase() + " de " + dias + " dias.");
		s.sendMessage("§aSucesso! Você gerou a key §7" + key + " §ado " + vipcolor + "  §ade §7" + dias + " §adias.");
		return false;
	}

}
