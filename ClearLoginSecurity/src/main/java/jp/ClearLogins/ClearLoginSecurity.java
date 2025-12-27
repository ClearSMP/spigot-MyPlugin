package jp.clearlogins;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ClearLoginSecurity extends Plugin {

    private static ClearLoginSecurity instance;
    private boolean maintenance = false;
    private Configuration config;

    @Override
    public void onEnable() {
        instance = this;

        loadConfig();

        getProxy().getPluginManager().registerListener(this, new LoginListener());
        getProxy().getPluginManager().registerCommand(this, new ClearcordCommand());

        getLogger().info("ClearLoginSecurity enabled");
    }

    private void loadConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdir();
            }

            File file = new File(getDataFolder(), "config.yml");

            if (!file.exists()) {
                ConfigurationProvider.getProvider(YamlConfiguration.class)
                        .save(
                                ConfigurationProvider.getProvider(YamlConfiguration.class)
                                        .load(getResourceAsStream("config.yml")),
                                file
                        );
            }

            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ClearLoginSecurity getInstance() {
        return instance;
    }

    public boolean isMaintenance() {
        return maintenance;
    }

    public void setMaintenance(boolean maintenance) {
        this.maintenance = maintenance;
    }

    public String getMaintenanceMessage() {
        return config.getString("maintenance.message");
    }
}
