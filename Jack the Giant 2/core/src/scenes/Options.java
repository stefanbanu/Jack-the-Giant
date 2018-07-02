package scenes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.stefanbanu.jackthegiant.GameMain;

import buttons.OptionsButtons;
import utils.GameUtil;

public class Options implements Screen {


    private GameMain gameMain;

    private OrthographicCamera mainCamera;
    private Viewport viewport;

    private Texture textureBg;

    private OptionsButtons optionsButtons;

    public Options(GameMain gameMain) {
        this.gameMain = gameMain;

        mainCamera = new OrthographicCamera();
        mainCamera.setToOrtho(false, GameUtil.WIDTH, GameUtil.HEIGHT);
        mainCamera.position.set(GameUtil.WIDTH / 2f, GameUtil.HEIGHT / 2f, 0);

        viewport = new StretchViewport(GameUtil.WIDTH, GameUtil.HEIGHT, mainCamera);

        textureBg = new Texture("backgrounds/Options BG.png");

        optionsButtons = new OptionsButtons(gameMain);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        GameUtil.clearScreen();

        gameMain.getBatch().begin();
        gameMain.getBatch().draw(textureBg, 0, 0);

        gameMain.getBatch().end();

        gameMain.getBatch().setProjectionMatrix(optionsButtons.getStage().getCamera().combined);
        optionsButtons.getStage().draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
        textureBg.dispose();
        optionsButtons.getStage().dispose();
    }
}
