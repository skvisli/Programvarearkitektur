package com.game.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.game.MyGdxGame;

public class MenyState extends State {
    private Texture background;
    private Texture playBtn;
    public MenyState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("gameboard.jpg");
        playBtn = new Texture("play-button_small.jpg");
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
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
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        sb.draw(playBtn, cam.position.x - playBtn.getWidth() / 2, cam.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}