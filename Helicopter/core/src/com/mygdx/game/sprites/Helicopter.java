package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;

import java.awt.Frame;

public class Helicopter {
    protected static final int GRAVITY = -15;
    protected Vector3 position;

    protected Vector3 velocity;
    protected Sprite texture;
    protected boolean goingRight;
    protected boolean goingUp;

    protected Rectangle hitbox;
    private Animation helicopterAnimation;
    protected float frameWidth;

    public  Helicopter(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0 ,0);
        texture = new Sprite(new Texture("helicopterAnimation.png"));
        frameWidth = texture.getWidth() / 4;
        hitbox = new Rectangle(x, y, frameWidth, texture.getHeight());
        helicopterAnimation = new Animation(new TextureRegion(texture), 4, 0.5f);
    }

    public void yAxis(float dt) {
        if (position.y > 0) {
            velocity.add(0, -15, 0); // Gravity adds up each delta-time making it go faster and faster
        }
        velocity.scl(dt);
        position.add(0, velocity.y, 0);

        // Keep texture form exiting screen in y axis
        if (position.y < 0) {
            position.y = 0;
        }
        if (position.y > (MyGdxGame.HEIGHT - texture.getHeight())) {
            position.y = MyGdxGame.HEIGHT - texture.getHeight();
        }
    }

    public void xAxis (float dt) {
        // X-axis
        if (position.y <= 0) { // Helicopter doesn't move on ground
        } else {
            // Keep texture form exiting screen in X-axis
            if (goingRight) {
                if (position.x < MyGdxGame.WIDTH - frameWidth){
                    position.add(2, 0, 0);
                } else {
                    goingRight = false;
                    for (TextureRegion frame : helicopterAnimation.frames) {
                        frame.flip(true, false);
                    }
                }
            }
            if (!goingRight) {
                if (position.x > 0){
                    position.add(-2, 0, 0);
                } else {
                    goingRight = true;
                    for (TextureRegion frame : helicopterAnimation.frames) {
                        frame.flip(true, false);
                    }
                }
            }
        }
        velocity.scl(1/dt);

    }

    public void update(float dt){
        helicopterAnimation.update(dt);
        yAxis(dt);
        xAxis(dt);
        updateHitbox();
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return helicopterAnimation.getFrame();
    }

    public void fly() {
        velocity.y = 250;
    }

    public boolean collides (Rectangle hitbox) {
        return hitbox.overlaps(this.hitbox);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    protected void updateHitbox () {
        //Updates the hitbox coordinates
        hitbox.x = position.x;
        hitbox.y = position.y;
    }

    public void bounce (Rectangle hitbox2) {
        float hitboxBottom = hitbox.y + hitbox.getHeight();
        float hitbox2Bottom = hitbox2.y + hitbox2.getHeight();
        float hitboxRight = hitbox.x + hitbox.getWidth();
        float hitbox2Right = hitbox2.x + hitbox2.getWidth();

        float b_collision = hitbox2Bottom - hitbox.y;
        float t_collision = hitboxBottom - hitbox2.y;
        float l_collision = hitboxRight - hitbox2.x;
        float r_collision = hitbox2Right - hitbox2.x;
        if (t_collision < b_collision && t_collision < l_collision && t_collision < r_collision )
        {
            //Top collision
            position.add(0, -20, 0);
        }
        if (b_collision < t_collision && b_collision < l_collision && b_collision < r_collision)
        {
            //bottom collision
            position.add(0, 20, 0);
        }
        if (l_collision < r_collision && l_collision < t_collision && l_collision < b_collision)
        {
            //Left collision
            position.add(-20, 0 ,0);
        }
        if (r_collision < l_collision && r_collision < t_collision && r_collision < b_collision )
        {
            //Right collision
            position.add(20, 0, 0);
        }
    }

    public void dispose(){
    }
}
