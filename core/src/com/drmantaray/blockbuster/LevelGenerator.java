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

//read csv and initializes game objects
public class LevelGenerator {
    private int size;
    private List<String[]> inFile;
    public ArrayList<GameObject> gameObjectArrayList;
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
        BasicBlock tempBlock;
        for (String[] stringlists : inFile.subList(1, inFile.size())) {
            if (stringlists[0].equals("GameWall")) {
                tempWall = new GameWall(assetManager.get(stringlists[4], com.badlogic.gdx.graphics.Texture.class));
                tempWall.rotate((float) Integer.valueOf(stringlists[3]));
                tempWall.setPosition((float) Integer.valueOf(stringlists[1]), (float) Integer.valueOf(stringlists[2]));
                gameObjectArrayList.add(tempWall);
                size++;
            }
            else if (stringlists[0].equals("BasicBlock")) {
                tempBlock = new BasicBlock(assetManager.get(stringlists[4], com.badlogic.gdx.graphics.Texture.class));
                tempBlock.rotate((float) Integer.valueOf(stringlists[3]));
                tempBlock.setPosition((float) Integer.valueOf(stringlists[1]), (float) Integer.valueOf(stringlists[2]));
                gameObjectArrayList.add(tempBlock);
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