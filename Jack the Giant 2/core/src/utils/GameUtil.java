package utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by Stefan on 8/30/2016.
 */
public class GameUtil {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    public static final int PPM = 100;

    public static void clearScreen() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
