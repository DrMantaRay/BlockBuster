package com.drmantaray.blockbuster;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by pchen on 3/7/2017.
 */
public class GameWall extends GameObject {
    // intialize GameWall, a fixed wall that all objects can collide with (color = 0)
    public GameWall(Texture texture, int height, int width) {
        super(texture, height, width);
    }
    public GameWall(Texture texture) {
        super(texture);
        color = 0;
    }
}
