/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.aaesos.MoArrowsReloaded.Arrows;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;

/**
 *
 * @author Aaesos
 */
public class LightningArrowEffect implements ArrowEffect {

    @Override
    public void onEntityHitEvent(Arrow arrow, Entity target) {
        arrow.getWorld().strikeLightning(arrow.getLocation());
        arrow.remove();
    }

    @Override
    public void onGroundHitEvent(Arrow arrow) {
        arrow.getWorld().strikeLightning(arrow.getLocation());
        arrow.remove();
    }
    
}
