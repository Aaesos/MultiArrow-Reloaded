/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.aaesos.MoArrowsReloaded;

import ca.aaesos.MoArrowsReloaded.Arrows.ArrowEffect;
import ca.aaesos.MoArrowsReloaded.VariableHandler.arrowType;
import java.util.List;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ProjectileHitEvent;

/**
 *
 * @author Aaesos
 */
public class ArrowListener implements Listener {

    private final MoArrowsReloaded moArrows;

    public ArrowListener(MoArrowsReloaded plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        moArrows = MoArrowsReloaded.moArrows;
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntityType() != EntityType.ARROW) {
            return;
        }

        Arrow arrow = (Arrow) event.getEntity();
        if (!(arrow.getShooter() instanceof Player)) {
            return;
        }

        Player shooter = (Player) arrow.getShooter();
        arrowType aType = moArrows.varHandle.activeArrowType.get(shooter.getName());

        List<Entity> entities = arrow.getNearbyEntities(1D, 1D, 1D);
        int entCount = entities.size();
        for (Entity ent : entities) {
            if ((ent instanceof Arrow) || (ent instanceof Item) || (ent == arrow.getShooter())) {
                entCount--;
            }
        }

        try {
            if (entCount == 0) {
                if (aType != arrowType.Normal) {
                    ArrowEffect arrowEffect = null;
                    String className = "ca.aaesos.MoArrowsReloaded.Arrows." + aType.toString() + "ArrowEffect";
                    try {
                        arrowEffect = (ArrowEffect) Class.forName(className).newInstance();
                    } catch (ClassNotFoundException e) {
                        moArrows.getLogger().warning("Failed to find class " + className);
                        return;
                    }

                    arrowEffect.onGroundHitEvent(arrow);
                }
            }
        } catch (Exception ex) {
            return;
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event instanceof EntityDamageByEntityEvent)) {
            return;
        }

        if (event.getCause() != DamageCause.PROJECTILE) {
            return;
        }

        EntityDamageByEntityEvent ebe = (EntityDamageByEntityEvent) event;
        if (!(ebe.getDamager() instanceof Arrow)) {
            return;
        }

        Arrow arrow = (Arrow) ebe.getDamager();
        if (!(arrow.getShooter() instanceof Player)) {
            return;
        }

        Player shooter = (Player) arrow.getShooter();
        arrowType aType = moArrows.varHandle.activeArrowType.get(shooter.getName());

        try {
            if (aType != arrowType.Normal) {
                ArrowEffect arrowEffect = null;
                String className = "ca.aaesos.MoArrowsReloaded.Arrows." + aType.toString() + "ArrowEffect";
                try {
                    arrowEffect = (ArrowEffect) Class.forName(className).newInstance();

                } catch (Exception e) {
                    moArrows.getLogger().severe(e.getMessage());
                }
                
                arrowEffect.onEntityHitEvent(arrow, event.getEntity());
            }
        
        } catch(Exception ex) {
            moArrows.getLogger().severe(ex.getMessage());
        }
    }
}
