package com.example.chessplayer.figures;

import android.widget.ImageView;

import com.example.chessplayer.Board;
import com.example.chessplayer.Constants;

public class Pawn extends aFigure {
    private boolean haveMoved = false;

    public Pawn(Board boardInstance,ImageView image, int posX, int posY) {
        super(boardInstance, image, posX, posY);
        figureID = Constants.PAWN;
    }

    protected Board.Tile[] chooseTiles() {
        Board.Tile[] tile = new Board.Tile[3+(haveMoved?1:0)];
        tile[0] = new Board.Tile(posX - 2*(isWhite?1:0) + 1, posY);
        tile[1] = new Board.Tile(posX - 2*(isWhite?1:0) + 1, posY - 1);
        tile[2] = new Board.Tile(posX - 2*(isWhite?1:0) + 1, posY + 1);
        if (haveMoved) {tile[3] = new Board.Tile(posX - 3*(isWhite?1:0) + 2, posY + 1);}
        return tile;
    }

    protected void move() {};
}
