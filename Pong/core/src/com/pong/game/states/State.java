package com.pong.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera cam;

    protected State(){
        cam = new OrthographicCamera();
    }

    abstract void handleInput();
    abstract void update(float dt);
    abstract void render(SpriteBatch sb);
    abstract void dispose();
}
