package buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.stefanbanu.jackthegiant.GameMain;

import scenes.MainMenu;
import utils.GameUtil;

public class UIHud {

    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;

    private Image coingImg, lifeImg, scoreImg;
    private Label scoreLabel, coinLabel, lifeLabel;

    private ImageButton pauseBtn;

    public Stage getStage() {
        return stage;
    }

    public UIHud(final GameMain game) {
        this.game = game;
        gameViewport = new FitViewport(GameUtil.WIDTH, GameUtil.HEIGHT, new OrthographicCamera());
        stage = new Stage(gameViewport, game.getBatch());

        Gdx.input.setInputProcessor(stage);

        createLabels();
        createImages();
        createBtnAndListener();

        Table lifeAndCoinTable = new Table();
        lifeAndCoinTable.top().left();
        lifeAndCoinTable.setFillParent(true);

        lifeAndCoinTable.add(lifeImg).padLeft(10).padTop(10);
        lifeAndCoinTable.add(lifeLabel).padLeft(5);
        lifeAndCoinTable.row();

        lifeAndCoinTable.add(coingImg).padLeft(10).padTop(10);
        lifeAndCoinTable.add(coinLabel).padLeft(5);

        Table scoreTable = new Table();
        scoreTable.top().right();
        scoreTable.setFillParent(true);

        scoreTable.add(scoreImg).padRight(10).padTop(10);
        scoreTable.row();
        scoreTable.add(scoreLabel).padRight(20).padTop(15);

        stage.addActor(lifeAndCoinTable);
        stage.addActor(scoreTable);
        stage.addActor(pauseBtn);
    }

    void createLabels() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/blow.ttf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;

        BitmapFont font = generator.generateFont(parameter);

        coinLabel = new Label("x0", new Label.LabelStyle(font, Color.WHITE));
        lifeLabel = new Label("x2", new Label.LabelStyle(font, Color.WHITE));
        scoreLabel = new Label("100", new Label.LabelStyle(font, Color.WHITE));
    }

    void createImages() {
        coingImg = new Image(new Texture("collectables/Coin.png"));
        lifeImg = new Image(new Texture("collectables/Life.png"));
        scoreImg = new Image(new Texture("buttons/gameplay/Score.png"));
    }

    void createBtnAndListener() {
        pauseBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("buttons/gameplay/Pause.png"))));
        pauseBtn.setPosition(460, 17, Align.bottomRight);
        pauseBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });
    }
}
