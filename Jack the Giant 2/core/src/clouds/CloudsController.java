package clouds;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

import player.Player;
import utils.GameUtil;

/**
 * Created by Stefan on 9/4/2016.
 */
public class CloudsController {

    private World world;

    private Array<Cloud> clouds = new Array<Cloud>();

    private final float DISTANCE_BETWEEN_CLOUDS = 250f;
    private float minX, maxX;
    private float lastCloudPositionY;
    private float cameraY;

    private Random random = new Random();


    public CloudsController(World world) {
        this.world = world;
        minX = GameUtil.WIDTH / 2f - 110;
        maxX = GameUtil.WIDTH / 2f + 110;
        createClouds();
        positionClouds(true);
    }

    private void createClouds() {

        for (int i = 0; i < 2; i++) {
            clouds.add(new Cloud(world, "Dark Cloud"));
        }

        int index = 1;

        for (int i = 0; i < 6; i++) {
            clouds.add(new Cloud(world, "Cloud " + index));
            index++;

            if (index == 4) {
                index = 1;
            }
        }

        clouds.shuffle();

    }

    public void positionClouds(boolean firstTimeArranging) {

        //clouds.get(0);

        while (clouds.get(0).getCloudName() == "Dark Cloud") {
            clouds.shuffle();
        }

        float positionY = 0;
        // float tempX = GameUtil.WIDTH / 2f;

        if (firstTimeArranging) {
            positionY = GameUtil.HEIGHT / 2f;
        } else {
            positionY = lastCloudPositionY;
        }

        int controlX = 0;
        for (Cloud c : clouds) {

            if (c.getX() == 0 && c.getY() == 0) {
                float tempX = 0;

                if (controlX == 0) {
                    tempX = randomBetweenNumbers(maxX - 20, maxX);
                    controlX = 1;
                    c.setDrawLeft(false);
                } else if (controlX == 1) {
                    tempX = randomBetweenNumbers(minX + 20, minX);
                    controlX = 0;
                    c.setDrawLeft(true);
                }

                c.setSpritePosition(tempX, positionY);
                positionY -= DISTANCE_BETWEEN_CLOUDS;
                lastCloudPositionY = positionY;
            }

        }
    }

    public void drawClouds(SpriteBatch batch) {
        for (Cloud c : clouds) {
           if(c.isDrawLeft()){
               batch.draw(c, c.getX() - c.getWidth() / 2f - 20, c.getY() - c.getHeight() / 2f);
           }else {
               batch.draw(c, c.getX() - c.getWidth() / 2f + 10, c.getY() - c.getHeight() / 2f);
           }
        }
    }

    public void createAndArrangeNewClouds() {
        for (int i = 0; i < clouds.size; i++) {
            if ((clouds.get(i).getY() - GameUtil.HEIGHT / 2f - 20) > cameraY) {
                // clouds is out of bounds
                clouds.get(i).getTexture().dispose();
                clouds.removeIndex(i);
            }
        }

        if (clouds.size == 4) {
            createClouds();

            positionClouds(false);
        }
    }

    public void setCameraY(float cameraY) {
        this.cameraY = cameraY;
    }

    public Player positionThePlayer(Player player){
        player = new Player(world, clouds.get(0).getX(), clouds.get(0).getY() + 100);
        return player;
    }

    private float randomBetweenNumbers(float min, float max) {
        return random.nextFloat() * (max - min) + min;
    }
}
