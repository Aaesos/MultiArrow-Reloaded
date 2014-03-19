/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.aaesos.MoArrowsReloaded;

import java.util.List;

/**
 *
 * @author Aaesos
 */
public class ConfigHandler {

    private final MoArrowsReloaded moArrows = MoArrowsReloaded.moArrows;

    public float compressionRadius = 4F;
    public float explosiveRadius = 4F;

    public List<String> removedArrows;
    public int teleportCooldown = 15;
    public int poisonCooldown = 15;
    public int explosiveCooldown = 15;
    public int compressionCooldown = 15;
    public int lightningCooldown = 15;
    public int razorCooldown = 15;
    public int slowCooldown = 15;
    public int fireCooldown = 15;
    public int netCooldown = 15;
    public int piercingCooldown = 15;
    public int gravityCooldown = 15;
    public int poisonLength = 10;
    public int slowLength = 10;
    public int fireLength = 10;
    public int netLength = 10;
    public int slowPower = 4;
    public int poisonPower = 1;
    public boolean allowExplosionDamage,
            allowFactionDamage, allowFireDamage;
    
    public void loadConfiguration(){
        removedArrows = moArrows.getConfig().getStringList("removed-arrows");
        poisonLength = moArrows.getConfig().getInt("arrow-durations.poison-duration");
        slowLength = moArrows.getConfig().getInt("arrow-durations.slow-duration");
        fireLength = moArrows.getConfig().getInt("arrow-durations.fire-duration");
        netLength = moArrows.getConfig().getInt("arrow-durations.net-durations");
        
        poisonPower = moArrows.getConfig().getInt("arrow-power.poison-power");
        slowPower = moArrows.getConfig().getInt("arrow-power.slow-power");
        
        teleportCooldown = moArrows.getConfig().getInt("arrow-cooldowns.teleport-cooldown");
        poisonCooldown = moArrows.getConfig().getInt("arrow-cooldowns.poison-cooldown");
        explosiveCooldown = moArrows.getConfig().getInt("arrow-cooldowns.explosive-cooldown");
        compressionCooldown = moArrows.getConfig().getInt("arrow-cooldowns.compression-cooldown");
        lightningCooldown = moArrows.getConfig().getInt("arrow-cooldowns.lightning-cooldown");
        razorCooldown = moArrows.getConfig().getInt("arrow-cooldowns.razor-cooldown");
        slowCooldown = moArrows.getConfig().getInt("arrow-cooldowns.slow-cooldown");
        fireCooldown = moArrows.getConfig().getInt("arrow-cooldowns.fire-cooldown");
        netCooldown = moArrows.getConfig().getInt("arrow-cooldowns.net-cooldown");
        piercingCooldown = moArrows.getConfig().getInt("arrow-cooldowns.piercing-cooldown");
        gravityCooldown = moArrows.getConfig().getInt("arrow-cooldowns.gravity-cooldown");
    }
}
