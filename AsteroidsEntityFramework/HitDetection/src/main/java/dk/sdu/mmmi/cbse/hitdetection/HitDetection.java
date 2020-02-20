/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.hitdetection;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

/**
 *
 * @author alext
 */
public class HitDetection implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        
        Entity tempEntity = null;
        float radius = 0;
        float tempRadius = 0;

        for (Entity entity : world.getEntities()) {
            
            if (tempEntity != null) {
                radius = entity.getRadius();
                tempRadius = tempEntity.getRadius();
                
                PositionPart tempEntityPos = tempEntity.getPart(PositionPart.class);
                PositionPart entityPos = entity.getPart(PositionPart.class);
                
                if (entityPos.getX() + radius  >= tempEntityPos.getX() - tempRadius 
                        && entityPos.getX() + radius < tempEntityPos.getX() + tempRadius 
                        && entityPos.getY() > tempEntityPos.getY() - tempRadius 
                        && entityPos.getY() < tempEntityPos.getY() + tempRadius) {
                    world.removeEntity(entity);
                    world.removeEntity(tempEntity);
                } else if (tempEntityPos.getX() + tempRadius >= entityPos.getX() - radius 
                        && tempEntityPos.getX() + tempRadius < entityPos.getX() + radius
                        && tempEntityPos.getY() > entityPos.getY() - radius
                        && tempEntityPos.getY() < entityPos.getY() + radius){
                    world.removeEntity(entity);
                    world.removeEntity(tempEntity);
                }
            }
            tempEntity = entity;
        }
    }

}
