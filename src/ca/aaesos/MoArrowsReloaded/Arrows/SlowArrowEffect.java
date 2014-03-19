/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.aaesos.MoArrowsReloaded.Arrows;

import ca.aaesos.MoArrowsReloaded.MoArrowsReloaded;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 *
 * @author Aaesos
 */
public class SlowArrowEffect implements ArrowEffect {
    
    private final MoArrowsReloaded moArrows = MoArrowsReloaded.moArrows;
    
    @Override
    public void onEntityHitEvent(Arrow arrow, Entity target) {
        LivingEntity e = (LivingEntity) target;
        PotionEffect potion = new PotionEffect(PotionEffectType.SLOW, (moArrows.confHandle.slowLength * 20), 4);
        e.addPotionEffect(potion);
    }
    
    @Override
    public void onGroundHitEvent(Arrow arrow) {
        
    }
    
}
