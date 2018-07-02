package scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.stefanbanu.jackthegiant.GameMain;

import buttons.HighScoreButtons;
import utils.GameUtil;

public class Highscore implements Screen {

    private GameMain gameMain;
    private OrthographicCamera mainCamera;
    private Viewport viewport;
    private Texture bg;

    private HighScoreButtons highScoreButtons;

    public Highscore(GameMain gameMain) {
        this.gameMain = gameMain;

        mainCamera = new OrthographicCamera();
        mainCamera.setToOrtho(false, GameUtil.WIDTH, GameUtil.HEIGHT);
        mainCamera.position.set(GameUtil.WIDTH / 2, GameUtil.HEIGHT / 2, 0);

        viewport = new StretchViewport(GameUtil.WIDTH, GameUtil.HEIGHT, mainCamera);

        bg = new Texture("backgrounds/Highscore BG.png");

        highScoreButtons = new HighScoreButtons(gameMain);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        GameUtil.clearScreen();

        gameMain.getBatch().begin();
        gameMain.getBatch().draw(bg, 0, 0);
        gameMain.getBatch().end();

        gameMain.getBatch().setProjectionMatrix(highScoreButtons.getStage().getCamera().combined);
        highScoreButtons.getStage().draw();
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
        bg.dispose();
        highScoreButtons.getStage().dispose();
    }
}
