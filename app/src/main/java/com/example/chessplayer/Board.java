package com.example.chessplayer;

import android.widget.ImageView;

import com.example.chessplayer.figures.aFigure;

public class Board {
    // Var
    public ImageView board;
    public aFigure[][] figures = new aFigure[8][8]; // массив фигур
    public Coords[][] tileCoords = new Coords[8][8]; // массив координат


    // Method
    public void init() {}; // Инициализация доски
    public void lightUpTile(Tile tiles) {}; // подсветка тайла
    public int canMove(Tile tile) {return 0;}; // проверка на возможность хода фигуры
    public void gameEnd() {}; //конец игры
    public Coords findNearestTile(Coords coords) {return tileCoords[0][0];}; // находит ближайшие координаты тайла


    // Struct
    public static class Coords {
        public int X;
        public int Y;
        public Coords(int X, int Y) {
            this.X = X;
            this.Y = Y;
        }
    }

    public static class Tile {
        public int X;
        public int Y;
        public Tile(int X, int Y) {
            this.X = X;
            this.Y = Y;
        }
    }
}