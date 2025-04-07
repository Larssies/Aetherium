package com.larssies.aetherium.checks.combat;

import com.larssies.aetherium.checks.CheckType;
import com.larssies.aetherium.violations.ViolationsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;

public class AutoClicker {

    private static final Map<Player, Long> lastClick = new HashMap<>();
    private static final Map<Player, Integer> clickStreak = new HashMap<>();

    public static void handle(PlayerInteractEvent event, CheckType check) {
        Player player = event.getPlayer();

        long currentTime = System.currentTimeMillis();
        long last = lastClick.getOrDefault(player, 0L);
        long delay = currentTime - last;

        if (delay > 0 && delay < 5) {
            int streak = clickStreak.getOrDefault(player, 0) + 1;
            clickStreak.put(player, streak);
            if (streak >= 5) {
                ViolationsManager.addViolation(player, check);
            }
        } else {
            clickStreak.put(player, 0);
        }

        lastClick.put(player, currentTime);
    }

}
