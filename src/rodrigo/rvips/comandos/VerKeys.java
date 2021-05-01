package rodrigo.rvips.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import rodrigo.rvips.Main;
import rodrigo.rvips.apis.MetodosKeys;

public class VerKeys implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (!s.hasPermission("rvips.verkeys")) {
			s.sendMessage("§cApenas membros da equipe podem executar este comando.");
			return false;
		}
		
		if (Main.vipkeys.isEmpty() && Main.vipmkeys.isEmpty() && Main.mvpkeys.isEmpty() && Main.mvpmkeys.isEmpty()) {
			s.sendMessage("§cNenhuma key foi criada, portanto a lista esta vazia.");
			return false;
		}
		
		for (String top : MetodosKeys.verKeys()) {
			s.sendMessage(top);
		}
		
		if (Main.ofensivelogs == true) Main.logTransacoes(s.getName() + " exibiu todas as keys existentes.");
		return false;
	}
}
