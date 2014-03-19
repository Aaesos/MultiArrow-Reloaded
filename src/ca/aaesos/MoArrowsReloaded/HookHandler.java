/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.aaesos.MoArrowsReloaded;

import com.massivecraft.factions.FFlag;
import com.massivecraft.factions.Factions;
import com.massivecraft.factions.entity.BoardColls;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.mcore.ps.PS;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityExplodeEvent;

/**
 *
 * @author Aaesos
 */
public class HookHandler {

    private final MoArrowsReloaded moArrows = MoArrowsReloaded.moArrows;

    private WorldGuardPlugin getWorldGuard() {
        if (moArrows.wgPlugin == null || !(moArrows.wgPlugin instanceof WorldGuardPlugin)) {
            return null;
        }
        return (WorldGuardPlugin) moArrows.wgPlugin;
    }

    private Factions getFactions() {
        if (moArrows.fPlugin == null || !(moArrows.fPlugin instanceof Factions)) {
            return null;
        }
        return (Factions) moArrows.fPlugin;
    }
/*
    boolean isProtected(Player player, Location location, int radius) {
        if (getWorldGuard() != null) {
            try {
                ApplicableRegionSet regionSet = getWorldGuard().getRegionManager(location.getWorld()).getApplicableRegions(location);
                if (!regionSet.allows(DefaultFlag.PVP) || !getWorldGuard().canBuild(player,
                        location.getBlock().getRelative(0, 0, 0))
                        || !getWorldGuard().canBuild(player,
                                location.getBlock().getRelative(radius, 0, 0))
                        || !getWorldGuard().canBuild(player,
                                location.getBlock().getRelative(0, radius, 0))
                        || !getWorldGuard().canBuild(player,
                                location.getBlock().getRelative(0, 0, radius))
                        || !getWorldGuard().canBuild(player,
                                location.getBlock().getRelative(-radius, 0, 0))
                        || !getWorldGuard().canBuild(player,
                                location.getBlock().getRelative(0, -radius, 0))
                        || !getWorldGuard().canBuild(player,
                                location.getBlock().getRelative(0, 0, -radius))
                        || !getWorldGuard().canBuild(player,
                                location.getBlock().getRelative(radius, radius, radius))
                        || !getWorldGuard().canBuild(player,
                                location.getBlock().getRelative(radius, -radius, radius))
                        || !getWorldGuard().canBuild(player,
                                location.getBlock().getRelative(-radius, -radius, radius))
                        || !getWorldGuard().canBuild(player,
                                location.getBlock().getRelative(-radius, radius, radius))
                        || !getWorldGuard().canBuild(player,
                                location.getBlock().getRelative(radius, radius, -radius))
                        || !getWorldGuard().canBuild(player,
                                location.getBlock().getRelative(-radius, radius, -radius))
                        || !getWorldGuard().canBuild(player,
                                location.getBlock().getRelative(radius, -radius, -radius))
                        || !getWorldGuard().canBuild(player,
                                location.getBlock().getRelative(-radius, -radius, -radius))) {
                    return false;
                } else {
                    return true;
                }
            } catch (Exception ex) {
                return true;
            }
        }
        return true;
    }

    private boolean isRegionPvp(Location location) {
        if (getWorldGuard() != null) { //add radius variable!!!!!!!!!!!
            //plugin.log.warning("#debug canShoot active.");

            try {
                ApplicableRegionSet regionSet = getWorldGuard().getRegionManager(location.getWorld()).getApplicableRegions(location);
                if (regionSet.allows(DefaultFlag.PVP)) {
                    return true;
                }
                return false;

            } catch (Exception ex) {
                return true;
            }
        } else {
            return true;
        }
    }*/

    boolean isFactionLand(Location location) {

        if (getFactions() != null) {
            Faction faction = BoardColls.get().getFactionAt(PS.valueOf(location));
            //plugin.log.warning("#debug No faction?=" + faction.isNone());
            if (faction.isNone()) {
                return false;
            } else {
                return true;
            }
        } else {
            //plugin.log.warning("#debug factions not hooked!");
            return false;
        }
    }

    boolean isFactionSafeZone(Location location) {

        if (getFactions() != null) {
            Faction faction = BoardColls.get().getFactionAt(PS.valueOf(location));
            //plugin.log.warning("#debug No faction?=" + faction.isNone());
            if (faction.getName().equals("SafeZone") || faction.getFlag(FFlag.PEACEFUL)) {
                return true;
            } else {
                return false;
            }
        } else {
            //plugin.log.warning("#debug factions not hooked!");
            return false;
        }
    }

    boolean isFactionWarZone(Location location) {

        if (getFactions() != null) {
            Faction faction = BoardColls.get().getFactionAt(PS.valueOf(location));
            //plugin.log.warning("#debug No faction?=" + faction.isNone());
            if (faction.getName().equals("WarZone")) {
                return true;
            } else {
                return false;
            }
        } else {
            //plugin.log.warning("#debug factions not hooked!");
            return false;
        }
    }

    public boolean canHit(Location location) {

        if (isFactionWarZone(location)) {
            return true;
        }

        if (isFactionSafeZone(location)) {
            return false;
        }

       // if (!isRegionPvp(location)) {
       //     return false;
       // }
        return true;
    }

    public boolean canExplode(EntityExplodeEvent event) {
        Boolean allowExplosionDamage = moArrows.varHandle.allowExplosionDamage;
        Boolean allowFactionDamage = moArrows.varHandle.allowFactionDamage;
        Location location;
        List<Block> blockList = event.blockList();

        if (event.getEntity() == null) {

            if (!allowExplosionDamage) {
                return false;
            }

            for (Block block : blockList) {
                location = block.getLocation();

                if (!allowFactionDamage && isFactionLand(location)) {
                    return false;
                }

                if (isFactionSafeZone(location)) {
                    return false;
                }

         //       if (!isRegionPvp(location)) {
         //           return false;
         //       }

            }
            return true;
        } else {
            return true;
        }
    }
}
