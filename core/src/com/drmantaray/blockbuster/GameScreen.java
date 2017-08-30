package com.drmantaray.blockbuster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import java.util.Arrays;

//The screen that gets rendered
public class GameScreen implements Screen {
    final BlockBuster game;
    private OrthographicCamera camera;
    private GameVaus rectPlayer1;
    private GameVaus rectPlayer2;
    private GameBall ball1;
    private GameBall ball2;
    private AssetManager manager;
    private LevelGenerator levelGenerator;
    private boolean playing;
    private int blockWidth = 23;
    private int blockHeight = 10;

    public static int score;
    private String scoreString;
    public static int lives;
    String livesString;

    public GameScreen(final BlockBuster gam) {
        this.game = gam;
        // initialized camera with height and width equal to the size we want
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 560);
        camera.update();
        //loads all assets
        manager = new AssetManager();
        manager.load("rectPlayer1.png", Texture.class);
        manager.load("rectPlayer2.png", Texture.class);
        manager.load("redBall.png", Texture.class);
        manager.load("blueBall.png", Texture.class);
        manager.load("WallTile.png", Texture.class);
        manager.load("HorizontalWallTile.png", Texture.class);
        manager.load("BasicBlock.png", Texture.class);
        manager.finishLoading();
        //loads level 1 from csv
        levelGenerator = new LevelGenerator();
        levelGenerator.inputCSV("levels/level1.csv");
        levelGenerator.generateGameObjects(manager);
        playing = false;
        lives = 2;
        score = 0;
        // initializing the Vauses and gameballs
        rectPlayer1 = new GameVaus(manager.get("rectPlayer1.png", Texture.class));
        rectPlayer1.setCenter(480 / 2 - rectPlayer1.getWidth()/4, 20);
        rectPlayer1.setAlpha((float)1);
        rectPlayer1.color = 1;

        rectPlayer2 = new GameVaus(manager.get("rectPlayer2.png", Texture.class));
        rectPlayer2.setCenter(480 / 2 + rectPlayer2.getWidth()/4, 20);
        rectPlayer2.setAlpha((float) 0.5);
        rectPlayer2.color = 2;

        ball1 = new GameBall(manager.get("redBall.png", Texture.class));
        ball1.setScale(0.5f);
        ball1.setPosition(rectPlayer1.getCenterX() - ball1.getWidth()/2,
                rectPlayer1.getY() + rectPlayer1.getHeight() + 200);
        ball1.setxyVelocity(0, -300);
        ball1.color = 1;
        ball2 = new GameBall(manager.get("blueBall.png", Texture.class));
        ball2.setScale(0.5f);
        ball2.setPosition(rectPlayer2.getCenterX() - ball2.getWidth() / 2,
                rectPlayer2.getY() + rectPlayer2.getHeight() + 200);
        ball2.setxyVelocity(0, -300);
        ball2.color = 2;
        //adds the gameVauses to the array of game objects
        levelGenerator.gameObjectArrayList.addAll(Arrays.asList(rectPlayer1, rectPlayer2));

    }
    //resets the game upon death
    public void reset() {
        levelGenerator = new LevelGenerator();
        levelGenerator.inputCSV("levels/level1.csv");
        levelGenerator.generateGameObjects(manager);
        playing = false;
        lives = 2;
        score = 0;
        rectPlayer1.setCenter(480 / 2 - rectPlayer1.getWidth()/4, 20);
        rectPlayer2.setCenter(480 / 2 + rectPlayer2.getWidth()/4, 20);
        ball1.setPosition(rectPlayer1.getCenterX() - ball1.getWidth()/2,
                rectPlayer1.getY() + rectPlayer1.getHeight() + 200);
        ball1.setxyVelocity(0, -300);
        ball2.setPosition(rectPlayer2.getCenterX() - ball2.getWidth() / 2,
                rectPlayer2.getY() + rectPlayer2.getHeight() + 200);
        ball2.setxyVelocity(0, -300);
        levelGenerator.gameObjectArrayList.addAll(Arrays.asList(rectPlayer1, rectPlayer2));
    }
    //renders game objects, as well as takes in user input
    public void render (float delta) {
        if (manager.update()) {
            scoreString = "Score: " + score;
            livesString = "Lives: " + lives;
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            camera.update();

            game.batch.enableBlending();
            game.batch.setProjectionMatrix(camera.combined);

            game.batch.begin();
            levelGenerator.drawGameObjects(game.batch);
            rectPlayer1.draw(game.batch);
            rectPlayer2.draw(game.batch);
            ball1.draw(game.batch);
            ball2.draw(game.batch);
            game.font.draw(game.batch, scoreString, 20, 540);
            game.font.draw(game.batch, livesString, 400, 540);
            game.batch.end();
            if (playing) {
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    rectPlayer2.translateX(-400 * Gdx.graphics.getDeltaTime());
                }
                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    rectPlayer2.translateX(400 * Gdx.graphics.getDeltaTime());
                }
                if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                    rectPlayer1.translateX(-400 * Gdx.graphics.getDeltaTime());
                }
                if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    rectPlayer1.translateX(400 * Gdx.graphics.getDeltaTime());
                }

                if (rectPlayer1.getX() < 10) {
                    rectPlayer1.setPosition(10, rectPlayer1.getY());
                }
                if (rectPlayer1.getX() > 480 - 10 - rectPlayer1.getWidth()) {
                    rectPlayer1.setPosition(480 - 10 - rectPlayer1.getWidth(), rectPlayer1.getY());
                }

                if (rectPlayer2.getX() < 10) {
                    rectPlayer2.setPosition(10, rectPlayer2.getY());
                }
                if (rectPlayer2.getX() > 480 - 10 - rectPlayer2.getWidth()) {
                    rectPlayer2.setPosition(480 - 10 - rectPlayer2.getWidth(), rectPlayer2.getY());
                }
                ball1.move(Gdx.graphics.getDeltaTime(), levelGenerator.gameObjectArrayList);
                ball2.move(Gdx.graphics.getDeltaTime(), levelGenerator.gameObjectArrayList);
                if (ball1.getY() < 0 && lives > 0 ) {
                    lives -= 1;
                    playing = false;
                    ball1.setPosition(rectPlayer1.getCenterX() - ball1.getWidth()/2,
                            rectPlayer1.getY() + rectPlayer1.getHeight() + 200);
                    ball1.setxyVelocity(0, -300);
                }
                if (ball2.getY() < 0 && lives > 0 ) {
                    lives -= 1;
                    playing = false;
                    ball2.setPosition(rectPlayer2.getCenterX() - ball2.getWidth() / 2,
                            rectPlayer2.getY() + rectPlayer2.getHeight() + 200);
                    ball2.setxyVelocity(0, -300);
                }
                if (ball1.getY() < 0 && lives < 1 && ball2.getY() < 0) {
                    this.reset();
                }
            }
            else {
                game.batch.begin();
                game.font.draw(game.batch, "Press Space to Begin", 100, 200);
                game.batch.end();
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    playing = true;
                }
            }
        }
    }
    public void dispose() {
    }
    public void show() {

    }
    public void hide() {

    }
    public void pause() {

    }
    public void resize(int sizex, int sizey) {

    }
    public void resume(){}

}
