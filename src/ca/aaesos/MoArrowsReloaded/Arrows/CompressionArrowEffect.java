/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.aaesos.MoArrowsReloaded.Arrows;

import ca.aaesos.MoArrowsReloaded.MoArrowsReloaded;
import ca.aaesos.MoArrowsReloaded.VariableHandler.arrowType;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;

/**
 *
 * @author Aaesos
 */
public class CompressionArrowEffect implements ArrowEffect {

    private final MoArrowsReloaded moArrows = MoArrowsReloaded.moArrows;

    @Override
    public void onEntityHitEvent(Arrow arrow, Entity target) {
        Location location = arrow.getLocation();
        arrow.getWorld().createExplosion(location.getX(), location.getY(), location.getZ(), 4F, false, false);
        arrow.remove();
    }

    @Override
    public void onGroundHitEvent(Arrow arrow) {
        Location location = arrow.getLocation();
        arrow.getWorld().createExplosion(location.getX(), location.getY(), location.getZ(), 4F, false, false);
        arrow.remove();
    }
}
