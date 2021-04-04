package com.example.chessplayer.figures;

import android.widget.ImageView;

import com.example.chessplayer.Board;

public class Pawn extends aFigure {
    Pawn(ImageView image, int posX, int posY) {
        super(image, posX, posY);
        figureID = Board.PAWN;
    }

    public void lightUp() {};
    public void move() {};
}
