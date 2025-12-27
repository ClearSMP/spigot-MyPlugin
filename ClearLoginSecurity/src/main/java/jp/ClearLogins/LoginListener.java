package jp.clearlogins;

import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class LoginListener implements Listener {

    @EventHandler
    public void onPreLogin(PreLoginEvent event) {

        if (!ClearLoginSecurity.getInstance().isMaintenance()) {
            return;
        }

        // 管理者は通す
        if (event.getConnection().hasPermission("clearloginsecurity.bypass")) {
            return;
        }

        event.setCancelled(true);
        event.setCancelReason(
                ClearLoginSecurity.getInstance().getMaintenanceMessage()
        );
    }
}
