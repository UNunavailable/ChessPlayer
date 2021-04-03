package com.example.chessplayer.figures;

import android.widget.ImageView;

import com.example.chessplayer.Board;

public class Pawn extends aFigure {
    Pawn(ImageView image, int posX, int posY) {
        figureName = Board.PAWN;
        this.image = image;
        this.posX = posX;
        this.posY = posY;
    }
}
