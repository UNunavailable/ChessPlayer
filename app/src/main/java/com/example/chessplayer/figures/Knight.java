package com.example.chessplayer.figures;

import android.widget.ImageView;
import com.example.chessplayer.Board;
import com.example.chessplayer.Constants;
import java.util.ArrayList;

public class Knight extends aFigure {
    private boolean haveMoved = false;

    public Knight(Board boardInstance,ImageView image, int posX, int posY, boolean isWhite, boolean canMove) {
        super(boardInstance, image, posX, posY, isWhite, canMove);
    }

    protected int[][] chooseTiles() {
        ArrayList<int[]> tiles = new ArrayList<int[]>();

        int[] X = new int[]{posX+1, posX+1, posX+2, posX+2, posX-1, posX-1, posX-2, posX-2};
        int[] Y = new int[]{posY-2, posY+2, posY-1, posY+1, posY-2, posY+2, posY-1, posY+1};

        for (int i = 0; i < 8; i++) {

            if(boardInstance.checkTile(X[i], Y[i]) == Constants.EMPTY  && boardInstance.ifInBounds(X[i], Y[i]))
            {
                tiles.add(new int[]{X[i], Y[i]});
                continue;
            }
            if(boardInstance.checkTile(X[i], Y[i]) == Constants.BLACKFIGURE && isWhite && boardInstance.ifInBounds(X[i], Y[i]))
            {
                tiles.add(new int[]{X[i], Y[i]});
                continue;
            }
            if(boardInstance.checkTile(X[i], Y[i]) == Constants.WHITEFIGURE && !isWhite && boardInstance.ifInBounds(X[i], Y[i]))
            {
                tiles.add(new int[]{X[i], Y[i]});
                continue;
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


