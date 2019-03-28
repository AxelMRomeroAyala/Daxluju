package com.yacarex.daxluju.models.axel;

public class StrokeModel {

    private boolean[][] matrix;
    private int color;

    public StrokeModel(boolean[][] matrix, int color){
        this.matrix= matrix;
        this.color= color;
    }

    public boolean[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(boolean[][] matrix) {
        this.matrix = matrix;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
