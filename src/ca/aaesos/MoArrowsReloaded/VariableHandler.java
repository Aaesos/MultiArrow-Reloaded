/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.aaesos.MoArrowsReloaded;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 *
 * @author Aaesos
 */
public class VariableHandler {

    public enum arrowType {

        Normal(0), Razor(1), Piercing(2), Lightning(3),
        Fire(4), Explosive(5), Compression(6), Poison(7),
        Slow(8), Net(9), Teleport(10), Gravity(11);

        private final int code;

        private arrowType(int c) {
            code = c;
        }

        public int getCode() {
            return code;
        }
    }
    
    private static Map<Integer, arrowType> codeToArrowMapping;

    public arrowType getArrowType(int i) {
        if (codeToArrowMapping == null) {
            initMapping();
        }
        return codeToArrowMapping.get(i);
    }

    private static void initMapping() {
        codeToArrowMapping = new TreeMap<>();
        for (arrowType s : arrowType.values()) {
            codeToArrowMapping.put(s.code, s);
        }
    }
    
    //=======================================================
    
    // Arrow Maps
    public Map<String, arrowType> activeArrowType = new TreeMap<>();
    public Map<String, List<arrowType>> removedTypes = new TreeMap<>();
    // arrowList <Location as string> <arrowType>
    public Map<String, arrowType> arrowList = new TreeMap<>();
    public List<Vector> compressionList = new ArrayList<>();
    public List<Vector> explosionList = new ArrayList<>();
    
    public boolean allowExplosionDamage;
    public boolean allowFactionDamage;
}
