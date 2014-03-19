/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.aaesos.MoArrowsReloaded;

import ca.aaesos.MoArrowsReloaded.VariableHandler.arrowType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Aaesos
 */
public class MoArrowsReloaded extends JavaPlugin {
    public static MoArrowsReloaded moArrows;
    
    public Plugin fPlugin;
    public Plugin wgPlugin;
    
    public VariableHandler varHandle;
    public HookHandler hookHandle;
    public ConfigHandler confHandle;
    
    public MoArrowsReloaded(){
        moArrows = this;
    }
    
    @Override
    public void onEnable(){
        this.saveDefaultConfig();
        
        fPlugin = getServer().getPluginManager().getPlugin("Factions");
        wgPlugin = getServer().getPluginManager().getPlugin("WorldGuard");
        
        varHandle = new VariableHandler();
        hookHandle = new HookHandler();
        confHandle = new ConfigHandler();
        
        // Register players in activeArrowType.
        for (Player p : getServer().getOnlinePlayers()) {
            varHandle.activeArrowType.put(p.getName(), arrowType.Normal);
        }
        
        new HudListener(this);
        new PlayerListener(this);
        new ArrowListener(this);
    }
}
