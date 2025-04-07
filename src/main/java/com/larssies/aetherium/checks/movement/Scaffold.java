package com.larssies.aetherium.checks.movement;

import com.larssies.aetherium.checks.CheckType;
import com.larssies.aetherium.violations.ViolationsManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;

public class Scaffold {

    public static void handle(BlockPlaceEvent event, CheckType check) {
        Player player = event.getPlayer();
        Block block = event.getBlockPlaced();

        boolean hasSupport = false;
        for (Block face : new Block[] {
                block.getRelative(0, -1, 0),
                block.getRelative(1, 0, 0),
                block.getRelative(-1, 0, 0),
                block.getRelative(0, 0, 1),
                block.getRelative(0, 0, -1)
        }) {
            if (face.getType() != Material.AIR) {
                hasSupport = true;
                break;
            }
        }

        if (!hasSupport) {
            ViolationsManager.addViolation(player, check);
        }
    }

}
