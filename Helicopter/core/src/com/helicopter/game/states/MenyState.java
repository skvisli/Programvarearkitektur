package com.helicopter.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.helicopter.game.MyGdxGame;

public class MenyState extends State {
    private Texture background;
    private Texture playBtn;
    public MenyState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("city-background.jpg");
        playBtn = new Texture("play-button_small.jpg");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        sb.draw(playBtn, (MyGdxGame.WIDTH / 2 - (playBtn.getWidth() / 2)), (MyGdxGame.HEIGHT / 2 - (playBtn.getHeight() / 2)));
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
