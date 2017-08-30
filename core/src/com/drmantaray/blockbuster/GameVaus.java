package com.drmantaray.blockbuster;

import com.badlogic.gdx.graphics.Texture;

//The player controlled paddle class
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
}
