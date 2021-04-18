package com.example.chessplayer.figures;

import android.widget.ImageView;

import androidx.fragment.app.FragmentManager;

import com.example.chessplayer.Board;
import com.example.chessplayer.BoardFragment;
import com.example.chessplayer.Constants;
import com.example.chessplayer.Dialog;
import com.example.chessplayer.MainActivity;

import java.util.ArrayList;
import java.util.zip.CheckedOutputStream;

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
                if(boardInstance.checkTile(X[i], Y[i]) == Constants.EMPTY)
                {
                    tiles.add(new int[]{X[i], Y[i]});
                    continue;
                }
            }
            if(i > 0 && i < 3) {
                if(boardInstance.checkTile(X[i], Y[i]) == Constants.BLACKFIGURE && isWhite)
                {
                    if(boardInstance.checkTile(X[i], Y[i]) == Constants.BLACKFIGURE && isWhite)
                    {
                        tiles.add(new int[]{X[i], Y[i]});
                        continue;
                    }

                    tiles.add(new int[]{X[i], Y[i]});
                    continue;
                }
                else if(boardInstance.checkTile(X[i], Y[i]) == Constants.WHITEFIGURE && !isWhite)
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
        haveMoved = true;

        for (int z = 0; z < 7; z++) {
            if(posX==z && posY == 0) {
                boardInstance.deleteFigure(posX,posY);
                boardInstance.addFigure(posX,posY,Constants.QUEEN, true, true);
            }
            if(posX==z && posY == 7) {
                boardInstance.deleteFigure(posX,posY);
                boardInstance.addFigure(posX,posY,Constants.QUEEN, false, false);
            }
        }


        for (int z = 0; z < 7; z++) {
            if(posX==z && posY == 0) {
                boardInstance.deleteFigure(posX,posY);
                boardInstance.addFigure(posX,posY,Constants.QUEEN, true, true);
            }
            if(posX==z && posY == 7) {
                boardInstance.deleteFigure(posX,posY);
                boardInstance.addFigure(posX,posY,Constants.QUEEN, false, false);
            }
        }

        if((posY == 0 && isWhite) || (posY == 7 && !isWhite)) {
            boardInstance.fragInstance.startDialog();
        }
    }
}


