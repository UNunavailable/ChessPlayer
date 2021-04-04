package com.example.chessplayer.figures;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.chessplayer.Board;
import com.example.chessplayer.Constants;

public abstract class aFigure {
    public int posX;
    public int posY;
    public int figureID;
    public ImageView image;
    public boolean isWhite;
    public Board boardInstance;

    protected abstract Board.Tile[] chooseTiles();
    protected abstract void move();

    aFigure(Board boardInstance, ImageView image, int posX, int posY) {
        this.boardInstance = boardInstance;
        this.image = image;
        this.posX = posX;
        this.posY = posY;
        this.image.setOnTouchListener(onTouchListener());
    }

    public View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        lightUp();
                        break;
                    case MotionEvent.ACTION_UP:
                        move();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        image.setX(event.getRawX());
                        image.setY(event.getRawY());
                        break;
                }
                return true;
            }
        };
    }

    protected void lightUp() {
        Board.Tile[] tile = chooseTiles();
        for (int i = 0; i<tile.length; i++) {
            if (boardInstance.canMove(tile[i]) == Constants.EMPTY
                    || boardInstance.canMove(tile[i]) == Constants.FIGURE)
            {
                boardInstance.lightUpTile(tile[i]);
            }
        }
    };

}
