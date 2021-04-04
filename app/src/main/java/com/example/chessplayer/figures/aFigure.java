package com.example.chessplayer.figures;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public abstract class aFigure {
    public int posX;
    public int posY;
    public int figureID;
    public ImageView image;
    public boolean ifWhite;

    public abstract void lightUp();
    public abstract void move();

    aFigure(ImageView image, int posX, int posY) {
        this.image = image;
        this.posX = posX;
        this.posY = posY;
        this.image.setOnTouchListener(onTouchListener());
    }

    public View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        lightUp();
                        break;
                    case MotionEvent.ACTION_UP:
                        move();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        image.setX(event.getRawX());
                        image.setY(event.getRawY());
                        break;
                }
                return true;
            }
        };
    }

}
