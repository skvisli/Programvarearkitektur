package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Helicopter;
import com.mygdx.game.sprites.HelicopterEnemy;

import java.util.Random;

import sun.rmi.runtime.Log;

public class PlayState extends State {
    private Texture background;
    private Helicopter helicopter;
    private HelicopterEnemy helicopter2;
    private BitmapFont font = new BitmapFont();

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("city-background.jpg");
        helicopter = new Helicopter(0, (MyGdxGame.WIDTH / 2));
        helicopter2 = new HelicopterEnemy(200, 200);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            helicopter.fly();
        }
        helicopter2.fly();
    }

    @Override
    public void update(float dt) {
        handleInput();
        helicopter.update(dt);
        helicopter2.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        font.draw(sb, helicopter.getPosition().toString(), 0, MyGdxGame.HEIGHT);
        sb.draw(helicopter.getTexture(), helicopter.getPosition().x, helicopter.getPosition().y);
        sb.draw(helicopter2.getTexture(), helicopter2.getPosition().x, helicopter2.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
