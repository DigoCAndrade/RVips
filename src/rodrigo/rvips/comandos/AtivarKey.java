package rodrigo.rvips.comandos;

import java.util.Date;

import org.bukkit.command.Command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rodrigo.rvips.Main;
import rodrigo.rvips.apis.MetodosKeys;
import rodrigo.rvips.apis.MetodosVIP;

public class AtivarKey implements CommandExecutor{
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (!(s instanceof Player)) {
			s.sendMessage("§cApenas jogadores podem executar este comando.");
			return true;
		}
		
		Player p = (Player) s;
		
		if (args.length < 1) {
			p.sendMessage("§cSintaxe incorreta, use /ativarkey <key>.");
			return true;
		}
		
		String key = args[0];
		
		if (!MetodosKeys.hasKey(key)) {
			p.sendMessage("§cA key §7" + key + " §cnão foi encontrada.");
			return true;
		}
		
		Date tempo = new Date();
		tempo.setHours(MetodosKeys.getTempoKey(key, MetodosKeys.getKey(key)) * 24);
		MetodosVIP.adicionarVIP(MetodosKeys.getKey(key), p.getName(), tempo.getTime());
		Main.logTransacoes(p.getName() + " ativou a key " + key + " do " + MetodosKeys.getKey(key) + " (" + MetodosKeys.getTempoKey(key, MetodosKeys.getKey(key)) + " Dias)");
		MetodosKeys.removerKeyVIP(key);
		return false;
	}
}
