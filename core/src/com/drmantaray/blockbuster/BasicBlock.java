package com.drmantaray.blockbuster;

import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;

//Basic destructible block
public class BasicBlock extends GameBlock {
    public BasicBlock (Texture texture) {
        super(texture);
        score = 1;
    }
    // when the block dies, update the score
    public void dies(ArrayList<GameObject> arrayList
    ) {
        //System.out.println(arrayList.contains(this));
        arrayList.remove(this);
        GameScreen.score += this.score;
        //System.out.println(arrayList.contains(this));
    }
}
