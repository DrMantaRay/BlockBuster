package com.drmantaray.blockbuster;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

//GameBlock class for all block shaped objects
public class GameBlock extends GameObject {
    int score;
    public GameBlock(Texture texture, int height, int width) {
        super(texture, height, width);
        score = 0;
    }
    public GameBlock(Texture texture) {
        super(texture);
        score = 0;
    }
}
