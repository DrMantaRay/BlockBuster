package com.drmantaray.blockbuster;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by pchen on 3/7/2017.
 */
public class GameVaus extends GameObject {
    public double friction;
    public GameVaus(Texture texture, int height, int width) {
        super(texture, height, width);
        friction = 0.5;
    }
    public GameVaus(Texture texture) {
        super(texture);
        friction = 0.5;
    }
    public void collided(GameBall gameObject) {
        if (gameObject.getX() + gameObject.getWidth() > this.getX()) {
            gameObject.setxyVelocity(-gameObject.getxVelocity(), gameObject.getyVelocity());
        } else if (gameObject.getX() < this.getX() + this.getWidth()) {
            gameObject.setxyVelocity(-gameObject.getxVelocity(), gameObject.getyVelocity());
        } else if (gameObject.getY() < this.getY() + this.getHeight()) {
            gameObject.setxyVelocity(gameObject.getxVelocity(), -gameObject.getyVelocity());
        } else if (gameObject.getY() + gameObject.getHeight() > this.getY()) {
            gameObject.setxyVelocity(gameObject.getxVelocity(), -gameObject.getyVelocity());
        }
    }
}
