/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.aaesos.MoArrowsReloaded.Arrows;

import ca.aaesos.MoArrowsReloaded.MoArrowsReloaded;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

/**
 *
 * @author Aaesos
 */
public class GravityArrowEffect implements ArrowEffect{
    private MoArrowsReloaded moArrows = MoArrowsReloaded.moArrows;

    @Override
    public void onEntityHitEvent(Arrow arrow, Entity target) {
        Vector newVelocity = new Vector(target.getVelocity().getX(),target.getVelocity().getY()+1000,target.getVelocity().getZ());
        target.setVelocity(newVelocity);
        moArrows.getServer().getPlayer("Aaesos").sendMessage("Hit an entity wiht Grav arrow.");
    }

    @Override
    public void onGroundHitEvent(Arrow arrow) {
        
    }
    
}
