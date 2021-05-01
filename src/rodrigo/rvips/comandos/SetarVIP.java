package rodrigo.rvips.comandos;

import java.util.Date;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import rodrigo.rvips.Main;
import rodrigo.rvips.apis.MetodosVIP;

public class SetarVIP implements CommandExecutor{
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (!s.hasPermission("rvips.setarvip")) {
			s.sendMessage("§cApenas membros da equipe podem executar este comando.");
			return false;
		}
		
		if (args.length < 3) {
			s.sendMessage("§cSintaxe incorreta, use /setarvip <jogador> <vip> <dias>.");
			return false;
		}
		
		String player = args[0];
		
		if (!args[1].equalsIgnoreCase("VIP") && !args[1].equalsIgnoreCase("VIP+") && !args[1].equalsIgnoreCase("MVP") && !args[1].equalsIgnoreCase("MVP+")) {
			s.sendMessage("§cEste VIP não foi encontrado.");
			return false;
		}
		
		int dias = 0;
		try {
			dias = Integer.parseInt(args[2]);
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
		
		Date expira = new Date();
		int horas = dias * 24;
		expira.setHours(expira.getHours() + horas);
		
		Main.logTransacoes(s.getName() + " setou o " + args[1].toUpperCase() + " de " + dias + " dias para " + args[1] + ".");
		MetodosVIP.setarVIP(args[1].toUpperCase(), player, expira.getTime());
		s.sendMessage("§aSucesso! Você setou o " + vipcolor + " §ade §7" + dias + " §adias para §7" + player + "§a.");
		return false;
	}
}
