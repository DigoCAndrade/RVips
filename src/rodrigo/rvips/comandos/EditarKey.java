package rodrigo.rvips.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import rodrigo.rvips.Main;
import rodrigo.rvips.apis.MetodosKeys;

public class EditarKey implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (!s.hasPermission("rvips.editarkey")) {
			s.sendMessage("§cApenas membros da equipe podem executar este comando.");
			return false;
		}
		
		if (args.length < 2) {
			s.sendMessage("§cSintaxe incorreta, use /editarkey <key> <dias>.");
			return false;
		}
		
		String key = args[0];
		if (!MetodosKeys.hasKey(key)) {
			s.sendMessage("§cA key §7" + key + " §cnão foi encontrada.");
			return false;
		}
		
		int dias = 0;
		try {
			dias = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			s.sendMessage("§cO número de dias não é um número válido.");
			return false;
		}
		
		if (dias < 1) {
			s.sendMessage("§cO número de dias deve ser maior que 0.");
			return false;
		}

		int keyd = MetodosKeys.getTempoKey(key, MetodosKeys.getKey(key));
		Main.logTransacoes(s.getName() + " editou a key " + key + " de " + keyd + " para " + dias + " dias. (" + MetodosKeys.getKey(key) + ")");
		s.sendMessage("§aSucesso! Você editou a key §7" + key + " §apara o valor de §7" + dias + " §adias.");
		MetodosKeys.editarKeyVIP(key, dias);
		return false;
	}

}
