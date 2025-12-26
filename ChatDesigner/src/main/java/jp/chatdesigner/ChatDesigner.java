package jp.chatdesigner;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatDesigner extends JavaPlugin {

    @Override
    public void onEnable() {

        // config.yml を生成
        saveDefaultConfig();

        // config から読み込み
        String prefix = getConfig().getString("prefix", "&a[Replit] &f")
                .replace("&", "§");
        String message = getConfig().getString("message", "テストメッセージ");
        int minutes = getConfig().getInt("interval-minutes", 5);

        // 分 → tick
        long ticks = minutes * 60L * 20L;

        // 5分ごとにチャット表示
        Bukkit.getScheduler().runTaskTimer(
                this,
                () -> Bukkit.broadcastMessage(prefix + message),
                ticks,
                ticks
        );

        getLogger().info("ChatDesigner が有効化されました");
    }

    @Override
    public void onDisable() {
        getLogger().info("ChatDesigner が無効化されました");
    }
}
