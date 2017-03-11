package com.drmantaray.blockbuster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.opencsv.CSVReader;

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
    private int size;
    private List<String[]> inFile;
    private ArrayList<GameObject> gameObjectArrayList;
    public LevelGenerator() {
        gameObjectArrayList = new ArrayList<GameObject>();
        size = 0;
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
        GameWall tempWall;
        for (String[] stringlists : inFile.subList(1, inFile.size())) {
            if (stringlists[0].equals("GameWall")) {
                tempWall = new GameWall(assetManager.get(stringlists[4], com.badlogic.gdx.graphics.Texture.class));
                tempWall.setPosition((float) Integer.valueOf(stringlists[1]), (float) Integer.valueOf(stringlists[2]));
                tempWall.rotate((float) Integer.valueOf(stringlists[3]));
                gameObjectArrayList.add(tempWall);
                size++;
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
    public int size() {
        return size;
    }

}