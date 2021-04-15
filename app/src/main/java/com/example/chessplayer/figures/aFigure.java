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
    int[][] moveTiles;

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
                        int[][] tempMoveTiles = chooseTiles();
                        moveTiles = boardInstance.checkMove(posX, posY, tempMoveTiles, isWhite);
                        if(moveTiles.length == 0) {
                            image.setX(boardInstance.tiles[posX][posY].X*boardInstance.scaleWidth);
                            image.setY(boardInstance.tiles[posX][posY].Y*boardInstance.scaleHeight);
                            return true;
                        }
                        for (int i = 0; i < moveTiles.length; i++) {
                            boardInstance.lightUpTile(moveTiles[i][0], moveTiles[i][1]);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if(moveTiles.length == 0) {
                            image.setX(boardInstance.tiles[posX][posY].X*boardInstance.scaleWidth);
                            image.setY(boardInstance.tiles[posX][posY].Y*boardInstance.scaleHeight);
                            return true;
                        }
                        for (int i = 0; i < moveTiles.length; i++) {
                            boardInstance.delightUpTile(moveTiles[i][0], moveTiles[i][1]);
                        }
                        int move[] = boardInstance.findNearestTile(
                                (int)(mousePosX),
                                (int)(mousePosY),
                                moveTiles);
                        if (move[2] < (boardInstance.tileHeight+boardInstance.tileWidth)/4) {
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

    public void move(int posX, int posY) {
        image.setX(boardInstance.tiles[posX][posY].X*boardInstance.scaleWidth);
        image.setY(boardInstance.tiles[posX][posY].Y*boardInstance.scaleHeight);
        boardInstance.tiles[posX][posY].figure = boardInstance.tiles[this.posX][this.posY].figure;
        boardInstance.tiles[this.posX][this.posY].figure = null;
        this.posX = posX;
        this.posY = posY;
    }

}
