package com.drmantaray.blockbuster;

import com.badlogic.gdx.graphics.Texture;

//fixed wall class
public class GameWall extends GameBlock {
    // intialize GameWall, a fixed wall that all objects can collide with (color = 0)
    public GameWall(Texture texture, int height, int width) {
        super(texture, height, width);
    }
    public GameWall(Texture texture) {
        super(texture);
    }
}
