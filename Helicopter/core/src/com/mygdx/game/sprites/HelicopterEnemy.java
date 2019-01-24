package com.mygdx.game.sprites;

import com.mygdx.game.MyGdxGame;

import java.util.Random;

public class HelicopterEnemy extends Helicopter{

    public HelicopterEnemy(int x, int y) {
        super(x, y);
    }

    @Override
    public void update(float dt) {
        // Y-axis
        if (goingUp) {
            if (position.y < MyGdxGame.HEIGHT - helicopter.getHeight()){
                position.add(0, 2,0);
            } else {
                goingUp = false;
            }
        }
        if (!goingUp) {
            if (position.y > 0){
                position.add(0, -2,0);
            } else {
                goingUp = true;
            }
        }

        // X-axis
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
