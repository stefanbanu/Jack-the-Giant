package clouds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import utils.GameUtil;

/**
 * Created by Stefan on 9/4/2016.
 */
public class Cloud extends Sprite {

    private World world;
    private Body body;
    private String cloudName;

    private boolean drawLeft;

    public Cloud(World world, String cloudName) {
        super(new Texture("clouds/" + cloudName + ".png"));
        this.world = world;
        this.cloudName = cloudName;
    }

    void createBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        bodyDef.position.set((getX() - 45) / GameUtil.PPM,
                getY()  / GameUtil.PPM);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((getWidth()/2 - 20) / GameUtil.PPM,
                (getHeight()/2 - 5)/ GameUtil.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();
    }

    public void setSpritePosition(float x, float y) {
        setPosition(x, y);
        createBody();
    }

    public String getCloudName() {
        return this.cloudName;
    }

    public boolean isDrawLeft() {
        return drawLeft;
    }

    public void setDrawLeft(boolean drawLeft) {
        this.drawLeft = drawLeft;
    }
}
