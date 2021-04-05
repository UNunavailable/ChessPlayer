package com.example.chessplayer;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.example.chessplayer.figures.aFigure;

public class Board {
    // Var
    public ImageView board;
    public aFigure[][] figures = new aFigure[8][8]; // массив фигур
    public Coords[][] tileCoords = new Coords[8][8]; // массив координат

    // Method
    public void init() { // Инициализация доски
        int top = board.getTop();
        int left = board.getLeft();
        for (int x=0;x<8;x++){
            for (int y=0;y<8;y++){
                tileCoords[x][y]=new Board.Coords(0, 0);
            }
        }

        Bitmap bitmap = ((BitmapDrawable)board.getDrawable()).getBitmap();

        int x_i = 0;
        boolean White = false;
        for (int x = left; x < board.getWidth(); x++) {
            if (Color.red(bitmap.getPixel(x, 10)) == 255) {
                if (!White) {
                    for (int y = 0; y < tileCoords.length; y++) {
                        tileCoords[x_i][y] = new Board.Coords(x, tileCoords[x_i][y].Y);
                    }
                    x_i++;
                    White = !White;
                }
            }
            if (Color.red(bitmap.getPixel(x, 10)) == 0) {
                if (White) {
                    for (int y = 0; y < tileCoords.length; y++) {
                        tileCoords[x_i][y] = new Board.Coords(x, tileCoords[x_i][y].Y);
                    }
                    x_i++;
                    White = !White;
                }
            }
        }

        int y_i = 0;
        White = false;
        for (int y = top; y < board.getHeight(); y++) {
            if (Color.red(bitmap.getPixel(10, y)) == 255) {
                if (!White) {
                    for (int x = 0; x < tileCoords.length; x++) {
                        tileCoords[x][y_i] = new Board.Coords(tileCoords[x][y_i].X, y);
                    }
                    y_i++;
                    White = !White;
                }
            }
            if (Color.red(bitmap.getPixel(10, y)) == 0) {
                if (White) {
                    for (int x = 0; x < tileCoords.length; x++) {
                        tileCoords[x][y_i] = new Board.Coords(tileCoords[x][y_i].X, y);
                    }
                    y_i++;
                    White = !White;
                }
            }
        }
    };

    public void lightUpTile(Tile tile) {}; // подсветка тайла
    public void delightUpTile(Tile tile) {}; // отсветка тайла
    public int canMove(Tile tile) {return 0;}; // проверка на возможность хода фигуры
    public void gameEnd() {}; // конец игры
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