package rodrigo.rvips.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import rodrigo.rvips.Main;
import rodrigo.rvips.apis.MetodosKeys;

public class RemoverKey implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (!s.hasPermission("rvips.removerkey")) {
			s.sendMessage("§cApenas membros da equipe podem executar este comando.");
			return false;
		}
		
		if (args.length < 1) {
			s.sendMessage("§cSintaxe incorreta, use /removerkey <key>.");
			return false;
		}
		
		String key = args[0].toUpperCase();
		if (!MetodosKeys.hasKey(key)) {
			s.sendMessage("§cEsta key não foi encontrada.");
			return false;
		}
		
		Integer keyd = MetodosKeys.getTempoKey(key, MetodosKeys.getKey(key));
		Main.logTransacoes(s.getName() + " removeu a key " + key + " do " + MetodosKeys.getKey(key) + " de " + keyd + " dias.");
		MetodosKeys.removerKeyVIP(key);
		s.sendMessage("§aSucesso! Você removeu a key §7" + key + "§a.");
		return false;
	}

}
