package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.stefanbanu.jackthegiant.GameMain;

import clouds.CloudsController;
import player.Player;
import utils.GameUtil;

/**
 * Created by Stefan on 8/30/2016.
 */
public class GamePlay implements Screen {

    private GameMain game;

    private OrthographicCamera mainCamera;
    private Viewport gameViewport;

    private OrthographicCamera box2DCamera;
    private Box2DDebugRenderer debugRenderer;

    private World world;

    private Sprite[] bgs;
    private float lastYPosition;

    /*// delete this later
    Cloud c;*/

    private CloudsController cloudsController;
    private Player player;

    public GamePlay(GameMain game) {
        this.game = game;

        mainCamera = new OrthographicCamera(GameUtil.WIDTH, GameUtil.HEIGHT);
        mainCamera.position.set(GameUtil.WIDTH / 2f, GameUtil.HEIGHT / 2f, 0);

        gameViewport = new StretchViewport(GameUtil.WIDTH, GameUtil.HEIGHT, mainCamera);

        box2DCamera = new OrthographicCamera();
        box2DCamera.setToOrtho(false, GameUtil.WIDTH / GameUtil.PPM,
                GameUtil.HEIGHT / GameUtil.PPM);
        box2DCamera.position.set(GameUtil.WIDTH / 2f, GameUtil.HEIGHT / 2f, 0);

        debugRenderer = new Box2DDebugRenderer();

        world = new World(new Vector2(0, -9.8f), true);

       /* c = new Cloud(world, "Cloud 1");
        c.setSpritePosition(GameUtil.WIDTH / 2f, GameUtil.HEIGHT / 2f);*/

        cloudsController = new CloudsController(world);

        player = cloudsController.positionThePlayer(player);

        createBackgrounds();
    }

    void handleInput(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.movePlayer(-2);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.movePlayer(2);
        } else {
            player.setWalking(false);
        }
    }

    void update(float dt) {
        handleInput(dt);
      //  moveCamera();
        checkBackgroundsOutOfBounds();
        cloudsController.setCameraY(mainCamera.position.y);
        cloudsController.createAndArrangeNewClouds();
    }

    private void moveCamera() {
        mainCamera.position.y -= 1.5f;
    }

    void createBackgrounds() {
        bgs = new Sprite[3];

        for (int i = 0; i < bgs.length; i++) {
            bgs[i] = new Sprite(new Texture("backgrounds/Game BG.png"));
            bgs[i].setPosition(0, -(i * bgs[i].getHeight()));
            lastYPosition = Math.abs(bgs[i].getY());
        }
    }

    void drawBackgrounds() {

        for (int i = 0; i < bgs.length; i++) {
            game.getBatch().draw(bgs[i], bgs[i].getX(), bgs[i].getY());
        }
    }

    void checkBackgroundsOutOfBounds() {
        for (int i = 0; i < bgs.length; i++) {
            if ((bgs[i].getY() - bgs[i].getHeight() / 2f - 5) >mainCamera.position.y ){
                float newPosition = bgs[i].getHeight() + lastYPosition;
                bgs[i].setPosition(0, -newPosition);
                lastYPosition = Math.abs(newPosition);
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        update(delta);

        GameUtil.clearScreen();

        game.getBatch().begin();

        drawBackgrounds();

        //game.getBatch().draw(c, c.getX() - c.getWidth() / 2f, c.getY() - c.getHeight() / 2f);

        cloudsController.drawClouds(game.getBatch());

        player.drawPlayerIdle(game.getBatch());
        player.drawPlayerAnimation(game.getBatch());

        game.getBatch().end();

        debugRenderer.render(world, box2DCamera.combined);

        game.getBatch().setProjectionMatrix(mainCamera.combined);
        mainCamera.update();

        player.updatePlayer();

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);

    }



    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        world.dispose();
        for (int i = 0; i < bgs.length; i++) {
            bgs[i].getTexture().dispose();
        }
        player.getTexture().dispose();
        debugRenderer.dispose();
    }
}
