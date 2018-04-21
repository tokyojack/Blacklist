package me.tokyojack.mcmarket.blacklist.utils.Konfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigFile {

	private Map<String, Object> configValues = new HashMap<String, Object>();
	private String configName;

	public ConfigFile(String configName) {
		this.configName = configName;
	}

	/**
	 * Sets a empty <code>String</code> value.
	 *
	 * @param String
	 *            Where to set the value.
	 */
	public ConfigFile setString(String valueLocation) {
		this.configValues.put(valueLocation, "");
		return this;
	}

	/**
	 * Sets a <code>String</code> value.
	 *
	 * @param String
	 *            Where to set the value.
	 * @param String
	 *            The value.
	 */
	public ConfigFile setString(String valueLocation, String value) {
		this.configValues.put(valueLocation, value);
		return this;
	}

	/**
	 * Sets a <code>Boolean</code> value.
	 *
	 * @param String
	 *            Where to set the value.
	 * @param Boolean
	 *            The value.
	 */
	public ConfigFile setBoolean(String valueLocation, boolean value) {
		this.configValues.put(valueLocation, value);
		return this;
	}

	/**
	 * Sets a <code>Boolean</code> value.
	 *
	 * @param String
	 *            Where to set the value.
	 */
	public ConfigFile setBoolean(String valueLocation) {
		this.configValues.put(valueLocation, false);
		return this;
	}

	/**
	 * Sets a <code>Integer</code> with the value of 0.
	 *
	 * @param String
	 *            Where to set the value.
	 */
	public ConfigFile setInteger(String valueLocation) {
		this.configValues.put(valueLocation, 0);
		return this;
	}

	/**
	 * Sets a <code>Integer</code> value.
	 *
	 * @param String
	 *            Where to set the value.
	 * @param Integer
	 *            The value.
	 */
	public ConfigFile setInteger(String valueLocation, int value) {
		this.configValues.put(valueLocation, value);
		return this;
	}

	/**
	 * Sets a <code>Double</code> with the value of 0.0.
	 *
	 * @param String
	 *            Where to set the value.
	 */
	public ConfigFile setDouble(String valueLocation) {
		this.configValues.put(valueLocation, 0.0);
		return this;
	}

	/**
	 * Sets a <code>Double</code> value.
	 *
	 * @param String
	 *            Where to set the value.
	 * @param Integer
	 *            The value.
	 */
	public ConfigFile setDouble(String valueLocation, double value) {
		this.configValues.put(valueLocation, value);
		return this;
	}

	/**
	 * Sets a empty <code>ArrayList<String></code>.
	 *
	 * @param String
	 *            Where to set the value.
	 */
	public ConfigFile setArray(String valueLocation) {
		this.configValues.put(valueLocation, new ArrayList<String>());
		return this;
	}

	/**
	 * Sets a <code>List<String></code> value.
	 *
	 * @param String
	 *            Where to set the value.
	 * @param List<String>
	 *            The value.
	 */
	public ConfigFile setArray(String valueLocation, List<String> value) {
		this.configValues.put(valueLocation, value);
		return this;
	}

	public ConfigFile setArray(String valueLocation, ArrayList<String> value) {
		this.configValues.put(valueLocation, value);
		return this;
	}

	/**
	 * Sets a <code>Object<String></code> value.
	 *
	 * @param String
	 *            Where to set the value.
	 * @param Object
	 *            The value.
	 */
	public ConfigFile set(String valueLocation, Object value) {
		this.configValues.put(valueLocation, value);
		return this;
	}

	/**
	 * Returns the ConfigValues.
	 */
	public Map<String, Object> getConfigValues() {
		return this.configValues;
	}

	/**
	 * Returns the ConfigName.
	 */
	public String getConfigName() {
		return this.configName;
	}

	/**
	 * Returns all the value locations.
	 */
	public List<String> getAllValuesLocations() {
		return new ArrayList<String>(this.configValues.keySet());
	}
}
