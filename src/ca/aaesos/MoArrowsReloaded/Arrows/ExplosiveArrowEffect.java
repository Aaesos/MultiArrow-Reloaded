/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.aaesos.MoArrowsReloaded.Arrows;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;

import ca.aaesos.MoArrowsReloaded.MoArrowsReloaded;
import org.bukkit.Location;

/**
 *
 * @author Aaesos
 */
public class ExplosiveArrowEffect implements ArrowEffect {

    private MoArrowsReloaded moArrows = MoArrowsReloaded.moArrows;

    @Override
    public void onEntityHitEvent(Arrow arrow, Entity target) {
        Location location = arrow.getLocation();
        arrow.getWorld().createExplosion(location, moArrows.confHandle.explosiveRadius);
        arrow.remove();
    }

    @Override
    public void onGroundHitEvent(Arrow arrow) {
        Location location = arrow.getLocation();
        arrow.getWorld().createExplosion(location, moArrows.confHandle.explosiveRadius);
        arrow.remove();
    }

}
