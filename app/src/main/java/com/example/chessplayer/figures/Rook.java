package com.example.chessplayer.figures;

import android.widget.ImageView;
import com.example.chessplayer.Board;
import com.example.chessplayer.Constants;
import java.util.ArrayList;

public class Rook extends aFigure {
    private boolean haveMoved = false;

    public Rook(Board boardInstance,ImageView image, int posX, int posY, boolean isWhite, boolean canMove) {
        super(boardInstance, image, posX, posY, isWhite, canMove);
    }

    protected int[][] chooseTiles() {
        ArrayList<int[]> tiles = new ArrayList<int[]>();

        if(isWhite){
            for(int i=1;i<8;i++){
                if (boardInstance.checkTile(posX, posY - i) != Constants.OUTOFBOARD) {
                    if(boardInstance.checkTile(posX, posY - i) != Constants.EMPTY){
                        if(boardInstance.checkTile(posX, posY - i) == Constants.BLACKFIGURE){
                            tiles.add(new int[]{posX, posY - i});
                            break;
                        }
                        break;
                    }
                    tiles.add(new int[]{posX, posY - i});
                }
            }
            for(int i=1;i<8;i++) {
                if (boardInstance.checkTile(posX, posY + i) != Constants.OUTOFBOARD) {
                    if (boardInstance.checkTile(posX, posY + i) != Constants.EMPTY) {
                        if (boardInstance.checkTile(posX, posY + i) == Constants.BLACKFIGURE) {
                            tiles.add(new int[]{posX, posY + i});
                            break;
                        }
                        break;
                    }
                    tiles.add(new int[]{posX, posY + i});
                }
            }
            for(int i=1;i<8;i++) {
                if (boardInstance.checkTile(posX - i, posY) != Constants.OUTOFBOARD) {
                    if (boardInstance.checkTile(posX - i, posY) != Constants.EMPTY) {
                        if (boardInstance.checkTile(posX - i, posY) == Constants.BLACKFIGURE) {
                            tiles.add(new int[]{posX - i, posY});
                            break;
                        }
                        break;
                    }
                    tiles.add(new int[]{posX - i, posY});
                }
            }
            for(int i=1;i<8;i++) {
                if (boardInstance.checkTile(posX + i, posY) != Constants.OUTOFBOARD) {
                    if (boardInstance.checkTile(posX + i, posY) != Constants.EMPTY) {
                        if (boardInstance.checkTile(posX + i, posY) == Constants.BLACKFIGURE) {
                            tiles.add(new int[]{posX + i, posY});
                            break;
                        }
                        break;
                    }
                    tiles.add(new int[]{posX + i, posY});
                }
            }
        }
        else{
            for(int i=1;i<8;i++){
                if (boardInstance.checkTile(posX, posY - i) != Constants.OUTOFBOARD) {
                    if(boardInstance.checkTile(posX, posY - i) != Constants.EMPTY){
                        if(boardInstance.checkTile(posX, posY - i) == Constants.WHITEFIGURE){
                            tiles.add(new int[]{posX, posY - i});
                            break;
                        }
                        break;
                    }
                    tiles.add(new int[]{posX, posY - i});
                }
            }
            for(int i=1;i<8;i++) {
                if (boardInstance.checkTile(posX, posY + i) != Constants.OUTOFBOARD) {
                    if (boardInstance.checkTile(posX, posY + i) != Constants.EMPTY) {
                        if (boardInstance.checkTile(posX, posY + i) == Constants.WHITEFIGURE) {
                            tiles.add(new int[]{posX, posY + i});
                            break;
                        }
                        break;
                    }
                    tiles.add(new int[]{posX, posY + i});
                }
            }
            for(int i=1;i<8;i++) {
                if (boardInstance.checkTile(posX - i, posY) != Constants.OUTOFBOARD) {
                    if (boardInstance.checkTile(posX - i, posY) != Constants.EMPTY) {
                        if (boardInstance.checkTile(posX - i, posY) == Constants.WHITEFIGURE) {
                            tiles.add(new int[]{posX - i, posY});
                            break;
                        }
                        break;
                    }
                    tiles.add(new int[]{posX - i, posY});
                }
            }
            for(int i=1;i<8;i++) {
                if (boardInstance.checkTile(posX + i, posY) != Constants.OUTOFBOARD) {
                    if (boardInstance.checkTile(posX + i, posY) != Constants.EMPTY) {
                        if (boardInstance.checkTile(posX + i, posY) == Constants.WHITEFIGURE) {
                            tiles.add(new int[]{posX + i, posY});
                            break;
                        }
                        break;
                    }
                    tiles.add(new int[]{posX + i, posY});
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


