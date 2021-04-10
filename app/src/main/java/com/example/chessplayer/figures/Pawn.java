package com.example.chessplayer.figures;

import android.widget.ImageView;

import com.example.chessplayer.Board;
import com.example.chessplayer.Constants;

public class Pawn extends aFigure {
    private boolean haveMoved = false;

    public Pawn(Board boardInstance,ImageView image, int posX, int posY, boolean isWhite) {
        super(boardInstance, image, posX, posY, isWhite);
    }

    protected Board.Tile[] chooseTiles() {
        int length = 2;
        if(posX>0){length++;}
        if(posX<7){length++;}
        int i = 0;
        Board.Tile[] tile = new Board.Tile[length];
        tile[i] = boardInstance.tiles[posX][posY - 2*(isWhite?1:0) + 1];
        i++;
        if(posX>0){ tile[i] = boardInstance.tiles[posX - 1][posY - 2*(isWhite?1:0) + 1]; i++;}
        if(posX<7){ tile[i] = boardInstance.tiles[posX + 1][posY - 2*(isWhite?1:0) + 1]; i++;}
        if (!haveMoved) {tile[i] = boardInstance.tiles[posX + 1][posY - 3*(isWhite?1:0) + 2];}
        return tile;
    }

    protected void move() {};
}
