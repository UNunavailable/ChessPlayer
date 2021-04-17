package com.example.chessplayer.figures;

import android.widget.ImageView;
import com.example.chessplayer.Board;
import com.example.chessplayer.Constants;
import java.util.ArrayList;

public class King extends aFigure {
    private boolean haveMoved = false;

    public King(Board boardInstance,ImageView image, int posX, int posY, boolean isWhite, boolean canMove) {
        super(boardInstance, image, posX, posY, isWhite, canMove);
    }

    protected int[][] chooseTiles() {
        ArrayList<int[]> tiles = new ArrayList<int[]>();

        int[] X = new int[]{posX+1, posX+1, posX+1, posX, posX, posX-1, posX-1, posX-1};
        int[] Y = new int[]{posY-1, posY, posY+1, posY+1, posY-1, posY-1, posY, posY+1};

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

        if(!haveMoved && !boardInstance.checkFinder(isWhite)) {
            for (int i = 1; i < 5; i++) {
                if(boardInstance.checkTile(posX + i, posY) != Constants.EMPTY) break;
                if(boardInstance.checkMove(posX, posY, new int[][]{new int[]{posX + i, posY}}, isWhite).length == 0) break;
                if(boardInstance.checkTile(posX + 1 + i, posY) == Constants.OUTOFBOARD) break;
                if(boardInstance.tiles[posX + 1 + i][posY].figure instanceof Rook
                    && boardInstance.tiles[posX + 1 + i][posY].figure.isWhite == isWhite
                    && !((Rook)boardInstance.tiles[posX + 1 + i][posY].figure).haveMoved
                    && i>1) {
                    tiles.add(new int[]{posX + 2, posY});
                }
            }
            for (int i = 1; i < 5; i++) {
                if(boardInstance.checkTile(posX - i, posY) != Constants.EMPTY) break;
                if(boardInstance.checkMove(posX, posY, new int[][]{new int[]{posX - i, posY}}, isWhite).length == 0) break;
                if(boardInstance.checkTile(posX - 1 - i, posY) == Constants.OUTOFBOARD) break;
                if(boardInstance.tiles[posX - 1 - i][posY].figure instanceof Rook
                        && boardInstance.tiles[posX - 1 - i][posY].figure.isWhite == isWhite
                        && !((Rook)boardInstance.tiles[posX - 1 - i][posY].figure).haveMoved) {
                    tiles.add(new int[]{posX - 2, posY});
                }
            }
        }

        int[][] result = new int[tiles.size()][2];
        result = tiles.toArray(result);
        return result;
    }

    public aFigure changePosition(int posX, int posY) {
        if(isWhite) boardInstance.whiteKingPos = new int[]{posX, posY};
        else boardInstance.blackKingPos = new int[]{posX, posY};
        return super.changePosition(posX, posY);
    }

    public void move(int posX, int posY) {
        if(Math.abs(this.posX - posX) == 2) {
            if(this.posX - posX > 0) {
                boardInstance.tiles[findRook(true)][posY].figure.move(posX+1, posY);
            } else {
                boardInstance.tiles[findRook(false)][posY].figure.move(posX-1, posY);
            }
        }
        super.move(posX, posY);
        haveMoved = true;
    }

    private int findRook(boolean isLeft) {
        if(isLeft) {
            for (int i = 0; i < 5; i++) {
                if(boardInstance.tiles[posX - i][posY].figure instanceof Rook) return posX - i;
            }
        }
        if(!isLeft) {
            for (int i = 0; i < 5; i++) {
                if(boardInstance.tiles[posX + i][posY].figure instanceof Rook) return posX + i;
            }
        }
        return -1;
    }
}


