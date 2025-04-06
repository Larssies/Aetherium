package com.larssies.aetherium.checks.movement;

import com.larssies.aetherium.checks.CheckType;
import com.larssies.aetherium.violations.ViolationsManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

public class Speed {

    public static void handle(PlayerMoveEvent event, CheckType check) {
        Player player = event.getPlayer();

        Location from = event.getFrom();
        Location to = event.getTo();

        if (from.getWorld() != to.getWorld()) return;

        double distance = to.distance(from);
        double maxAllowed = player.isSprinting() ? 0.3 : 0.2;

        if (!player.isFlying() && distance > maxAllowed) {
            ViolationsManager.addViolation(player, check);
        }
    }
}
