package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {

    private float x;
    private float y;
    Random ranGen = new Random();
    
    private Entity asteroid;

    public AsteroidPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        
        // Add entities to the world
        asteroid = createAsteroid(gameData);
        world.addEntity(asteroid);
    }

    private Entity createAsteroid(GameData gameData) {
        float deacceleration = 0;
        float acceleration = 20;
        float maxSpeed = 20;
        float rotationSpeed = 5;
        
        generateStartPos(gameData);
        
        float radians = ranGen.nextInt(10);
        
        Entity asteroidEntity = new Asteroid();
        asteroidEntity.setRadius(35);
        asteroidEntity.setShapeX(new float[6]);
        asteroidEntity.setShapeY(new float[6]);
        asteroidEntity.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        asteroidEntity.add(new PositionPart(x, y, radians));
        
        return asteroidEntity;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(asteroid);
    }
    
    private void generateStartPos(GameData gameData){
        int side = ranGen.nextInt(2);
        
        if(side == 0) {
            x = (float) ranGen.nextInt((gameData.getDisplayWidth() / 2) - 20);
            y = (float) ranGen.nextInt(gameData.getDisplayHeight());
        } else {
            x = (float) ranGen.nextInt(gameData.getDisplayWidth() / 2) + 250;
            y = (float) ranGen.nextInt(gameData.getDisplayHeight());
        }
    }

}
