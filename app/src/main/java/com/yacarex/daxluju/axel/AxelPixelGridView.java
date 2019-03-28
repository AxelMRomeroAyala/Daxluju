package com.yacarex.daxluju.axel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.yacarex.daxluju.models.axel.StrokeModel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class AxelPixelGridView extends View{

    private int matrixIndex = 0;
    private int numColumns, numRows;
    private int cellWidth, cellHeight;
    private Paint blackPaint = new Paint();
    private Paint currentPaint = new Paint();
    private int currentColor = Color.BLACK;
    private boolean[][] currentCellMatrix;
    private ArrayList<StrokeModel> strokes = new ArrayList<>();
    private boolean showGrid = true;

    public AxelPixelGridView(Context context) {
        this(context, null);
    }

    public AxelPixelGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        currentPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public void updateMatrixes(boolean currentCellMatrix[][]) {
        int newColor = currentColor;
        strokes.add(new StrokeModel(currentCellMatrix, newColor));
        this.currentCellMatrix = new boolean[numColumns][numRows];
        matrixIndex++;
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
        calculateDimensions();
    }

    public int getNumColumns() {
        return numColumns;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
        calculateDimensions();
    }

    public int getNumRows() {
        return numRows;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calculateDimensions();
    }

    private void calculateDimensions() {
        if (numColumns < 1 || numRows < 1) {
            return;
        }

        cellWidth = getWidth() / numColumns;
        cellHeight = getHeight() / numRows;

        currentCellMatrix = new boolean[numColumns][numRows];

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        if (numColumns == 0 || numRows == 0) {
            return;
        }


        computePixels(canvas);

        //paint the current matrix representation
        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                if (currentCellMatrix[i][j]) {
                    canvas.drawRect(i * cellWidth, j * cellHeight,
                            (i + 1) * cellWidth, (j + 1) * cellHeight,
                            currentPaint);
                }
            }
        }

        paintGrid(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int column = (int) (event.getX() / cellWidth);
                int row = (int) (event.getY() / cellHeight);

                try {
                    currentCellMatrix[column][row] = true;
                } catch (ArrayIndexOutOfBoundsException aioobe) {
                    aioobe.printStackTrace();
                }

                break;
            case MotionEvent.ACTION_UP:
                updateMatrixes(currentCellMatrix);
                break;
        }

        invalidate();

        return true;
    }

    public void undo() {
        if (matrixIndex > 0) {
            strokes.remove(strokes.size() - 1);
            matrixIndex--;
            invalidate();
        }
    }

    private void paintGrid(Canvas canvas) {

        if (showGrid) {
            for (int i = 1; i < numColumns; i++) {
                canvas.drawLine(i * cellWidth, 0, i * cellWidth, getHeight(), blackPaint);
            }

            for (int i = 1; i < numRows; i++) {
                canvas.drawLine(0, i * cellHeight, getWidth(), i * cellHeight, blackPaint);
            }
        }
    }

    private void computePixels(Canvas canvas) {
        if (strokes != null && !strokes.isEmpty()) {//if the matrix array is not null or empty
            for (int i = 0; i < numColumns; i++) {//for each X value Axis in pixel representation
                for (int j = 0; j < numRows; j++) {//for each Y value Axis in pixel representation
                    for (int h = 0; h < strokes.size(); h++) {//for each matrix in the array
                        if (strokes.get(h) != null) {//if it is not null
                            if (strokes.get(h).getMatrix()[i][j]) {//get it and paint the corresponding quadrant

                                Paint gridPaint = new Paint();
                                gridPaint.setColor(strokes.get(h).getColor());
                                canvas.drawRect(i * cellWidth, j * cellHeight,
                                        (i + 1) * cellWidth, (j + 1) * cellHeight,
                                        gridPaint);
                                Log.d("Paint", "color in position " + String.valueOf(h) + " was " + String.valueOf(strokes.get(h).getColor()));
                            }
                        }
                    }
                }
            }
        }
    }

    public void toggleGrid() {
        showGrid = !showGrid;
        invalidate();
    }

    public void setPaint(int color) {

        currentColor = color;
        Paint paint = new Paint();
        paint.setColor(color);
        currentPaint = paint;
    }

    public Bitmap createBitmap() {
        Bitmap b = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        layout(0, 0, getWidth(), getHeight());
//Get the view’s background
        Drawable bgDrawable = getBackground();
        if (bgDrawable != null)
//has background drawable, then draw it on the canvas
            bgDrawable.draw(c);
        else
//does not have background drawable, then draw white background on the canvas
            c.drawColor(Color.WHITE);
        draw(c);
        return b;
    }

}


