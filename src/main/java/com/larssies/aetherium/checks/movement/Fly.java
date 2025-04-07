package com.larssies.aetherium.checks.movement;

import com.larssies.aetherium.checks.CheckType;
import com.larssies.aetherium.violations.ViolationsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

public class Fly {

    public static void handle(PlayerMoveEvent event, CheckType check) {
        Player player = event.getPlayer();

        if (player.getAllowFlight()) return;
        if (player.isFlying()) {
            ViolationsManager.addViolation(player, check);
        }
    }

}
