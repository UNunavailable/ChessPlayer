package com.example.chessplayer.figures;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import com.example.chessplayer.Board;
import com.example.chessplayer.BoardFragment;
import com.example.chessplayer.Constants;

import org.xml.sax.Parser;

import java.util.ArrayList;
import java.util.Date;

public class Pawn extends aFigure {
    private boolean haveMoved = false;
    public boolean enPassantLeft = false;
    public boolean enPassantRight = false;

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
                if(boardInstance.checkTile(X[i], Y[i]) == Constants.EMPTY)
                {
                    tiles.add(new int[]{X[i], Y[i]});
                    continue;
                }
            }

            if(i > 0 && i < 3) {
                if((boardInstance.checkTile(X[i], Y[i]) == Constants.BLACKFIGURE && isWhite) || enPassantLeft)
                {
                    tiles.add(new int[]{X[i], Y[i]});
                    continue;
                }
                else if((boardInstance.checkTile(X[i], Y[i]) == Constants.WHITEFIGURE && !isWhite) || enPassantRight)
                {
                    tiles.add(new int[]{X[i], Y[i]});
                    continue;
                }
            }

            else {
                if(boardInstance.ifInBounds(X[i], Y[i])
                        && boardInstance.checkTile(X[i], Y[i]) == Constants.EMPTY
                        && boardInstance.checkTile(X[i], Y[0]) == Constants.EMPTY
                        && !haveMoved)
                {
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
        if(enPassantRight || enPassantLeft) {
            boardInstance.deleteFigure(posX, posY + (isWhite?2:0) - 1);
        }
        if(boardInstance.checkTile(posX-1, posY) == Constants.WHITEFIGURE
                || boardInstance.checkTile(posX-1, posY) == Constants.BLACKFIGURE) {
            if(boardInstance.tiles[posX-1][posY].figure.isWhite != isWhite
                    && boardInstance.tiles[posX-1][posY].figure instanceof Pawn) {
                ((Pawn)boardInstance.tiles[posX-1][posY].figure).enPassantRight = true;
            }
        }
        if(boardInstance.checkTile(posX+1, posY) == Constants.WHITEFIGURE
                || boardInstance.checkTile(posX+1, posY) == Constants.BLACKFIGURE) {
            if(boardInstance.tiles[posX+1][posY].figure.isWhite != isWhite
                    && boardInstance.tiles[posX+1][posY].figure instanceof Pawn) {
                ((Pawn)boardInstance.tiles[posX+1][posY].figure).enPassantRight = true;
            }
        }
        haveMoved = true;
        enPassantLeft = false;
        enPassantRight = false;

        if((posY == 0 && isWhite) || (posY == 7 && !isWhite)) {
            boardInstance.fragInstance.startDialog(posX, posY, isWhite, canMove);
        }
    }
}


