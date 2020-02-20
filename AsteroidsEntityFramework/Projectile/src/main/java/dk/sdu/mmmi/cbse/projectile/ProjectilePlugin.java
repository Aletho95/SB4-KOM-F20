package dk.sdu.mmmi.cbse.projectile;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.ShootingPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.playersystem.Player;

public class ProjectilePlugin {

    private Entity projectile;
    private Entity shooter;

    private float x;
    private float y;
    private float radians;

    public ProjectilePlugin() {
    }

    
    public Entity shoot(GameData gameData, Entity shooter) {

        // Add entities to the world
                projectile = createProjectile(gameData, shooter);
                return projectile;
            }

    private Entity createProjectile(GameData gameData, Entity shooter) {

        float deacceleration = 0;
        float acceleration = 500;
        float maxSpeed = 200;
        float rotationSpeed = 0;

        setPosToShooter(gameData, shooter);
//        float x = gameData.getDisplayWidth() / 2;
//        float y = gameData.getDisplayHeight() / 2;
//        float radians = 3.1415f / 2;

        Entity projectileEntity = new Projectile();
        projectileEntity.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        projectileEntity.add(new PositionPart(x, y, radians));

        return projectileEntity;
    }

    private void setPosToShooter(GameData gameData, Entity shooter) {
        PositionPart posPart = shooter.getPart(PositionPart.class);
        radians = posPart.getRadians();
        x = posPart.getX() + 8;
        y = posPart.getY() + 8;
    }

}
