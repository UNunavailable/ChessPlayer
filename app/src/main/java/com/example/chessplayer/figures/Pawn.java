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
        for (int i = 0; i < 4; i++) {
            if(i == 0) {
                if(boardInstance.checkTile(X[i], Y[i]) == Constants.EMPTY) {
                    tiles.add(new int[]{X[i], Y[i]});
                    continue;
                }
            }
            if(i > 0 && i < 3) {
                if(boardInstance.checkTile(X[i], Y[i]) == Constants.BLACKFIGURE && isWhite) {
                    tiles.add(new int[]{X[i], Y[i]});
                    continue;
                } else if(boardInstance.checkTile(X[i], Y[i]) == Constants.WHITEFIGURE && !isWhite) {
                    tiles.add(new int[]{X[i], Y[i]});
                    continue;
                }
            } else {
                if(boardInstance.ifInBounds(X[i], Y[i]) && !haveMoved) {
                    tiles.add(new int[]{X[i], Y[i]});
                }
            }
        }
        int[][] result = new int[tiles.size()][2];
        result = tiles.toArray(result);
        return result;
    }

    public void move(int posX, int posY) {
        super.move(posX, posY);
        haveMoved = true;
    }
}


