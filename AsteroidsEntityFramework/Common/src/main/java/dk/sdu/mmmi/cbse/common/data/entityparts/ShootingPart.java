/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 *
 * @author alext
 */
public class ShootingPart implements EntityPart {

    boolean isShooting;

    public ShootingPart() {
        isShooting = false;
    }

    public boolean getShooting() {
        return isShooting;
    }

    public void setShooting(boolean state) {
        isShooting = state;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        if (isShooting) {
            
        }
    }

}
