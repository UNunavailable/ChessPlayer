package com.example.chessplayer.figures;

import android.widget.ImageView;

import com.example.chessplayer.Board;
import com.example.chessplayer.Constants;

import java.util.ArrayList;

public class Pawn extends aFigure {
    private boolean haveMoved = false;

    public Pawn(Board boardInstance,ImageView image, int posX, int posY, boolean isWhite) {
        super(boardInstance, image, posX, posY, isWhite);
    }

    protected Board.Tile[] chooseTiles() {
        ArrayList<Board.Tile> tiles = new ArrayList<Board.Tile>();
        int[] X = new int[]{posX, posX - 1, posX + 1, posX + 1};
        int[] Y = new int[]{
                posY - 2*(isWhite?1:0) + 1,
                posY - 2*(isWhite?1:0) + 1,
                posY - 2*(isWhite?1:0) + 1,
                posY - 3*(isWhite?1:0) + 2};
        for (int i = 0; i < X.length; i++) {
            if(i<X.length-1) {
                if(boardInstance.checkOutOfBounds(X[i], Y[i])) {
                    tiles.add(boardInstance.tiles[X[i]][Y[i]]);
                }
            } else {
                if(boardInstance.checkOutOfBounds(X[i], Y[i]) && !haveMoved) {
                    tiles.add(boardInstance.tiles[X[i]][Y[i]]);
                }
            }
        }
        Board.Tile[] result = new Board.Tile[tiles.size()];
        result = tiles.toArray(result);
        return result;
    }

    protected void move() {};
}
