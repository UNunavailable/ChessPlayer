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
    public Board boardInstance;

    protected abstract Board.Tile[] chooseTiles();
    protected abstract void move();

    aFigure(Board boardInstance, ImageView image, int posX, int posY, boolean isWhite) {
        this.boardInstance = boardInstance;
        this.image = image;
        this.posX = posX;
        this.posY = posY;
        this.image.setOnTouchListener(onTouchListener());
        this.isWhite = isWhite;
    }

    public View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
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
                        Board.Tile[] moveTiles = chooseTiles();
                        delightUp(moveTiles);
                        int move[] = boardInstance.findNearestTile(
                                (int)(mousePosX),
                                (int)(mousePosY),
                                moveTiles);
                        if (move[2] < 200) {
                            image.setX(move[0]*boardInstance.scaleWidth);
                            image.setY(move[1]*boardInstance.scaleHeight);
                        } else {
                            image.setX(boardInstance.tiles[posX][posY].X*boardInstance.scaleWidth);
                            image.setY(boardInstance.tiles[posX][posY].Y*boardInstance.scaleHeight);
                        }
                        move();
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

    protected void lightUp(Board.Tile[] tile) {
        for (int i = 0; i<tile.length; i++) {
            if (boardInstance.canMove(tile[i]) == Constants.EMPTY
                    || boardInstance.canMove(tile[i]) == Constants.FIGURE)
            {
                boardInstance.lightUpTile(tile[i]);
            }
        }
    };

    protected void delightUp(Board.Tile[] tile) {
        for (int i = 0; i<tile.length; i++) {
            if (boardInstance.canMove(tile[i]) == Constants.EMPTY
                    || boardInstance.canMove(tile[i]) == Constants.FIGURE)
            {
                boardInstance.delightUpTile(tile[i]);
            }
        }
    };

}
