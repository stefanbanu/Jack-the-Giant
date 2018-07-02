package buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.stefanbanu.jackthegiant.GameMain;

import scenes.GamePlay;
import scenes.Highscore;
import scenes.Options;
import utils.GameUtil;

public class MainMenuButtons {
    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;

    private ImageButton playBtn;
    private ImageButton highscoreBtn;
    private ImageButton optionsBtn;
    private ImageButton quitBtn;
    private ImageButton musicBtn;



    public Stage getStage() {
        return stage;
    }

    public MainMenuButtons(GameMain game) {
        this.game = game;
        gameViewport = new FitViewport(GameUtil.WIDTH, GameUtil.HEIGHT, new OrthographicCamera());
        stage = new Stage(gameViewport, game.getBatch());

        Gdx.input.setInputProcessor(stage);

        createAndPositionButtons();
        addListeners();

        stage.addActor(playBtn);
        stage.addActor(highscoreBtn);
        stage.addActor(optionsBtn);
        stage.addActor(quitBtn);
        stage.addActor(musicBtn);
    }

    void createAndPositionButtons() {
        playBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("buttons/main menu/Start Game.png"))));
        highscoreBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("buttons/main menu/Highscore.png"))));
        optionsBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("buttons/main menu/Options.png"))));
        quitBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("buttons/main menu/Quit.png"))));
        musicBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("buttons/main menu/Music On.png"))));

        playBtn.setPosition(GameUtil.WIDTH / 2 - 80, GameUtil.HEIGHT / 2 + 50, Align.center);
        highscoreBtn.setPosition(GameUtil.WIDTH / 2 - 60, GameUtil.HEIGHT / 2 - 20, Align.center);
        optionsBtn.setPosition(GameUtil.WIDTH / 2 - 40, GameUtil.HEIGHT / 2 - 90, Align.center);
        quitBtn.setPosition(GameUtil.WIDTH / 2 - 20, GameUtil.HEIGHT / 2 - 160, Align.center);
        musicBtn.setPosition(GameUtil.WIDTH  - 13,13, Align.bottomRight);
    }

    void addListeners() {
        playBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GamePlay(game));
            }
        });

        highscoreBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new Highscore(game));
            }
        });

        optionsBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new Options(game));
            }
        });

        quitBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("bla bla");
            }
        });

        musicBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("bla bla");
            }
        });
    }
}
