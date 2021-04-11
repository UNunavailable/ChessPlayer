package com.example.chessplayer.figures;

import android.widget.ImageView;

import com.example.chessplayer.Board;
import com.example.chessplayer.Constants;

import java.util.ArrayList;

public class Pawn extends aFigure {
    private boolean haveMoved = false;

    public Pawn(Board boardInstance,ImageView image, int posX, int posY, boolean isWhite, boolean canMove) {
        super(boardInstance, image, posX, posY, isWhite, canMove);
    }

    protected int[][] chooseTiles() {
        ArrayList<int[]> tiles = new ArrayList<int[]>();
        int[] X = new int[]{posX, posX - 1, posX + 1, posX};
        int[] Y = new int[]{
                posY - 2*(isWhite?1:0) + 1,
                posY - 2*(isWhite?1:0) + 1,
                posY - 2*(isWhite?1:0) + 1,
                posY - 4*(isWhite?1:0) + 2};
        for (int i = 0; i < X.length; i++) {
            if(i<X.length-1) {
                if(boardInstance.checkOutOfBounds(X[i], Y[i])) {
                    tiles.add(new int[]{X[i], Y[i]});
                }
            } else {
                if(boardInstance.checkOutOfBounds(X[i], Y[i]) && !haveMoved) {
                    tiles.add(new int[]{X[i], Y[i]});
                }
            }
        }
        int[][] result = new int[tiles.size()][2];
        result = tiles.toArray(result);
        return result;
    }

    protected void move() {};
}
