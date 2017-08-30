package com.drmantaray.blockbuster;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

//All objects in game are GameObjects, which are an extension of base Sprite class.
// Adds color, getCenterX/Y and intersects methods
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
    public float getCenterX() {
        return this.getX() + getWidth() / 2;
    }
    public float getCenterY() {
        return this.getY() + getWidth() / 2;
    }
    public float getCenterDistance(GameObject gameObject) {
        return (float) Math.sqrt(Math.pow(gameObject.getCenterX() - this.getCenterX() , 2) + Math.pow(gameObject.getCenterY() - this.getCenterY(),2));
    }
    public boolean intersects(GameObject gameobject) {
        return this.getBoundingRectangle().overlaps(gameobject.getBoundingRectangle()) || this.getBoundingRectangle().contains(gameobject.getBoundingRectangle())
                || gameobject.getBoundingRectangle().contains(this.getBoundingRectangle());
    }
    public GameObject() {
        color = 0;
    }
    public void collided(GameObject gameObject) {}
    public void collided(GameBall gameball, float delta) {};
    private boolean colorEqual(GameObject gameObject) {
        return this.color == gameObject.color;
    }
}