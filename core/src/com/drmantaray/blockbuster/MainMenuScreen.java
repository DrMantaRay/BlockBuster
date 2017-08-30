package com.drmantaray.blockbuster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

//Main Menu Screen. Not currently in use
public class MainMenuScreen implements Screen {
    final BlockBuster game;
    OrthographicCamera camera;
    public MainMenuScreen(final BlockBuster gam) {
        this.game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 560);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to BlockBuster!" , 100, 150);
        game.batch.end();

    }
    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
