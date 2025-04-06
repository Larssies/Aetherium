package com.larssies.aetherium.checks;

import com.larssies.aetherium.checks.combat.Reach;
import com.larssies.aetherium.checks.movement.Speed;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class ChecksManager {

    public static void handleMovement(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission(CheckType.SPEED.getBypassPermission())) return;

        Speed.handle(event, CheckType.SPEED);
    }

    public static void handleCombat(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        Player player = (Player) event.getDamager();

        if (player.hasPermission(CheckType.REACH.getBypassPermission())) return;

        Reach.handle(event, CheckType.REACH);
    }

}
