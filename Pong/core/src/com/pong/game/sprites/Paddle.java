package com.game.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.game.game.MyGdxGame;

public class Paddle {
    private Sprite texture;
    private Vector2 position;
    private Rectangle hitbox;

    public Paddle(){
        texture = new Sprite(new Texture("paddle.jpg"));
    }

    public void setPos(float x, float y){
        position = new Vector2(x, y);
        hitbox = new Rectangle(position.x, MyGdxGame.HEIGHT / 2 - (texture.getHeight() / 2), texture.getWidth(), texture.getHeight());
    }

    public Sprite getTexture() {
        return texture;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void update(float dt){
        updateHitbox();
    }

    protected void updateHitbox () {
        //Updates the hitbox coordinates
        hitbox.x = position.x;
        hitbox.y = position.y;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void moveUp(){
        position.add(0,5);
    }

    public void moveDown(){
        position.add(0, -5);
    }
}
