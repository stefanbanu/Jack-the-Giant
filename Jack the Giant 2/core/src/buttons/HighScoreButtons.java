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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.stefanbanu.jackthegiant.GameMain;

import scenes.GamePlay;
import scenes.Highscore;
import scenes.MainMenu;
import utils.GameUtil;

public class HighScoreButtons {
    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;

    private Label scoreLabel, coinLabel;

    private ImageButton backBtn;

    public Stage getStage() {
        return stage;
    }

    public HighScoreButtons(final GameMain game) {
        this.game = game;
        gameViewport = new FitViewport(GameUtil.WIDTH, GameUtil.HEIGHT, new OrthographicCamera());
        stage = new Stage(gameViewport, game.getBatch());

        Gdx.input.setInputProcessor(stage);

        createAndPositionUiElements();

        stage.addActor(backBtn);
        stage.addActor(scoreLabel);
        stage.addActor(coinLabel);
    }


        void createAndPositionUiElements(){
            backBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("buttons/options menu/Back.png"))));

            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/blow.ttf"));

            FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                    new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = 50;

            BitmapFont font = generator.generateFont(parameter);
           // BitmapFont coinFont = generator.generateFont(parameter);

            scoreLabel = new Label("100", new Label.LabelStyle(font, Color.WHITE));

            coinLabel = new Label("100", new Label.LabelStyle(font, Color.WHITE));

            backBtn.setPosition(17, 17, Align.bottomLeft);

            scoreLabel.setPosition(GameUtil.WIDTH / 2f - 40, GameUtil.HEIGHT / 2f -120);
            coinLabel.setPosition(GameUtil.WIDTH / 2f - 40, GameUtil.HEIGHT / 2f -220);


            backBtn.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    game.setScreen(new MainMenu(game));
                }
            });

        }


    }


