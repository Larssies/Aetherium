package com.larssies.aetherium.violations;

import com.larssies.aetherium.checks.CheckType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ViolationsManager {

    private static final Map<Player, Map<CheckType, Integer>> violations = new HashMap<>();

    public static void addViolation(Player player, CheckType check) {
        violations.putIfAbsent(player, new HashMap<>());
        Map<CheckType, Integer> playerViolations = violations.get(player);
        int newVL = playerViolations.getOrDefault(check, 0) + 1;
        playerViolations.put(check, newVL);

        for (Player p : Bukkit.getOnlinePlayers()) {
            if(p.hasPermission("aetherium.alerts")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[Aetherium] &e" + player.getName() + " has reached " + newVL + " violations for " + check + "!"));
            }
        }

        if (newVL >= check.getMaxVL()) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if(p.hasPermission("aetherium.alerts")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[Aetherium] &e" + player.getName() + " has been kicked for violations of " + check + "!"));
                }
            }
            player.kickPlayer(ChatColor.translateAlternateColorCodes('&', "&cYou have been kicked for an unfair advantage!"));
        }
    }

    public static int getViolationLevel(Player player, String check) {
        return violations.getOrDefault(player, new HashMap<>()).getOrDefault(check, 0);
    }
}
