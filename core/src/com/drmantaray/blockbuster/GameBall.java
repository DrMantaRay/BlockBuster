package com.drmantaray.blockbuster;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * Created by pchen on 3/7/2017.
 */
public class GameBall extends GameObject {
    // on top of the GameObject class, Gameball has x/y velocities and a movement vector made of velocity + angle
    private double velocity;
    private double xVelocity;
    private double yVelocity;
    private double angle;
    // initialize gameball with a texture and set initial velocities
    public GameBall(Texture texture) {
        super(texture);
        velocity = 0;
        xVelocity = 0;
        yVelocity = 0;
        angle= 90;
    }
    // initialize gameball without texture
    public GameBall() {
        velocity = 0;
        xVelocity = 0;
        yVelocity = 0;
        angle= 90;
    }
    // set velocity and angle. also updates x and y velocities
    public void setVelocity(double velocity, double angle) {
        this.velocity = velocity;
        this.angle = angle;
        xVelocity = velocity * Math.cos(Math.toRadians(angle));
        yVelocity = velocity * Math.sin(Math.toRadians(angle));
    }
    // set x and y velocities. also updates velocity and angle.
    public void setxyVelocity(double xVelocity, double yVelocity) {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        angle = Math.toDegrees(Math.atan(this.yVelocity/this.xVelocity));
        velocity = Math.sqrt(xVelocity * xVelocity + yVelocity * yVelocity);
    }
    // moves the ball by using the x and y velocities, as well as the duration of the last frame.
    public void move(float delta, ArrayList<GameObject> gameObjectArrayList) {
        // initializes array for possible collisions
        ArrayList<GameObject> possibleCollisions = new ArrayList<GameObject>();
        //translates x and y by x and y velocities multiplied by delta
        translateX((float) this.xVelocity * delta);
        translateY((float) this.yVelocity * delta);
        // brute force search for collisions. This is log(n). Only adds GameObjects if their bounding rectangles intersect
        // with GameBall object and color is same as GameBall object, or if either the GameBall or GameObject have no color
        for (GameObject gameObject : gameObjectArrayList) {
            if ((this.color == 0 || gameObject.color == 0 || gameObject.color == this.color)
                    && this.getBoundingRectangle().overlaps(gameObject.getBoundingRectangle())) {
                possibleCollisions.add(gameObject);

            }
        }
        // if there are no possible collisions, return
        if (possibleCollisions.isEmpty()) {
            return;
        }
        //initialize a minDistance to compare with, in order to find closest object to collide with, in case there is a
        // multi-object collision
        float minDistance = 100000;
        GameObject minGameObject = new GameObject();
        GameObject otherGameObject;
        //searches through he possibleCollisions to find closest object
        for (GameObject gameObject : possibleCollisions) {
            if (minDistance > this.getCenterDistance(gameObject)) {
                minDistance = this.getCenterDistance(gameObject);
                minGameObject = gameObject;
            }
        }

        //sends the closest colliding object to GameCollsion class to handle collision
        if (minGameObject instanceof GameWall) {
            GameCollision.collides(this, (GameWall) minGameObject, delta);
        } else if (minGameObject instanceof GameVaus) {
            GameCollision.collides(this, (GameVaus) minGameObject, delta);
        }
        else if (minGameObject instanceof BasicBlock) {
            GameCollision.collides(this, (BasicBlock) minGameObject, delta);
            ((BasicBlock) minGameObject).dies(gameObjectArrayList);
        }

    }
    //functions below are getters for velocities and angles
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
