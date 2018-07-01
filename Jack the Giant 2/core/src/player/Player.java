package player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import utils.GameUtil;

/**
 * Created by stefanbanu on 30.12.2016.
 */

public class Player extends Sprite {

    private World world;
    private Body body;

    private TextureAtlas playerAtlas;
    private Animation<TextureRegion> animation;
    private float elapsedTime;

    private boolean isWalking;

    public Player(World world, float x, float y){
        super(new Texture("player/Player 1.png"));
        this.world = world;
        setPosition(x, y);
        createBody();
        playerAtlas = new TextureAtlas("animation/playerAnimation.atlas");
    }

    void createBody(){

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX() / GameUtil.PPM, getY() / GameUtil.PPM);

        body = world.createBody(bodyDef);
        body.setFixedRotation(true);

        //System.out.println("test");

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((getWidth() / 2f - 10) / GameUtil.PPM, (getHeight() / 2f) / GameUtil.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 4f;
        fixtureDef.friction = 2f;
        fixtureDef.shape = shape;

        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();
    }

    public void drawPlayerIdle(SpriteBatch batch){
        if (!isWalking){
            batch.draw(this, getX() + getWidth() / 2f - 5, getY() - getHeight() / 2f);
        }
    }

    public void drawPlayerAnimation(SpriteBatch batch) {
        if (isWalking) {
            elapsedTime += Gdx.graphics.getDeltaTime();

            Array<TextureAtlas.AtlasRegion> frames = playerAtlas.getRegions();

            for (TextureRegion frame : frames) {
                if (body.getLinearVelocity().x < 0 && !frame.isFlipX()) {
                    frame.flip(true,false);
                }else if (body.getLinearVelocity().x > 0 && frame.isFlipX()) {
                    frame.flip(true,false);
                }
            }


            animation = new Animation(1f / 10f, playerAtlas.getRegions());

            batch.draw(animation.getKeyFrame(elapsedTime, true),
                    getX() + getWidth() / 2f - 5,
                    getY() - getHeight() / 2f);

        }
    }

    public void updatePlayer(){
        setPosition(body.getPosition().x * GameUtil.PPM, body.getPosition().y * GameUtil.PPM);
    }

    public void movePlayer(float x) {

        if (x < 0 && !isFlipX()) {
            flip(true, false);
        }else if (x > 0 &&  isFlipX()) {
            flip(true, false);
        }

        isWalking = true;
        body.setLinearVelocity(x, body.getLinearVelocity().y);
    }

    public void setWalking(boolean walking) {
        isWalking = walking;
    }

}





