package com.helicopter.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.helicopter.game.MyGdxGame;
import com.helicopter.game.sprites.Helicopter;
import com.helicopter.game.sprites.HelicopterEnemy;

import java.util.ArrayList;

public class PlayState extends State {
    private Texture background;
    private Helicopter helicopter;
    private Helicopter helicopter2;
    private Helicopter helicopter3;
    private ArrayList<Helicopter> helicopters;
    private BitmapFont font = new BitmapFont();

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("city-background.jpg");
        helicopter = new Helicopter(0, (MyGdxGame.WIDTH / 2));
        helicopter2 = new HelicopterEnemy(200, 200);
        helicopter3 = new HelicopterEnemy(400, 400);
        helicopters = new ArrayList<Helicopter>();
        helicopters.add(helicopter);
        helicopters.add(helicopter2);
        helicopters.add(helicopter3);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            helicopter.fly();
        }
        //helicopter2.fly();
    }

    @Override
    public void update(float dt) {
        handleInput();
        helicopter.update(dt);
        helicopter2.update(dt);
        helicopter3.update(dt);

        for (Helicopter i : helicopters) {
            for (Helicopter j : helicopters) {
                if (i.collides(j.getHitbox())) {
                    i.bounce(j.getHitbox());
                }
            }
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        // Get current frame of animation for the current stateTime
        sb.begin();
        sb.draw(background, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        font.draw(sb, helicopter.getPosition().toString(), 0, MyGdxGame.HEIGHT);
        sb.draw(helicopter.getTexture(), helicopter.getPosition().x, helicopter.getPosition().y);
        sb.draw(helicopter2.getTexture(), helicopter2.getPosition().x, helicopter2.getPosition().y);
        sb.draw(helicopter3.getTexture(), helicopter3.getPosition().x, helicopter3.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
