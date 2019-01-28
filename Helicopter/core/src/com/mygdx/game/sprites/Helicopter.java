package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;

public class Helicopter {
    protected static final int GRAVITY = -15;
    protected Vector3 position;

    protected Vector3 velocity;
    protected Sprite helicopter;
    protected boolean goingRight;
    protected boolean goingUp;

    public  Helicopter(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0 ,0);
        helicopter = new Sprite(new Texture("heli1cut.PNG"));
    }

    public void update(float dt){
        if (position.y > 0) {
            velocity.add(0, GRAVITY, 0); // Gravity adds up each delta-time making it go faster and faster
        }
        velocity.scl(dt);
        position.add(0, velocity.y, 0);
        if (position.y < 0) {
            position.y = 0;
        }
        if (position.y > (MyGdxGame.HEIGHT - helicopter.getHeight())) {
            position.y = MyGdxGame.HEIGHT - helicopter.getHeight();
        }

        velocity.scl(1/dt);

        // X-axis
        if (position.y <= 0) { // Helicopter doesn't move on ground

        } else {
            if (goingRight) {
                if (position.x < MyGdxGame.WIDTH - helicopter.getWidth()){
                    position.add(2, 0,0);
                } else {
                    goingRight = false;
                    helicopter.flip(true, false);
                }
            }
            if (!goingRight) {
                if (position.x > 0){
                    position.add(-2, 0,0);
                } else {
                    goingRight = true;
                    helicopter.flip(true, false);
                }
            }
        }

    }

    public Vector3 getPosition() {
        return position;
    }

    public Sprite getTexture() {
        return helicopter;
    }

    public void fly() {
        velocity.y = 250;
    }
}
