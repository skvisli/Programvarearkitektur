package com.pong.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.pong.game.MyGdxGame;
import com.pong.game.sprites.Ball;
import com.pong.game.sprites.Paddle;

public final class PlayState extends State {
    private Texture background;
    private Ball ball;
    private Paddle paddleLeft;
    private Paddle paddleRight;
    private BitmapFont font = new BitmapFont();
    private static int goalsLeft = 0;
    private static int goalsRight = 0;

    protected PlayState() {
        super();
        background = new Texture("gameboard.jpg");
        ball = new Ball(MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2);
        paddleLeft = new Paddle();
        paddleRight = new Paddle();
        paddleLeft.setPos(0, MyGdxGame.HEIGHT / 2 - (paddleLeft.getTexture().getHeight() / 2));
        paddleRight.setPos(MyGdxGame.WIDTH - paddleRight.getTexture().getWidth(), MyGdxGame.HEIGHT / 2 - (paddleLeft.getTexture().getHeight() / 2));
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
    }

    @Override
    protected void handleInput() {
        // Keyboard
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            paddleRight.moveUp();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            paddleRight.moveDown();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            paddleLeft.moveUp();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            paddleLeft.moveDown();
        }

        // Touch controls
        for(int i = 0; i < 2; i++){ // Checks 2 fingers
            Vector3 touchPos = new Vector3(Gdx.input.getX(i), Gdx.input.getY(i), 0);
            cam.unproject(touchPos);
            if (Gdx.input.isTouched(i)){
                if (touchPos.x < MyGdxGame.WIDTH / 2){
                    // Left paddle
                    if (touchPos.y > paddleLeft.getPosition().y + (paddleLeft.getHitbox().getHeight() / 2)) {
                        paddleLeft.moveUp();
                    }
                    if (touchPos.y < paddleLeft.getPosition().y + (paddleLeft.getHitbox().getHeight() / 2)) {
                        paddleLeft.moveDown();
                    }

                } else {
                    // Right paddle
                    if (touchPos.y > paddleRight.getPosition().y + (paddleRight.getHitbox().getHeight() / 2)) {
                        paddleRight.moveUp();
                    }
                    if (touchPos.y < paddleRight.getPosition().y + (paddleRight.getHitbox().getHeight() / 2)) {
                        paddleRight.moveDown();
                    }
                }
            }
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        ball.update(dt);
        paddleLeft.update(dt);
        paddleRight.update(dt);
        if (ball.collides(paddleLeft.getHitbox())) {
            ball.bounceRight();

        }
        if (ball.collides(paddleRight.getHitbox())) {
            ball.bounceLeft();
        }
        if (ball.isGoalLeft()){
            goalsLeft +=1;
            pauseGame();
        }
        if (ball.isGoalRight()){
            goalsRight += 1;
            pauseGame();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        // Get current frame of animation for the current stateTime
        sb.begin();
        sb.draw(background, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        font.draw(sb, goalsLeft + " : " + goalsRight, MyGdxGame.WIDTH / 2 - font.getRegion().getRegionWidth() / 2, MyGdxGame.HEIGHT);
        sb.draw(ball.getTexture(), ball.getPosition().x, ball.getPosition().y);
        sb.draw(paddleLeft.getTexture(), paddleLeft.getPosition().x, paddleLeft.getPosition().y);
        sb.draw(paddleRight.getTexture(), paddleRight.getPosition().x, paddleRight.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }

    public void pauseGame(){
        GameStateManager.getInstance().setState(new MenyState());
    }
}
