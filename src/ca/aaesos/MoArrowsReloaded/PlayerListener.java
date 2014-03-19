/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.aaesos.MoArrowsReloaded;

import ca.aaesos.MoArrowsReloaded.VariableHandler.arrowType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 *
 * @author Aaesos
 */
public class PlayerListener implements Listener{
    private final MoArrowsReloaded moArrows;
    
    public PlayerListener(MoArrowsReloaded plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        moArrows = MoArrowsReloaded.moArrows;
    }
    
    //=========================================================
    
    @EventHandler
    public void playerShootBow(EntityShootBowEvent event){
        if(event.getEntityType()!=EntityType.PLAYER){
            return;
        }
        
        Player player = (Player) event.getEntity();
        player.sendMessage("You shot a bow.");
        
        arrowType aType = moArrows.varHandle.activeArrowType.get(player.getName());
        player.sendMessage("You shot a "+aType.toString()+" arrow.");
    }
    
    //==========================================================
    
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        moArrows.varHandle.activeArrowType.put(event.getPlayer().getName(), arrowType.Normal);
    }
}
