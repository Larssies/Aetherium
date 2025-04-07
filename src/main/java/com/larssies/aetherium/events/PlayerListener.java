package com.larssies.aetherium.events;

import com.larssies.aetherium.checks.CheckType;
import com.larssies.aetherium.checks.ChecksManager;
import com.larssies.aetherium.checks.movement.Speed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        ChecksManager.handleMovement(event);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        ChecksManager.handleCombat(event);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        ChecksManager.handleAutoClicker(event);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        ChecksManager.handleBlockPlace(event);
    }

}
