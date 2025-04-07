package com.larssies.aetherium.checks.combat;

import com.larssies.aetherium.checks.CheckType;
import com.larssies.aetherium.violations.ViolationsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class NoFall {

    public static void handle(EntityDamageByEntityEvent event, CheckType check) {
        if (!(event.getEntity() instanceof Player)) return;
        if (event.getCause() != EntityDamageEvent.DamageCause.FALL) return;

        Player player = (Player) event.getEntity();

        if (event.getDamage() <= 0.0) {
            ViolationsManager.addViolation(player, check);
        }
    }

}
