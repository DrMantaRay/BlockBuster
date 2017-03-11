package com.drmantaray.blockbuster;

/**
 * Created by pchen on 3/8/2017.
 */
public class GameCollision {
    public GameCollision() {
    }
    private static float beforeCollideDelta(GameBall gameball, GameObject gameObject, float min, float max) {
        if (max - min < 0.00001) {
            //System.out.println(min);
            return min;
        }
        float delta = (min + max) / 2;
        float originalX = gameball.getX();
        float originalY = gameball.getY();
        gameball.translate(delta * (float) gameball.getxVelocity(), delta * (float) gameball.getyVelocity());
        boolean overlap = gameball.intersects(gameObject);
        gameball.setPosition(originalX, originalY);
        if (overlap) {
            return beforeCollideDelta(gameball, gameObject, min, delta);
        }
        else {
            return beforeCollideDelta(gameball, gameObject, delta, max);
        }

    }
    private static boolean collidesIterator(GameBall gameball, GameObject gameObject, float min, float max) {
        float originalX = gameball.getX();
        float originalY = gameball.getY();
        //System.out.println("originalX " + originalX);
        //System.out.println("originalY " + originalY);
        //System.out.println("Before collision " + gameball.intersects(gameObject));
        float delta = (min + max) / 2;
        gameball.translateX(delta * (float)gameball.getxVelocity());
        boolean xOverlap = gameball.intersects(gameObject);
        gameball.setX((float) originalX);
        gameball.translateY(delta * (float)gameball.getyVelocity());
        boolean yOverlap = gameball.intersects(gameObject);
        gameball.setY((float) originalY);
        /*
        System.out.println("new X " + gameball.getX());
        System.out.println("new Y " + gameball.getY());
        System.out.println("---delta " + delta);
        System.out.println(gameball.getX());
        System.out.println(gameObject.getX());
        System.out.println(gameball.getY());
        System.out.println(gameObject.getY());
        System.out.println(min);
        System.out.println(max);
        System.out.println(xOverlap);
        System.out.println(yOverlap);
        */
        if (xOverlap == true && yOverlap == true) {
            return collidesIterator(gameball, gameObject, min , delta);
        }
        else if (xOverlap == false && yOverlap == false) {
            return collidesIterator(gameball, gameObject, delta, max);
        }
        else if (xOverlap == true && yOverlap == false) {
            return true;
        }
        return false;
    }
    public static void collides(GameBall gameball, GameWall gamewall, float delta) {
        gameball.translate((float) -gameball.getxVelocity() * delta,
                (float) -gameball.getyVelocity() * delta);
        double xLocation = gameball.getX();
        double yLocation = gameball.getY();
        float newDelta = beforeCollideDelta(gameball, (GameObject) gamewall, 0, delta);
        gameball.translate((float) gameball.getxVelocity() * newDelta,
                (float) gameball.getyVelocity() * newDelta);
        boolean isX = collidesIterator(gameball, gamewall, 0, delta - newDelta);
        if (isX == true) {
            gameball.setxyVelocity(-gameball.getxVelocity(), gameball.getyVelocity());
        }
        else {
            gameball.setxyVelocity(gameball.getxVelocity(), -gameball.getyVelocity());
        }
        gameball.setPosition((float) xLocation, (float) yLocation);

    }
    public static void collides(GameBall gameball, GameVaus gameVaus, float delta) {
        gameball.translate((float) -gameball.getxVelocity() * delta,
                (float) -gameball.getyVelocity() * delta);
        double xLocation = gameball.getX();
        double yLocation = gameball.getY();
        float newDelta = beforeCollideDelta(gameball, (GameObject) gameVaus, 0, delta);
        gameball.translate((float)  gameball.getxVelocity() * newDelta,
                (float) gameball.getyVelocity() * newDelta);
        double justBeforeCollideX = gameball.getCenterX();
        boolean isX = collidesIterator(gameball, gameVaus, 0, delta - newDelta);

        if (isX == true) {
            gameball.setxyVelocity(-gameball.getxVelocity(), gameball.getyVelocity());
        }
        else {
            System.out.println(180*((gameVaus.getX() +gameVaus.getScaledWidth()- justBeforeCollideX)/gameVaus.getScaledWidth()));
            System.out.println(gameVaus.getCenterX());
            System.out.println(gameball.getCenterX());
            gameball.setVelocity(gameball.getVelocity(),
                    180*((gameVaus.getX() +gameVaus.getScaledWidth()- justBeforeCollideX)/gameVaus.getScaledWidth())) ;


        }
        gameball.setPosition((float) xLocation, (float) yLocation);
    }
}

