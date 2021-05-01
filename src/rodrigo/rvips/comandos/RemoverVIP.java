package rodrigo.rvips.comandos;

import java.util.Date;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import rodrigo.rvips.Main;
import rodrigo.rvips.apis.MetodosVIP;

public class RemoverVIP implements CommandExecutor{
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (!s.hasPermission("rvips.removervip")) {
			s.sendMessage("§cApenas membros da equipe podem executar este comando.");
			return false;
		}
		
		if (args.length < 2) {
			s.sendMessage("§cSintaxe incorreta, use /removervip <jogador> <vip>.");
			return false;
		}
		
		String player = args[0];
		
		if (!args[1].equalsIgnoreCase("VIP") && !args[1].equalsIgnoreCase("VIP+") && !args[1].equalsIgnoreCase("MVP") && !args[1].equalsIgnoreCase("MVP+")) {
			s.sendMessage("§cEste VIP não foi encontrado.");
			return false;
		}
		
		if (!MetodosVIP.hasVIP(args[1].toUpperCase(), player)) {
			s.sendMessage("§cO jogador §7" + player + " §cnão possui este vip.");
			return false;
		}
		
		String vipcolor = "";
		if (args[1].equalsIgnoreCase("VIP")) vipcolor = "§6VIP";
		if (args[1].equalsIgnoreCase("VIP+")) vipcolor = "§6VIP§b+";
		if (args[1].equalsIgnoreCase("MVP")) vipcolor = "§bMVP";
		if (args[1].equalsIgnoreCase("MVP+")) vipcolor = "§bMVP§6+";
		
		Date vip = new Date(MetodosVIP.getTempoVIP(args[1].toUpperCase(), player.toUpperCase()));
		Main.logTransacoes(s.getName() + " removeu o " + args[1].toUpperCase() + " de " + vip.getDay() + " dias de " + args[1] + ".");
		MetodosVIP.removerVIP(args[1].toUpperCase(), player);
		s.sendMessage("§aSucesso! O " + vipcolor + " §ade " + player + " §afoi removido.");
		return false;
	}
}
