package com.example.chessplayer;

import android.widget.ImageView;

import com.example.chessplayer.figures.aFigure;

public class Board {
    public static final int PAWN = 1;
    public static final int BISHOP = 2;
    public static final int KNIGHT = 3;
    public static final int ROOK = 4;
    public static final int QUEEN = 5;
    public static final int KING = 6;

    public ImageView board;
    public aFigure[][] figures = new aFigure[8][8]; // массив фигур
    public Coords[][] tileCoords = new Coords[8][8]; // массив координат

    public void init() {}; // Инициализация доски
    public void lightUp() {}; // подсветка тайла
    public boolean figureWalk() {return true;}; // проверка на возможность хода фигуры
    public void gameEnd() {}; //конец игры
    public Coords findNearestTile(Coords coords) {return tileCoords[0][0];}; // находит ближайшие координаты тайла
}

class Coords {
    public int X;
    public int Y;
    Coords(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }
}