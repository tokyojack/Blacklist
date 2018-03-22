package me.tokyojack.mcmarket.blacklist.utils.Konfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unchecked")
public class Konfig {

	//NOTE, THIS IS A MODIFIED/TEMP VERSION OF MY KONFIG CLASS
	//It's kinda a mess at the moment, but I'm planning to make a proper util class for it
	
	private JavaPlugin plugin;
	private boolean doesLog;

	private List<ConfigFile> configFiles;
	private Map<String, Object> settings;

	public Konfig(JavaPlugin plugin) {
		this.plugin = plugin;
		this.doesLog = false;

		this.configFiles = new ArrayList<ConfigFile>();
		this.settings = new HashMap<String, Object>();
	}

	/**
	 * Builds all the ConfigFile's (Adds values and sets up files)
	 */
	public Konfig build(boolean isClosing) {
		addSettings(isClosing);
		return this;
	}

	/**
	 * Builds the config (Adds values and sets up files). If true, it logs each
	 * movement of the files
	 *
	 * @param Boolean
	 *            - If it'll log to the console or not.
	 */
	public Konfig build(boolean isClosing, boolean doesLog) {
		this.doesLog = doesLog;
		addSettings(isClosing);
		return this;
	}

	/**
	 * Gets a <code>String</code> value from within any of the attached
	 * ConfigFile's.
	 *
	 * @param String
	 *            - Where the value is located.
	 * @return String - Gets the String found with the given location.
	 */
	public String getString(String valueLocation) {
		if (!containsKey(valueLocation))
			return ChatColor.RED + "ERROR, CONFIG LOCATION '" + valueLocation + "' IS NOT FOUND";
		return (String) settings.get(valueLocation);
	}

	/**
	 * Gets a <code>Boolean</code> value from within any of the attached
	 * ConfigFile's.
	 *
	 * @param String
	 *            Where the value is located.
	 * @return Boolean - Gets the Boolean found with the given location.
	 */
	public Boolean getBoolean(String valueLocation) {
		if (!containsKey(valueLocation))
			return null;
		return (Boolean) settings.get(valueLocation);
	}

	/**
	 * Gets a <code>Integer</code> value from within any of the attached
	 * ConfigFile's.
	 *
	 * @param String
	 *            Where the value is located.
	 * @return Integer - Gets the Integer found with the given location.
	 */
	public int getInteger(String valueLocation) {
		if (!containsKey(valueLocation))
			return -1;
		return (Integer) settings.get(valueLocation);
	}

	/**
	 * Gets a <code>Double</code> value from within any of the attached
	 * ConfigFile's.
	 *
	 * @param String
	 *            Where the value is located.
	 * @return Double - Gets the Double found with the given location.
	 */
	public double getDouble(String valueLocation) {
		if (!containsKey(valueLocation))
			return -1.0;
		return (Double) settings.get(valueLocation);
	}

	/**
	 * Gets a <code>List</code> value from within any of the attached
	 * ConfigFile's. configs.
	 *
	 * @param String
	 *            Where the value is located.
	 * @return List<String> - Gets the List found with the given location.
	 */
	@SuppressWarnings("serial")
	public List<String> getArray(String valueLocation) {
		if (!containsKey(valueLocation))
			return new ArrayList<String>() {
				{
					add(ChatColor.RED + "ERROR, CONFIG LOCATION '" + valueLocation + "' IS NOT FOUND");
				}
			};
		return (List<String>) settings.get(valueLocation);
	}

	public void setString(String valueLocation, String value) {
		ConfigFile configFile = getConfigFileFromValueLocation(valueLocation);

		if (configFile == null) {
			Console.ERROR.log("Could not find location '" + valueLocation + "' when setting '" + value + "'");
			return;
		}

		configFile.setString(valueLocation, value);
	}

	public void setString(String valueLocation) {
		ConfigFile configFile = getConfigFileFromValueLocation(valueLocation);

		if (configFile == null) {
			Console.ERROR.log("Could not find location '" + valueLocation + "' when setting 'empty value'");
			return;
		}

		configFile.setString(valueLocation);
	}

	public void setInteger(String valueLocation, int value) {
		ConfigFile configFile = getConfigFileFromValueLocation(valueLocation);

		if (configFile == null) {
			Console.ERROR.log("Could not find location '" + valueLocation + "' when setting '" + value + "'");
			return;
		}

		configFile.setInteger(valueLocation, value);
	}

	public void setInteger(String valueLocation) {
		ConfigFile configFile = getConfigFileFromValueLocation(valueLocation);

		if (configFile == null) {
			Console.ERROR.log("Could not find location '" + valueLocation + "' when setting 'empty value'");
			return;
		}

		configFile.setInteger(valueLocation);
	}

	public void setDouble(String valueLocation, double value) {
		ConfigFile configFile = getConfigFileFromValueLocation(valueLocation);

		if (configFile == null) {
			Console.ERROR.log("Could not find location '" + valueLocation + "' when setting '" + value + "'");
			return;
		}

		configFile.setDouble(valueLocation, value);
	}

	public void setDouble(String valueLocation) {
		ConfigFile configFile = getConfigFileFromValueLocation(valueLocation);

		if (configFile == null) {
			Console.ERROR.log("Could not find location '" + valueLocation + "' when setting 'empty value'");
			return;
		}

		configFile.setDouble(valueLocation);
	}

	public void setBoolean(String valueLocation, boolean value) {
		ConfigFile configFile = getConfigFileFromValueLocation(valueLocation);

		if (configFile == null) {
			Console.ERROR.log("Could not find location '" + valueLocation + "' when setting '" + value + "'");
			return;
		}

		configFile.setBoolean(valueLocation, value);
	}

	public void setBoolean(String valueLocation) {
		ConfigFile configFile = getConfigFileFromValueLocation(valueLocation);

		if (configFile == null) {
			Console.ERROR.log("Could not find location '" + valueLocation + "' when setting 'empty value'");
			return;
		}

		configFile.setBoolean(valueLocation);
	}

	public void setArray(String valueLocation, List<String> value) {
		ConfigFile configFile = getConfigFileFromValueLocation(valueLocation);

		if (configFile == null) {
			Console.ERROR.log("Could not find location '" + valueLocation + "' when setting '" + value + "'");
			return;
		}

		Console.INFO.log("HERE " + configFile.getConfigName());
		configFile.setArray(valueLocation, value);
	}

	public void setArray(String valueLocation, ArrayList<String> value) {
		ConfigFile configFile = getConfigFileFromValueLocation(valueLocation);

		if (configFile == null) {
			Console.ERROR.log("Could not find location '" + valueLocation + "' when setting '" + value + "'");
			return;
		}
		Console.INFO.log("HERE " + configFile.getConfigName());

		configFile.setArray(valueLocation, value);
	}

	public void setArray(String valueLocation) {
		ConfigFile configFile = getConfigFileFromValueLocation(valueLocation);

		if (configFile == null) {
			Console.ERROR.log("Could not find location '" + valueLocation + "' when setting 'empty value'");
			return;
		}

		configFile.setArray(valueLocation, new ArrayList<String>());
	}

	/**
	 * Adds a <code>ConfigFile</code> to the config.
	 *
	 * @param A
	 *            pre-set config file.
	 */
	public Konfig addConfigFile(ConfigFile configFile) {
		this.configFiles.add(configFile);
		return this;
	}

	/**
	 * Reloads a config with a certain name.
	 *
	 * @param String
	 *            The configs name.
	 */
	public void reload(String configName) {
		configName.replace(".yml", ""); // Just in case.
		if (!containsConfig(configName))
			return;

		YmlFile ymlFile = new YmlFile(plugin, configName, this.doesLog);
		ymlFile.save();
		ymlFile.reload();

		this.build(false);

		Console.SUCCESS.log("Config '" + configName + "' has been reloaded");
	}

	/**
	 * Reloads all config files.
	 */
	public void reload() {
		if (this.configFiles.isEmpty())
			return;

		this.configFiles.forEach(configFile -> {
			YmlFile ymlFile = new YmlFile(plugin, configFile.getConfigName(), this.doesLog);
			ymlFile.save();
			ymlFile.reload();

		});

		this.build(false);

		Console.SUCCESS.log("All configs have been reloaded!");
	}

	/**
	 * Reloads all config files.
	 */
	public void reloadAll() {
		if (this.configFiles.isEmpty())
			return;

		this.configFiles.forEach(configFile -> {
			YmlFile ymlFile = new YmlFile(plugin, configFile.getConfigName(), this.doesLog);
			ymlFile.save();
			ymlFile.reload();
		});

		this.build(false);

		Console.SUCCESS.log("All configs have been reloaded!");
	}

	// ===== Tools =====

	/**
	 * Adds all the ConfigFile's value to a hashmap, along with setting them in
	 * the YmlFile if not set.
	 */
	private void addSettings(boolean isClosing) {
		long startingTime = System.currentTimeMillis();
		settings.clear();

		this.configFiles.forEach(file -> {
			YmlFile dummy = new YmlFile(plugin, file.getConfigName(), this.doesLog);
			file.getConfigValues().forEach((valueLocation, value) -> {
				if (dummy.isSet(valueLocation)) {
					if (!dummy.get(valueLocation).equals(value)) {
						if (isClosing)
							dummy.set(valueLocation, value);
						else
							dummy.set(valueLocation, dummy.get(valueLocation));
					}
				} else
					dummy.set(valueLocation, value);

				Object configValue = dummy.get(valueLocation);
				// TODO translate string arraylists

				if (configValue instanceof String)
					settings.put(valueLocation, color((String) configValue));

				else
					settings.put(valueLocation, configValue);
			});
			dummy.save();
		});

		Console.INFO.log("Configs built in " + (System.currentTimeMillis() - startingTime) + "ms");
	}

	/**
	 * Checks if a configs value location is set.
	 *
	 * @param String
	 *            The configs value location.
	 */
	private boolean containsKey(String valueLocation) {
		if (this.settings.containsKey(valueLocation))
			return true;

		Console.ERROR.log("Cannot find key: " + valueLocation);
		return false;
	}

	/**
	 * Checks a YmlFile is set.
	 *
	 * @param String
	 *            The configs name.
	 */
	private boolean containsConfig(String configName) {
		if (this.configFiles.isEmpty())
			return false;

		Boolean containsFile = (this.configFiles.stream()
				.filter(configFile -> configFile.getConfigName().equalsIgnoreCase(configName)).findFirst()
				.orElse(null)) == null;

		if (!containsFile)
			Console.ERROR.log("Cannot find the file '" + configName + "' to reload");

		return containsFile;
	}

	private ConfigFile getConfigFileFromValueLocation(String string) {
		for (ConfigFile configFile : this.configFiles) {
			if (configFile.getAllValuesLocations().contains(string))
				return configFile;
		}
		return null;
	}

	private String color(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}
}
