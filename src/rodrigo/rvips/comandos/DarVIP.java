package rodrigo.rvips.comandos;

import java.util.Date;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import rodrigo.rvips.Main;
import rodrigo.rvips.apis.MetodosVIP;

public class DarVIP implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (!s.hasPermission("rvips.darvip")) {
			s.sendMessage("§cApenas membros da equipe podem executar este comando.");
			return false;
		}
		
		if (args.length < 3) {
			s.sendMessage("§cSintaxe incorreta, use /darvip <jogador> <vip> <dias>.");
			return false;
		}
		
		if (!args[1].equalsIgnoreCase("VIP") && !args[1].equalsIgnoreCase("VIP+") && !args[1].equalsIgnoreCase("MVP") && !args[1].equalsIgnoreCase("MVP+")) {
			s.sendMessage("§cEste VIP não foi encontrado.");
			return false;
		}

		String player = args[0];
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
		
		Main.logTransacoes(s.getName() + " deu um " + args[1].toUpperCase() +" de " + dias + " para " + player);
		Date expira = new Date();
		int horas = dias * 24;
		expira.setHours(expira.getHours() + horas);
		MetodosVIP.adicionarVIP(args[1].toUpperCase(), player, expira.getTime());
		return false;
	}

}
