package com.drmantaray.blockbuster;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by pchen on 3/7/2017.
 */
public class GameObject extends Sprite {
    public int color;
    public GameObject(Texture texture, int height, int width) {
        super(texture, height, width);
        color = 0;
    }
    public GameObject(Texture texture) {
        super(texture);
        color = 0;
    }
    public GameObject() {
        color = 0;
    }
    public void collided(GameObject gameObject) {}
    private boolean colorEqual(GameObject gameObject) {
        return this.color == gameObject.color;
    }
}