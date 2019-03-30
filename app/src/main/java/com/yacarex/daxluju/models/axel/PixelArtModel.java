package com.yacarex.daxluju.models.axel;

import java.util.ArrayList;
import java.util.Date;

public class PixelArtModel {

    private String name;
    private ArrayList<StrokeModel> strokes;

    public PixelArtModel(){
        name= "PAM"+String.valueOf(new Date().getTime());
        strokes= new ArrayList<>();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<StrokeModel> getStrokes() {
        return strokes;
    }

    public void setStrokes(ArrayList<StrokeModel> strokes) {
        this.strokes = strokes;
    }

    public void addStroke(StrokeModel strokeModel){
        strokes.add(strokeModel);
    }

    public void removeLastStroke(){
        strokes.remove(strokes.size() - 1);
    }

    public int getStrokeSize(){
        return strokes.size();
    }
}
