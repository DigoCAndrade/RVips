package rodrigo.rvips.apis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.bukkit.configuration.file.FileConfiguration;

import rodrigo.rvips.Main;

public class MetodosKeys {
	
	private static FileConfiguration configkeys = Main.instance.keyf;
	
	public static void salvarKeys() {
		Main.logTransacoes("As keys foram salvas com sucesso.");
		
		for (String keyvip : Main.vipkeys.keySet()) {
			configkeys.set("cache.vip." + keyvip, Main.vipkeys.get(keyvip));
			Main.instance.saveKeys();
		}

		for (String keyvipm : Main.vipmkeys.keySet()) {
			configkeys.set("cache.vip+." + keyvipm, Main.vipmkeys.get(keyvipm));
			Main.instance.saveKeys();
		}

		for (String keymvp : Main.mvpkeys.keySet()) {
			configkeys.set("cache.mvp." + keymvp, Main.mvpkeys.get(keymvp));
			Main.instance.saveKeys();
		}

		for (String keymvpm : Main.mvpmkeys.keySet()) {
			configkeys.set("cache.mvp+." + keymvpm, Main.mvpmkeys.get(keymvpm));
			Main.instance.saveKeys();
		}
	}
	
	public static void carregarKeys() {
		Main.logTransacoes("As keys foram carregadas com sucesso.");
		if (configkeys.getConfigurationSection("cache.vip") != null) {
			for (String key : configkeys.getConfigurationSection("cache.vip").getKeys(false)) {
				Main.vipkeys.put(key.toUpperCase(), configkeys.getInt("cache.vip." + key.toUpperCase()));
				Main.instance.saveKeys();
			}
		}

		if (configkeys.getConfigurationSection("cache.vip+") != null) {
			for (String key : configkeys.getConfigurationSection("cache.vip+").getKeys(false)) {
				Main.vipmkeys.put(key, configkeys.getInt("cache.vip+." + key));
				Main.instance.saveKeys();
			}
		}

		if (configkeys.getConfigurationSection("cache.mvp") != null) {
			for (String key : configkeys.getConfigurationSection("cache.mvp").getKeys(false)) {
				Main.mvpkeys.put(key, configkeys.getInt("cache.mvp." + key));
				Main.instance.saveKeys();
			}
		}

		if (configkeys.getConfigurationSection("cache.mvp+") != null) {
			for (String key : configkeys.getConfigurationSection("cache.mvp+").getKeys(false)) {
				Main.mvpmkeys.put(key, configkeys.getInt("cache.mvp+." + key));
				Main.instance.saveKeys();
			}
		}
	}
	
	public static boolean hasKey(String key) {
		key = key.toUpperCase();
		if (Main.vipkeys.containsKey(key) || Main.vipmkeys.containsKey(key) || Main.mvpkeys.containsKey(key) || Main.mvpmkeys.containsKey(key)) {
			if (Main.ofensivelogs == true) Main.logTransacoes("O método hasKey retornou a key " + key + " como 'true'");
			return true;
		} else {
			if (Main.ofensivelogs == true) Main.logTransacoes("O método hasKey retornou a key " + key + " como 'false'");
			return false;
		}
	}
	
	public static String getKey(String key) {
		key = key.toUpperCase();
		if (Main.mvpmkeys.containsKey(key)) {
			if (Main.ofensivelogs == true) Main.logTransacoes("O método getKey retornou a key " + key + " como 'MVP+'");
			return "MVP+";
		}else if (Main.mvpkeys.containsKey(key)) {
			if (Main.ofensivelogs == true) Main.logTransacoes("O método getKey retornou a key " + key + " como 'MVP'");
			return "MVP";
		}else if (Main.vipmkeys.containsKey(key)) {
			if (Main.ofensivelogs == true) Main.logTransacoes("O método getKey retornou a key " + key + " como 'VIP+'");
			return "VIP+";
		}else if (Main.vipkeys.containsKey(key)) {
			if (Main.ofensivelogs == true) Main.logTransacoes("O método getKey retornou a key " + key + " como 'VIP'");
			return "VIP";
		}
		if (Main.ofensivelogs == true) Main.logTransacoes("O método getKey retornou a key " + key + " como 'null'");
		return null;
	}
	
	public static Integer getTempoKey(String key, String VIP) {
		Integer tempo = 0;
		key = key.toUpperCase();
		switch (VIP.toUpperCase()) {
		case "MVP+":
			tempo = Main.mvpmkeys.get(key);
			break;
		case "MVP":
			tempo = Main.mvpkeys.get(key);
			break;
		case "VIP+":
			tempo = Main.vipmkeys.get(key);
			break;
		case "VIP":
			tempo = Main.vipkeys.get(key);
			break;
		}
		
		if (Main.ofensivelogs == true) Main.logTransacoes("O método getTempoKey retornou o tempo da key " + key + " como '" + tempo + "'");
		return tempo;
	}
	
	public static void gerarKeyVIP(String VIP, int dias, String key) {
		Main.logTransacoes("Um processo de geração de Key foi iniciado...");
		if (hasKey(key)) {
			Main.logTransacoes("A key " + key + " já foi criada, gerando outra key...");
			gerarKeyVIP(VIP, dias, key);
			return;
		}
		switch (VIP.toUpperCase()) {
		case "MVP+":
			Main.mvpmkeys.put(key, dias);
			break;
		case "MVP":
			Main.mvpkeys.put(key, dias);
			break;
		case "VIP+":
			Main.vipmkeys.put(key, dias);
			break;
		case "VIP":
			Main.vipkeys.put(key, dias);
			break;
		}
		if (Main.ofensivelogs == true) Main.logTransacoes("A key " + key + " do " + VIP + " de " + dias + " dias foi criada.");
	}
	
	@SuppressWarnings("deprecation")
	public static void removerKeyVIP(String key) {
		if (!hasKey(key)) {
			if (Main.ofensivelogs == true) Main.logTransacoes("O método removerKeyVIP não encontrou a key " + key + ".");
			return;
		}
		Date keyd = new Date(MetodosKeys.getTempoKey(key, MetodosKeys.getKey(key)));
		switch (getKey(key)) {
		case "MVP+":
			if (Main.ofensivelogs == true) Main.logTransacoes("A key " + key + " do MVP+ de " + keyd.getDay() + " dias foi removida.");
			Main.mvpmkeys.remove(key);
			configkeys.set("cache.mvp+." + key, null);
			Main.instance.saveKeys();
			break;
		case "MVP":
			if (Main.ofensivelogs == true) Main.logTransacoes("A key " + key + " do MVP de " + keyd.getDay() + " dias foi removida.");
			Main.mvpkeys.remove(key);
			configkeys.set("cache.mvp." + key, null);
			Main.instance.saveKeys();
			break;
		case "VIP+":
			if (Main.ofensivelogs == true) Main.logTransacoes("A key " + key + " do VIP+ de " + keyd.getDay() + " dias foi removida.");
			Main.vipmkeys.remove(key);
			configkeys.set("cache.vip+." + key, null);
			Main.instance.saveKeys();
			break;
		case "VIP":
			if (Main.ofensivelogs == true) Main.logTransacoes("A key " + key + " do VIP de " + keyd.getDay() + " dias foi removida.");
			Main.vipkeys.remove(key);
			configkeys.set("cache.vip." + key, null);
			Main.instance.saveKeys();
			break;
		}
	}
	
	public static void editarKeyVIP(String key, int dias) {
		if (!hasKey(key)) {
			if (Main.ofensivelogs == true) Main.logTransacoes("O método editarKeyVIP não encontrou a key " + key + ".");
			return;
		}
		int diasatual = MetodosKeys.getTempoKey(key, MetodosKeys.getKey(key));
		switch (getKey(key)) {
		case "MVP+":
			if (Main.ofensivelogs == true) Main.logTransacoes("A key " + key + " do MVP+ de " + diasatual + " dias foi alterada para " + dias + " dias.");
			Main.mvpmkeys.put(key, dias);
			break;
		case "MVP":
			if (Main.ofensivelogs == true) Main.logTransacoes("A key " + key + " do MVP de " + diasatual + " dias foi alterada para " + dias + " dias.");
			Main.mvpkeys.put(key, dias);
			break;
		case "VIP+":
			if (Main.ofensivelogs == true) Main.logTransacoes("A key " + key + " do VIP+ de " + diasatual + " dias foi alterada para " + dias + " dias.");
			Main.vipmkeys.put(key, dias);
			break;
		case "VIP":
			if (Main.ofensivelogs == true) Main.logTransacoes("A key " + key + " do VIP de " + diasatual + " dias foi alterada para " + dias + " dias.");
			Main.vipkeys.put(key, dias);
			break;
		}
	}
	
	public static List<String> verKeys() {
		ArrayList<String> keys = new ArrayList<String>();
		keys.add("");
		keys.add(" §eLista de Keys §7(Atualiza Imediatamente)");
		keys.add("");
		int i = 0;
		if (!Main.mvpmkeys.isEmpty()) {
			for (String key : Main.mvpmkeys.keySet()) {
				i++;
				keys.add("   §f" + i + "º §e" + key + " §f- §bMVP§6+ §7(" + getTempoKey(key, "MVP+") + " Dias)");
			}
		}
		
		if (!Main.mvpkeys.isEmpty()) {
			for (String key : Main.mvpkeys.keySet()) {
				i++;
				keys.add("   §f" + i + "º §e" + key + " §f- §bMVP §7(" + getTempoKey(key, "MVP") + " Dias)");
			}
		}
		
		if (!Main.vipmkeys.isEmpty()) {
			for (String key : Main.vipmkeys.keySet()) {
				i++;
				keys.add("   §f" + i + "º §e" + key + " §f- §6VIP§b+ §7(" + getTempoKey(key, "VIP+") + " Dias)");
			}
		}
		
		if (!Main.vipkeys.isEmpty()) {
			for (String key : Main.vipkeys.keySet()) {
				i++;
				keys.add("   §f" + i + "º §e" + key + " §f- §6VIP §7(" + getTempoKey(key, "VIP") + " Dias)");
			}
		}
		keys.add("");
		return keys;
	}
	
	static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    static String[] splittedChars = chars.split("");
    private static String createArgs() {
        String key = "";
        for(int i = 0; i < 4; i++) {
            int random = new Random().nextInt(chars.length());
            key += splittedChars[random];
        }
        return key;
    }
    
    public static String generateKey() {
        return createArgs() + "-" + createArgs() + "-" + createArgs() + "-" + createArgs();
    }
}
