package dk.sdu.mmmi.cbse.projectile;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.SPACE;

/**
 *
 * @author jcs
 */
public class ProjectileControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
            for (Entity projectile : world.getEntities(Projectile.class)) {
                PositionPart positionPart = projectile.getPart(PositionPart.class);
                MovingPart movingPart = projectile.getPart(MovingPart.class);

                movingPart.setUp(true);

                movingPart.process(gameData, projectile);
                positionPart.process(gameData, projectile);

                if(positionPart.getX() > gameData.getDisplayWidth() - 5 
                        || positionPart.getY() > gameData.getDisplayHeight() - 5) {
                    world.getEntities().remove(projectile);
                } else {
                    updateShape(projectile);
                }
            }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 2);
        shapey[0] = (float) (y + Math.sin(radians) * 2);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 4);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 4);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 2);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 2);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 4);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 4);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
