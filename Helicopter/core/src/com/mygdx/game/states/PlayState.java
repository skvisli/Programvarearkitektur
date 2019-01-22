package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Helicopter;

public class PlayState extends State {
    private Texture background;
    private Helicopter helicopter;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("city-background.jpg");
        helicopter = new Helicopter(0, (MyGdxGame.WIDTH / 2));
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
    }

    @Override
    protected void handleInput() {
        /*if(Gdx.input.justTouched()) {
            helicopter.fly();
        }*/
        helicopter.fly();
    }

    @Override
    public void update(float dt) {
        handleInput();
        helicopter.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        sb.draw(helicopter.getTexture(), helicopter.getPosition().x, helicopter.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
