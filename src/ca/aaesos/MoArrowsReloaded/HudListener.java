/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.aaesos.MoArrowsReloaded;

import ca.aaesos.MoArrowsReloaded.VariableHandler.arrowType;
import me.confuser.barapi.BarAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

/**
 *
 * @author Aaesos
 */
public class HudListener implements Listener{

    private final MoArrowsReloaded moArrows;
;

    private enum direction {

        next, previous
    }

    public HudListener(MoArrowsReloaded plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        moArrows = plugin;
    }

    //=================================================
    @EventHandler
    public void onPlayerHeldChange(final PlayerItemHeldEvent event) {
        if (event.getPlayer().isSneaking()) {
            if (event.getPlayer().getInventory().first(Material.BOW) < 9) {
                if (event.getPlayer().getInventory().getItemInHand().getType() != Material.BOW) {
                    changeArrowType(event.getPlayer(), getDirection(event));
                    updateBarText(event.getPlayer(), moArrows.varHandle.activeArrowType.get(event.getPlayer().getName()));
                }
                event.getPlayer().getInventory().setHeldItemSlot(event.getPlayer().getInventory().first(Material.BOW));
            } else {
                BarAPI.removeBar(event.getPlayer());
            }
        }
    }

    private direction getDirection(PlayerItemHeldEvent event) {
        if (event.getNewSlot() < event.getPreviousSlot()) {
            if (event.getNewSlot() == 0 && event.getPreviousSlot() == 8) {
                return direction.previous;
            } else {
                return direction.next;
            }
        } else {
            if (event.getNewSlot() == 8 && event.getPreviousSlot() == 0) {
                return direction.next;
            } else {
                return direction.previous;
            }
        }
    }

    private void changeArrowType(Player player, direction d) {
        if (moArrows.varHandle.activeArrowType.get(player.getName()) == arrowType.Gravity && d == direction.next) {
            moArrows.varHandle.activeArrowType.put(player.getName(), arrowType.Normal);
        } else if (moArrows.varHandle.activeArrowType.get(player.getName()) == arrowType.Normal && d == direction.previous) {
            moArrows.varHandle.activeArrowType.put(player.getName(), arrowType.Gravity);
        } else if (d == direction.next) {
            moArrows.varHandle.activeArrowType.put(player.getName(), moArrows.varHandle.getArrowType((int) (moArrows.varHandle.activeArrowType.get(player.getName()).getCode() + 1)));
        } else if (d == direction.previous) {
            moArrows.varHandle.activeArrowType.put(player.getName(), moArrows.varHandle.getArrowType((int) (moArrows.varHandle.activeArrowType.get(player.getName()).getCode() - 1)));
        }
    }

    public void updateBarText(Player arrowUser, arrowType arrow) {
        int barMarker;
        barMarker = ((arrow.getCode() * 100) / 11);
        BarAPI.setMessage(arrowUser, ChatColor.AQUA + "Current Arrow: " + ChatColor.GREEN + arrow.toString());
        BarAPI.setHealth(arrowUser, (float) barMarker);
    }
}
