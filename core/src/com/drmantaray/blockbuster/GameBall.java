package com.drmantaray.blockbuster;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * Created by pchen on 3/7/2017.
 */
public class GameBall extends GameObject {
    private double velocity;
    private double xVelocity;
    private double yVelocity;
    private double angle;
    public GameBall(Texture texture, int height, int width) {
        super(texture, height, width);
        velocity = 0;
        xVelocity = 0;
        yVelocity = 0;
        angle= 90;
    }
    public GameBall(Texture texture) {
        super(texture);
        velocity = 0;
        xVelocity = 0;
        yVelocity = 0;
        angle= 90;
    }
    public GameBall() {
        velocity = 0;
        xVelocity = 0;
        yVelocity = 0;
        angle= 90;
    }
    public void setVelocity(double velocity, double angle) {
        this.velocity = velocity;
        this.angle = angle;
        xVelocity = this.angle * Math.cos(90);
        yVelocity = this.angle * Math.sin(90);
    }
    public void setxyVelocity(double xVelocity, double yVelocity) {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        angle = Math.atan(this.xVelocity/this.yVelocity);
    }
    public void move(double delta, ArrayList<GameObject> gameObjectArrayList) {
        translateX((float) this.xVelocity);
        translateY((float) this.yVelocity);
        for (GameObject gameObject : gameObjectArrayList) {
            if (this.color == gameObject.color && this.getBoundingRectangle().overlaps(gameObject.getBoundingRectangle())) {
                gameObject.collided(this);
            }
        }
    }
    @Override
    public void collided(GameObject gameObject) {
    }
    public double getVelocity() {
        return this.velocity;
    }
    public double getAngle() {
        return this.angle;
    }
    public double getyVelocity() {
        return this.yVelocity;
    }
    public double getxVelocity() {
        return this.xVelocity;
    }
}
