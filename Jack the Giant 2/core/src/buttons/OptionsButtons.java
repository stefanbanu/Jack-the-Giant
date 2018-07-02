package buttons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.stefanbanu.jackthegiant.GameMain;

import scenes.MainMenu;
import utils.GameUtil;

public class OptionsButtons {

    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;

    private ImageButton easyBtn, mediumBtn, hardBtn, backBtn;
    private Image sign;

    public Stage getStage() {
        return stage;
    }

    public OptionsButtons(GameMain game) {
        this.game = game;
        gameViewport = new FitViewport(GameUtil.WIDTH, GameUtil.HEIGHT, new OrthographicCamera());
        stage = new Stage(gameViewport, game.getBatch());

        Gdx.input.setInputProcessor(stage);

        createAndPositionButtons();
        addAllListeners();

        stage.addActor(easyBtn);
        stage.addActor(mediumBtn);
        stage.addActor(hardBtn);
        stage.addActor(backBtn);
        stage.addActor(sign);

    }

    void createAndPositionButtons() {
        easyBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("buttons/options menu/Easy.png"))));
        mediumBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("buttons/options menu/Medium.png"))));
        hardBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("buttons/options menu/Hard.png"))));
        backBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("buttons/options menu/Back.png"))));
        sign = new Image(new Texture("buttons/options menu/Check Sign.png"));

        backBtn.setPosition(17,17, Align.bottomLeft);
        easyBtn.setPosition(GameUtil.WIDTH / 2,GameUtil.HEIGHT / 2 + 40 , Align.center);
        mediumBtn.setPosition(GameUtil.WIDTH / 2,GameUtil.HEIGHT / 2 - 40 , Align.center);
        hardBtn.setPosition(GameUtil.WIDTH / 2,GameUtil.HEIGHT / 2 - 120 , Align.center);
        sign.setPosition(GameUtil.WIDTH / 2 + 76,mediumBtn.getY() + 13, Align.bottomLeft);
    }

    void addAllListeners() {
        backBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
               game.setScreen(new MainMenu(game));
            }
        });

        easyBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                sign.setY(easyBtn.getY() + 13);
            }
        });

        mediumBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                sign.setY(mediumBtn.getY() + 13);
            }
        });

        hardBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                sign.setY(hardBtn.getY() + 13);
            }
        });
    }

}
