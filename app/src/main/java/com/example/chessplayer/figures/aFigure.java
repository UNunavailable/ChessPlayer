package com.example.chessplayer.figures;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.chessplayer.Board;
import com.example.chessplayer.Constants;

public abstract class aFigure {
    public int posX;
    public int posY;
    public ImageView image;
    public boolean isWhite;
    public boolean canMove;
    public Board boardInstance;

    protected abstract int[][] chooseTiles();

    aFigure(Board boardInstance, ImageView image, int posX, int posY, boolean isWhite, boolean canMove) {
        this.boardInstance = boardInstance;
        this.image = image;
        this.posX = posX;
        this.posY = posY;
        this.image.setOnTouchListener(onTouchListener());
        this.isWhite = isWhite;
        this.canMove = canMove;
    }

    public View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(!canMove){return true;}
                int[] location = new int[2];
                boardInstance.board.getLocationOnScreen(location);
                float mousePosX = event.getRawX()-location[0]-image.getMeasuredWidth()/2;
                float mousePosY = event.getRawY()-location[1]-image.getMeasuredHeight()/2;
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        image.setX(mousePosX);
                        image.setY(mousePosY);
                        lightUp(chooseTiles());
                        break;
                    case MotionEvent.ACTION_UP:
                        int[][] moveTiles = chooseTiles();
                        delightUp(moveTiles);
                        int move[] = boardInstance.findNearestTile(
                                (int)(mousePosX),
                                (int)(mousePosY),
                                moveTiles);
                        if (move[2] < (boardInstance.tileHeight+boardInstance.tileWidth)/2) {
                            boardInstance.makeTurn(posX, posY, move[0], move[1]);
                        } else {
                            image.setX(boardInstance.tiles[posX][posY].X*boardInstance.scaleWidth);
                            image.setY(boardInstance.tiles[posX][posY].Y*boardInstance.scaleHeight);
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        image.setX(mousePosX);
                        image.setY(mousePosY);
                        break;
                }
                return true;
            }
        };
    }

    protected void lightUp(int[][] temp) {
    };

    protected void delightUp(int[][] temp) {
    };

}
