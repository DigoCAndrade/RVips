package rodrigo.rvips;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import rodrigo.rvips.apis.MetodosKeys;
import rodrigo.rvips.apis.MetodosVIP;
import rodrigo.rvips.comandos.AtivarKey;
import rodrigo.rvips.comandos.DarVIP;
import rodrigo.rvips.comandos.EditarKey;
import rodrigo.rvips.comandos.GerarKey;
import rodrigo.rvips.comandos.RemoverKey;
import rodrigo.rvips.comandos.RemoverVIP;
import rodrigo.rvips.comandos.SetarVIP;
import rodrigo.rvips.comandos.TempoVIP;
import rodrigo.rvips.comandos.VerKeys;
import rodrigo.rvips.eventos.InventoryBlock;

public class Main extends JavaPlugin{
	
	public static Boolean ofensivelogs = false;
	public static Main instance;
	public static HashMap<String, Long> vip = new HashMap<>();
	public static HashMap<String, Long> vipm = new HashMap<>();
	public static HashMap<String, Long> mvp = new HashMap<>();
	public static HashMap<String, Long> mvpm = new HashMap<>();
	public static HashMap<String, Integer> vipkeys = new HashMap<>();
	public static HashMap<String, Integer> vipmkeys = new HashMap<>();
	public static HashMap<String, Integer> mvpkeys = new HashMap<>();
	public static HashMap<String, Integer> mvpmkeys = new HashMap<>();
	public File keysc = new File(getDataFolder(), "keys.yml");
	public FileConfiguration keyf = YamlConfiguration.loadConfiguration(keysc);
	
	public File vipsc = new File(getDataFolder(), "vips.yml");
	public FileConfiguration vipsf = YamlConfiguration.loadConfiguration(vipsc);
	
	@Override
	public void onEnable() {
		File transacoes = new File(getDataFolder(), "transacoes.txt");
		if (!transacoes.exists()) {
			try {
				transacoes.createNewFile();
			} catch (IOException e) {
				Bukkit.getConsoleSender().sendMessage("§c[RVIPs] Ocorreu um erro ao criar a §7transacoes.txt§c.");
				e.printStackTrace();
			}
		}
		instance = this;
		getCommand("darvip").setExecutor(new DarVIP());
		getCommand("removervip").setExecutor(new RemoverVIP());
		getCommand("tempovip").setExecutor(new TempoVIP());
		getCommand("gerarkey").setExecutor(new GerarKey());
		getCommand("ativarkey").setExecutor(new AtivarKey());
		getCommand("removerkey").setExecutor(new RemoverKey());
		getCommand("editarkey").setExecutor(new EditarKey());
		getCommand("setarvip").setExecutor(new SetarVIP());
		getCommand("verkeys").setExecutor(new VerKeys());
		Bukkit.getPluginManager().registerEvents(new InventoryBlock(), this);
		saveKeys();
		saveVips();
		MetodosVIP.carregarVIPs();
		MetodosKeys.carregarKeys();
		MetodosVIP.removerVIPs();
	}
	
	@Override
	public void onDisable() {
		MetodosVIP.salvarVIPs();
		MetodosKeys.salvarKeys();
	}
	
	public void saveKeys() {
		try {
			keyf.save(keysc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveVips() {
		try {
			vipsf.save(vipsc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void logTransacoes(String message){
        try {
            File saveTo = new File(Main.instance.getDataFolder(), "transacoes.txt");
            if (!saveTo.exists()) {
                saveTo.createNewFile();
            }
            FileWriter fw = new FileWriter(saveTo, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(getDate() + message);
            pw.flush();
            pw.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
	
	@SuppressWarnings("deprecation")
	private static String getDate() {
		Date now = new Date();
		now.setHours(now.getHours() - 3);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return "[" + format.format(now) + "] ";
	}
}
