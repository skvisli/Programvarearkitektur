package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Ball;
import com.mygdx.game.sprites.Paddle;

public class PlayState extends State {
    private Texture background;
    private Ball ball;
    private Paddle paddleLeft;
    private Paddle paddleRight;
    private BitmapFont font = new BitmapFont();
    private static int goalsLeft = 0;
    private static int goalsRight = 0;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("gameboard.jpg");
        ball = new Ball(MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2);
        paddleLeft = new Paddle(0);
        paddleRight = new Paddle(950);
        cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
    }

    @Override
    protected void handleInput() {
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
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        // Get current frame of animation for the current stateTime
        sb.begin();
        sb.draw(background, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        font.draw(sb, goalsLeft + " : " + goalsRight, 0, MyGdxGame.HEIGHT);
        sb.draw(ball.getTexture(), ball.getPosition().x, ball.getPosition().y);
        sb.draw(paddleLeft.getTexture(), paddleLeft.getPosition().x, paddleLeft.getPosition().y);
        sb.draw(paddleRight.getTexture(), paddleRight.getPosition().x, paddleRight.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }

    public static void goalLeft(){
        goalsLeft += 1;
    }
    public static void goalRight(){
        goalsRight += 1;
    }
}
