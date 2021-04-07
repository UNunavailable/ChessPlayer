package com.example.chessplayer;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.example.chessplayer.figures.aFigure;

public class Board {
    // Var
    public ImageView board;
    public Tile[][] tiles = new Tile[8][8]; // массив фигур
    public int tileSize;

    // Method
    public void init() { // Инициализация доски
        int top = board.getTop();
        int left = board.getLeft();
        for (int x=0;x<8;x++){
            for (int y=0;y<8;y++){
                tiles[x][y]=new Board.Tile(0, 0);
            }
        }

        Bitmap bitmap = ((BitmapDrawable)board.getDrawable()).getBitmap();

        int x_i = 0;
        boolean White = false;
        for (int x = left; x < board.getWidth(); x++) {
            if (Color.red(bitmap.getPixel(x, 10)) == 255) {
                if (!White) {
                    for (int y = 0; y < tiles.length; y++) {
                        tiles[x_i][y] = new Board.Tile(x, tiles[x_i][y].Y);
                    }
                    x_i++;
                    White = !White;
                }
            }
            if (Color.red(bitmap.getPixel(x, 10)) == 0) {
                if (White) {
                    for (int y = 0; y < tiles.length; y++) {
                        tiles[x_i][y] = new Board.Tile(x, tiles[x_i][y].Y);
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
                    for (int x = 0; x < tiles.length; x++) {
                        tiles[x][y_i] = new Board.Tile(tiles[x][y_i].X, y);
                    }
                    y_i++;
                    White = !White;
                }
            }
            if (Color.red(bitmap.getPixel(10, y)) == 0) {
                if (White) {
                    for (int x = 0; x < tiles.length; x++) {
                        tiles[x][y_i] = new Board.Tile(tiles[x][y_i].X, y);
                    }
                    y_i++;
                    White = !White;
                }
            }
        }

        tileSize = tiles[1][1].X - tiles[0][0].X;
    };
    public void lightUpTile(Tile tile) {}; // подсветка тайла
    public void delightUpTile(Tile tile) {}; // отсветка тайла
    public int canMove(Tile tile) {return 0;}; // проверка на возможность хода фигуры
    public void moveFigure() {};
    public void deleteFigure() {};
    public void addFigure() {};
    public void gameEnd() {}; // конец игры
    public Tile findNearestTile(Tile tile) {return tiles[0][0];}; // находит ближайшие координаты тайла

    // Struct
    public static class Tile {
        public int X;
        public int Y;
        public Tile(int X, int Y) {
            this.X = X;
            this.Y = Y;
        }
        public aFigure figure;
    }
}