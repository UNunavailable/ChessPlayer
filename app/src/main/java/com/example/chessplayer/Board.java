package com.example.chessplayer;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.chessplayer.figures.Bishop;
import com.example.chessplayer.figures.King;
import com.example.chessplayer.figures.Knight;
import com.example.chessplayer.figures.Pawn;
import com.example.chessplayer.figures.Queen;
import com.example.chessplayer.figures.Rook;
import com.example.chessplayer.figures.aFigure;

public class Board {
    // Var
    public BoardFragment fragInstance;
    public ImageView board;
    public Tile[][] tiles = new Tile[8][8]; // массив фигур
    public ImageView[][] image_rounds = new ImageView[8][8];
    public int tileWidth;
    public int tileHeight;
    public float scaleWidth;
    public float scaleHeight;
    private boolean whiteTurn = true;

    // Methods
    public Board(BoardFragment fragInstance, ImageView board) {
        this.fragInstance = fragInstance;
        this.board = board;
    }

    public void init() {
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
        scaleWidth = board.getMeasuredWidth()/(float)bitmap.getWidth();
        scaleHeight = board.getMeasuredHeight()/(float)bitmap.getHeight();
    }; // Используется при запуске программы. Вызывается после того как прогрузятся все View.

    public void lightUpTile(int posX, int posY) {
        ImageView round = new ImageView(fragInstance.getContext());
        round.setImageResource(R.drawable.round);

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(tileWidth, tileHeight);
        fragInstance.layout.addView(round, layoutParams);
        image_rounds[posX][posY]=round;
        round.setX(tiles[posX][posY].X*scaleWidth);
        round.setY(tiles[posX][posY].Y*scaleHeight);
    } // подсветка тайла

    public void delightUpTile(int posX, int posY) {
        if(image_rounds[posX][posY] == null) { return; }
        image_rounds[posX][posY].setVisibility(View.GONE);
    } // отсветка тайла

    public int checkTile(int posX, int posY) {
        if(!ifInBounds(posX, posY)) return Constants.OUTOFBOARD;
        if(tiles[posX][posY].figure == null) return Constants.EMPTY;
        if(tiles[posX][posY].figure.isWhite) return Constants.WHITEFIGURE;
        if(!tiles[posX][posY].figure.isWhite) return Constants.BLACKFIGURE;
        return -1;
    }; // проверка на возможность хода фигуры

    public void makeTurn(int posX, int posY, int whereX, int whereY) {
        if(tiles[whereX][whereY].figure != null) deleteFigure(whereX, whereY);
        tiles[posX][posY].figure.move(whereX, whereY);
        changeTurn();
    };

    public void deleteFigure(int x, int y) {
        tiles[x][y].figure.image.setVisibility(View.GONE);
        tiles[x][y].figure = null;
    }; //

    public void addFigure(int posX, int posY, int figureCode, boolean isWhite, boolean canMove) {
        if(tiles[posX][posY].figure != null) { return; }
        ImageView imageView = new ImageView(fragInstance.getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                tileWidth,
                tileHeight
        );

        switch (figureCode) {
            case Constants.PAWN:
            {
                    if (isWhite) { imageView.setImageResource(R.drawable.w_pawn); }
                    else { imageView.setImageResource(R.drawable.b_pawn); }

                    fragInstance.layout.addView(imageView, layoutParams);
                    tiles[posX][posY].figure = new Pawn(this, imageView, posX, posY, isWhite, canMove);
                    break;
             }

            case Constants.ROOK:
            {
                    if (isWhite) { imageView.setImageResource(R.drawable.w_rook); }
                    else { imageView.setImageResource(R.drawable.b_rook); }

                    fragInstance.layout.addView(imageView, layoutParams);
                    tiles[posX][posY].figure = new Rook(this, imageView, posX, posY, isWhite, canMove);
                    break;
            }
            case Constants.KNIGHT:
            {
                if (isWhite) { imageView.setImageResource(R.drawable.w_knight); }
                else { imageView.setImageResource(R.drawable.b_knight); }

                fragInstance.layout.addView(imageView, layoutParams);
                tiles[posX][posY].figure = new Knight(this, imageView, posX, posY, isWhite, canMove);
                break;
            }
            case Constants.BISHOP:
            {
                if (isWhite) { imageView.setImageResource(R.drawable.w_bishop); }
                else { imageView.setImageResource(R.drawable.b_bishop); }

                fragInstance.layout.addView(imageView, layoutParams);
                tiles[posX][posY].figure = new Bishop(this, imageView, posX, posY, isWhite, canMove);
                break;
            }
            case Constants.QUEEN:
            {
                if (isWhite) { imageView.setImageResource(R.drawable.w_queen); }
                else { imageView.setImageResource(R.drawable.b_queen); }

                fragInstance.layout.addView(imageView, layoutParams);
                tiles[posX][posY].figure = new Queen(this, imageView, posX, posY, isWhite, canMove);
                break;
            }

            case Constants.KING:
            {
                if (isWhite) { imageView.setImageResource(R.drawable.w_king); }
                else { imageView.setImageResource(R.drawable.b_king); }

                fragInstance.layout.addView(imageView, layoutParams);
                tiles[posX][posY].figure = new King(this, imageView, posX, posY, isWhite, canMove);
                break;
            }

                default:
                    { break;}
        }

        imageView.setX(tiles[posX][posY].X*scaleWidth);
        imageView.setY(tiles[posX][posY].Y*scaleHeight);
    }; // Добавляет выбранную фигуру на выбранный номер тайла.

    public void changeTurn() {
        whiteTurn = !whiteTurn;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if(tiles[i][j].figure != null) {
                    tiles[i][j].figure.canMove = !tiles[i][j].figure.canMove;
                }
            }
        }
    }

    public void checkFinder(Tile attacker, Tile king) {}

    public void checkMateFinder(Tile attacker, Tile king) {}

    public void gameEnd() {}; // конец игры

    public int[] findNearestTile(int X, int Y, int[][] tilePos) {
        if(tilePos.length == 0) return null;
        int distance[] = new int[tilePos.length];
        for (int i = 0; i < tilePos.length; i++) {
            distance[i] = distanceTwoPoints(X, Y,
                    tiles[tilePos[i][0]][tilePos[i][1]].X, tiles[tilePos[i][0]][tilePos[i][1]].Y);
        }
        int maxDist = getMinIndex(distance);
        return new int[]{ tilePos[maxDist][0], tilePos[maxDist][1], distance[maxDist]};
    }; // находит ближайшие координаты тайла используя заданные номера тайлов и координаты. Возвращает номер тайла и расстояние до него.

    private int distanceTwoPoints(int X1, int Y1, int X2, int Y2) {
        return (int)Math.sqrt((X2-X1)*(X2-X1) + (Y2-Y1)*(Y2-Y1));
    }

    private int getMinIndex(int[] inputArray){
        int maxValue = inputArray[0];
        int index = 0;
        for(int i=1;i < inputArray.length;i++){
            if(inputArray[i] < maxValue){
                maxValue = inputArray[i];
                index = i;
            }
        }
        return index;
    }

    public boolean ifInBounds(int posX, int posY) {
        if(posX>7) return false;
        if(posY>7) return false;
        if(posX<0) return false;
        if(posY<0) return false;
        return true;
    } // Проверяет выходят ли заданные координаты за рамки поля. Возвращяет true если координаты находятся в рамках поля.

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