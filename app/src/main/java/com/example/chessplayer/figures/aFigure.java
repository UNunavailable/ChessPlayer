package com.example.chessplayer.figures;

import android.view.View;
import android.widget.ImageView;

public abstract class aFigure {
    public int figureName;
    public ImageView image;
    abstract View.OnTouchListener onTouchListener();
}
