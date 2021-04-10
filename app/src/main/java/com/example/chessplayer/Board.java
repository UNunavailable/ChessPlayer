package com.example.chessplayer;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.chessplayer.figures.Pawn;
import com.example.chessplayer.figures.aFigure;

public class Board {
    // Var
    public BoardFragment fragInstance;
    public ImageView board;
    public Tile[][] tiles = new Tile[8][8]; // массив фигур
    public int tileWidth;
    public int tileHeight;

    // Method
    public Board(BoardFragment fragInstance) {
        this.fragInstance = fragInstance;
    }
    public void init() { // Инициализация доски
        int top = (int) board.getX();
        int left = (int) board.getY();
        for (int x=0;x<8;x++){
            for (int y=0;y<8;y++){
                tiles[x][y]=new Board.Tile(0, 0);
            }
        }

        Bitmap bitmap = ((BitmapDrawable)board.getDrawable()).getBitmap();

        int x_i = 0;
        boolean White = false;
        for (int x = left; x < bitmap.getWidth(); x++) {
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
        for (int y = top; y < bitmap.getHeight(); y++) {
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

        tileWidth = tiles[1][1].X - tiles[0][0].X;
        tileHeight = tiles[1][1].Y - tiles[0][0].Y;
    };
    public void lightUpTile(Tile tile) {}; // подсветка тайла
    public void delightUpTile(Tile tile) {}; // отсветка тайла
    public int canMove(Tile tile) {return 0;}; // проверка на возможность хода фигуры
    public void moveFigure() {};
    public void deleteFigure(int x, int y) {
        tiles[x][y].figure.image.setVisibility(View.GONE);
        tiles[x][y].figure = null;
    };
    public void addFigure(int x, int y, int figureCode) {
        if(tiles[x][y].figure != null) { return; }

        switch (figureCode) {
            case Constants.PAWN:
                ImageView imageView = new ImageView(fragInstance.getContext());
                imageView.setImageResource(R.drawable.w_pawn);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                        tileWidth,
                        tileHeight
                );
                fragInstance.layout.addView(imageView, layoutParams);
                imageView.setX(tiles[x][y].X*board.getMeasuredWidth()/1024);
                imageView.setY(tiles[x][y].Y*board.getMeasuredHeight()/1024);
                tiles[x][y].figure = new Pawn(this, imageView, x, y);
                break;
            default:
                break;
        }

    };
    public void checkFinder(Tile attacker, Tile king) {}
    public void checkMateFinder(Tile attacker, Tile king) {}
    public void gameEnd() {}; // конец игры
    public Tile findNearestTile(Tile tile) {return tiles[0][0];}; // находит ближайшие координаты тайла

    // Struct
    public static class Tile {
        public int X;
        public int Y;
        public Tile(int X, int Y) {
            this.X = X;
            this.Y = Y;
            figure = null;
        }
        public aFigure figure;
    }
}