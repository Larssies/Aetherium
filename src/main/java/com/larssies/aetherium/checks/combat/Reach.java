package com.larssies.aetherium.checks.combat;

import com.larssies.aetherium.checks.CheckType;
import com.larssies.aetherium.violations.ViolationsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Reach {

    public static void handle(EntityDamageByEntityEvent e, CheckType check) {
        if (!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player)) return;

        Player damager = (Player) e.getDamager();
        Player target = (Player) e.getEntity();

        double distance = damager.getLocation().distance(target.getLocation());
        double maxReach = 3.05;

        if (distance > maxReach) {
            ViolationsManager.addViolation(damager, check);
        }
    }

}
