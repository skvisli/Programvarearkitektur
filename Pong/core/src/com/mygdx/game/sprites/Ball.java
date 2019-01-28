package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.states.PlayState;

import java.awt.Frame;

public class Ball {
    protected static final int GRAVITY = -15;
    protected Vector2 position;
    protected Vector2 velocity;
    protected Sprite texture;
    protected Rectangle hitbox;

    public Ball(int x, int y) {
        position = new Vector2(x, y);
        velocity = new Vector2(10, 5);
        texture = new Sprite(new Texture("ball.jpg"));
        hitbox = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    public void update(float dt){
        position.add(0, velocity.y);
        position.add(velocity.x, 0);
        if (position.y < 0 || position.y > (MyGdxGame.HEIGHT - texture.getHeight())) {
            velocity.y = velocity.y * (-1);
        }
        updateHitbox();
        if (position.x >= MyGdxGame.WIDTH - texture.getWidth()) {
            PlayState.goalRight();
            resetPos();
        }
        if (position.x <= 0) {
            PlayState.goalLeft();
            resetPos();
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public Sprite getTexture() {
        return texture;
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

    public void dispose(){
    }

    public void bounceRight(){
        velocity.x = 10;
    }
    public void bounceLeft(){
        velocity.x = -10;
    }

    protected void resetPos(){
        position = new Vector2(MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT /2);
        velocity = new Vector2(-10,5);
    }
}
