package com.example.chessplayer.figures;

import android.view.MotionEvent;
import android.view.View;
import com.example.chessplayer.Board;

public class Pawn extends aFigure {
    Pawn() {
        figureName = Board.PAWN;
    }
    public View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                return true;
            }
        };
    }
}
