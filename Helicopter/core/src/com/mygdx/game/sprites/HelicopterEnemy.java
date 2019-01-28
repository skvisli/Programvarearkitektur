package com.mygdx.game.sprites;

import com.mygdx.game.MyGdxGame;

public class HelicopterEnemy extends Helicopter{

    public HelicopterEnemy(int x, int y) {
        super(x, y);
    }

    @Override
    public void yAxis(float dt) {
        // Keep texture form exiting screen in X-axis
        if (goingUp) {
            if (position.y < MyGdxGame.HEIGHT - texture.getHeight()){
                position.add(0, 2, 0);
            } else {
                goingUp = false;
            }
        }
        if (!goingUp) {
            if (position.y > 0){
                position.add(0, -2, 0);
            } else {
                goingUp = true;
            }
        }
    }

}
