package com.larssies.aetherium.checks.movement;

import com.larssies.aetherium.checks.CheckType;
import com.larssies.aetherium.violations.ViolationsManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Speed {
    private static final Map<UUID, Double> lastValidSpeed = new HashMap<>();
    private static final Map<UUID, Integer> violationLevels = new HashMap<>();
    private static final double WALKING_SPEED = 0.217;
    private static final double SPRINTING_SPEED = 0.283;
    private static final double SNEAKING_SPEED = 0.094;
    private static final double ICE_MULTIPLIER = 1.6;
    private static final double SOUL_SPEED_MULTIPLIER = 1.25;
    private static final double JUMP_BOOST_PER_LEVEL = 0.12;
    private static final double SPEED_EFFECT_MULTIPLIER = 0.2;
    private static final double VERTICAL_TOLERANCE = 0.5;
    private static final int VL_THRESHOLD = 3;

    public static void handle(PlayerMoveEvent event, CheckType check) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Location from = event.getFrom();
        Location to = event.getTo();

        if (from.getWorld() != to.getWorld() || player.isFlying() ||
                player.isInsideVehicle() || player.isDead() || player.isSwimming() ||
                player.getGameMode().toString().contains("CREATIVE") ||
                player.getGameMode().toString().contains("SPECTATOR")) {
            return;
        }

        double horizontalDistance = Math.sqrt(
                Math.pow(to.getX() - from.getX(), 2) +
                        Math.pow(to.getZ() - from.getZ(), 2)
        );

        double verticalDistance = to.getY() - from.getY();
        double maxAllowedSpeed = calculateMaxSpeed(player, verticalDistance > 0);

        if (Math.abs(verticalDistance) > 0.1) {
            maxAllowedSpeed += Math.min(Math.abs(verticalDistance) * 0.3, VERTICAL_TOLERANCE);
        }

        Material blockBelow = from.getBlock().getRelative(0, -1, 0).getType();
        if (blockBelow.toString().contains("ICE") || blockBelow.toString().contains("SLIME")) {
            violationLevels.put(uuid, 0);
        }

        if (horizontalDistance > maxAllowedSpeed) {
            int vl = violationLevels.getOrDefault(uuid, 0) + 1;
            violationLevels.put(uuid, vl);

            if (vl >= VL_THRESHOLD) {
                ViolationsManager.addViolation(player, check);
            }
        } else {
            int vl = violationLevels.getOrDefault(uuid, 0);
            if (vl > 0) {
                violationLevels.put(uuid, vl - 1);
            }
            lastValidSpeed.put(uuid, horizontalDistance);
        }
    }

    private static double calculateMaxSpeed(Player player, boolean jumping) {
        double baseSpeed;

        if (player.isSneaking()) {
            baseSpeed = SNEAKING_SPEED;
        } else if (player.isSprinting()) {
            baseSpeed = SPRINTING_SPEED;
        } else {
            baseSpeed = WALKING_SPEED;
        }

        Location loc = player.getLocation();
        Location blockLoc = loc.clone().subtract(0, 0.1, 0);
        Material blockType = blockLoc.getBlock().getType();

        if (blockType.toString().contains("ICE")) {
            baseSpeed *= ICE_MULTIPLIER;
        }

        if (blockType.toString().contains("SOUL") &&
                player.getInventory().getBoots() != null &&
                player.getInventory().getBoots().getEnchantments().keySet().toString().contains("SOUL_SPEED")) {
            baseSpeed *= SOUL_SPEED_MULTIPLIER;
        }

        for (PotionEffect effect : player.getActivePotionEffects()) {
            if (effect.getType().equals(PotionEffectType.SPEED)) {
                int amplifier = effect.getAmplifier() + 1;
                baseSpeed *= (1 + (SPEED_EFFECT_MULTIPLIER * amplifier));
            }

            if (jumping && effect.getType().equals(PotionEffectType.JUMP_BOOST)) {
                int amplifier = effect.getAmplifier() + 1;
                baseSpeed += JUMP_BOOST_PER_LEVEL * amplifier;
            }
        }

        baseSpeed *= 1.1;

        return baseSpeed;
    }
}