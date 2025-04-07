package com.larssies.aetherium.checks;

import com.larssies.aetherium.checks.combat.AutoClicker;
import com.larssies.aetherium.checks.combat.NoFall;
import com.larssies.aetherium.checks.combat.Reach;
import com.larssies.aetherium.checks.movement.Fly;
import com.larssies.aetherium.checks.movement.Scaffold;
import com.larssies.aetherium.checks.movement.Speed;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class ChecksManager {

    /**
     * Handles movement checks.
     *
     * @param event The PlayerMoveEvent to handle.
     */
    public static void handleMovement(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission(CheckType.getBypassPermission())) return;

        Speed.handle(event, CheckType.SPEED);
        Fly.handle(event, CheckType.FLY);
    }

    /**
     * Handles block place checks.
     *
     * @param event The BlockPlaceEvent to handle.
     */
    public static void handleBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission(CheckType.getBypassPermission())) return;

        Scaffold.handle(event, CheckType.SCAFFOLD);
    }

    /**
     * Handles combat checks.
     *
     * @param event The EntityDamageByEntityEvent to handle.
     */
    public static void handleCombat(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        Player player = (Player) event.getDamager();

        if (player.hasPermission(CheckType.getBypassPermission())) return;

        Reach.handle(event, CheckType.REACH);
        NoFall.handle(event, CheckType.NO_FALL);
    }

    /**
     * Handles auto clicker checks.
     *
     * @param event The PlayerInteractEvent to handle.
     */
    public static void handleAutoClicker(PlayerInteractEvent event) {
        if (event.getPlayer().hasPermission(CheckType.getBypassPermission())) return;

        AutoClicker.handle(event, CheckType.AUTO_CLICKER);
    }

}
