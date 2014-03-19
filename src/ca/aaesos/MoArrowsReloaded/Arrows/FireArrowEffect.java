/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.aaesos.MoArrowsReloaded.Arrows;

import ca.aaesos.MoArrowsReloaded.MoArrowsReloaded;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;

/**
 *
 * @author Aaesos
 */
public class FireArrowEffect implements ArrowEffect{
    private MoArrowsReloaded moArrows = MoArrowsReloaded.moArrows;

    @Override
    public void onEntityHitEvent(Arrow arrow, Entity target) {
        target.setFireTicks(moArrows.confHandle.fireLength * 20);
    }

    @Override
    public void onGroundHitEvent(Arrow arrow) {
       
    }
    
}
