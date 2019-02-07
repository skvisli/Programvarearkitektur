package com.pong.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public final class GameStateManager {
    private static final GameStateManager GSM = new GameStateManager();
    private State state;

    private GameStateManager(){
        setState(new MenyState());
    }

    public static GameStateManager getInstance() {
        return GSM;
    }

    public void setState(final State newState){
        state = newState;
    }

    public void update(float dt){
        state.update(dt);
    }

    public void render(SpriteBatch sb){
        state.render(sb);
    }
}
