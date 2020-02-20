package dk.sdu.mmmi.cbse.projectile;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.playersystem.Player;

public class ProjectilePlugin implements IGamePluginService {

    private Entity projectile;
    private Player player;

    private float x;
    private float y;
    private float radians;
    
    public ProjectilePlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        
        // Add entities to the world
        for (Entity entity : world.getEntities(Player.class)) {
            player = (Player) entity;
        }
        projectile = createProjectile(gameData, player);
        world.addEntity(projectile);
    }

    private Entity createProjectile(GameData gameData, Player player) {

        float deacceleration = 0;
        float acceleration = 500;
        float maxSpeed = 200;
        float rotationSpeed = 0;
        
        setPosToPlayer(gameData, player);
//        float x = gameData.getDisplayWidth() / 2;
//        float y = gameData.getDisplayHeight() / 2;
//        float radians = 3.1415f / 2;
        
        Entity projectileEntity = new Projectile();
        projectileEntity.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        projectileEntity.add(new PositionPart(x, y, radians));
        
        return projectileEntity;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(projectile);
    }

    private void setPosToPlayer(GameData gameData, Player player) {
        PositionPart posPart = player.getPart(PositionPart.class);
        radians = posPart.getRadians();
        x = posPart.getX() + 8;
        y = posPart.getY() + 8;
    }

}
