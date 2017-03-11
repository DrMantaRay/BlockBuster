package com.drmantaray.blockbuster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.opencsv.CSVReader;
import com.sun.prism.Texture;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pchen on 3/7/2017.
 */
public class LevelGenerator {
    private List<String[]> inFile;
    private ArrayList<GameObject> gameObjectArrayList;
    public LevelGenerator() {
        gameObjectArrayList = new ArrayList<GameObject>();
    }
    public void inputCSV(String pathName) {
        try {
            CSVReader reader = new CSVReader(new FileReader(Gdx.files.internal(pathName).file()));
            try {
                inFile = reader.readAll();
            }catch(IOException e) {
                e.printStackTrace();
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public void generateGameObjects(AssetManager assetManager) {
        for (String[] stringlists : inFile.subList(1, inFile.size())) {
            if (stringlists[1] == "GameWall") {
                gameObjectArrayList.add(new GameWall(assetManager.get(stringlists[4], com.badlogic.gdx.graphics.Texture.class)));
            }
        }
    }
    public ArrayList<GameObject> returnGameObjects(){
        return gameObjectArrayList;
    }
    public void drawGameObjects(Batch batch) {
        for (GameObject gameObject : gameObjectArrayList) {
            gameObject.draw(batch);
        }
    }

}