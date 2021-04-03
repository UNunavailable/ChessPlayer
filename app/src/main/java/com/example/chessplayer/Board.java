package com.example.chessplayer;

import com.example.chessplayer.figures.aFigure;

public class Board {
    public static final int PAWN = 1;
    public static final int BISHOP = 2;
    public static final int KNIGHT = 3;
    public static final int ROOK = 4;
    public static final int QUEEN = 5;
    public static final int KING = 6;

    public aFigure[][] figures = new aFigure[8][8]; // массив фигур
    public void init() {}; // Инициализация доски
    public void lightUp() {}; // подсветка тайла
    public boolean figureWalk() {return true;}; // проверка на возможность хода фигуры
    public void gameEnd() {}; //конец игры
}