package com.example.chessplayer;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class BoardFragment extends Fragment {
    FrameLayout layout;
    Board boardInstance;
    TextView notation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_board, container, false);
        boardInstance = new Board(this);
        boardInstance.board = (ImageView) rootView.findViewById(R.id.image_board);
        notation = (TextView) rootView.findViewById(R.id.text_notation);

        return rootView;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        layout = getView().findViewById(R.id.boardFrame);
        boardInstance.board.post(new Runnable() { // Метод сработает как только картинка поля загрузится
            @Override
            public void run() {
                boardInstance.init();
            }
        });

        Button button = (Button) view.findViewById(R.id.button_start);
        button.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v)
            {
                notation.setText(null);
                boardInstance.addFigure(0,6,Constants.PAWN);
                boardInstance.addFigure(1,6,Constants.PAWN);
                boardInstance.addFigure(2,6,Constants.PAWN);
                boardInstance.addFigure(3,6,Constants.PAWN);
                boardInstance.addFigure(4,6,Constants.PAWN);
                boardInstance.addFigure(5,6,Constants.PAWN);
                boardInstance.addFigure(6,6,Constants.PAWN);
                boardInstance.addFigure(6,6,Constants.PAWN);
                // TODO Удалить проверку координат
                boolean checkCoords = false;
                if (checkCoords) {
                    Bitmap oldBitmap = ((BitmapDrawable) boardInstance.board.getDrawable()).getBitmap();
                    // copying to newBitmap for manipulation
                    Bitmap newBitmap = oldBitmap.copy(Bitmap.Config.ARGB_8888, true);
                    // traversing each pixel in Image as an 2D Array

                    for (int x = 0; x < boardInstance.tiles.length; x++) {
                        for (int y = 0; y < boardInstance.tiles.length; y++) {
                            for (int i = boardInstance.tiles[x][y].X + 10; i < boardInstance.tiles[x][y].X + boardInstance.tileWidth - 10; i++) {
                                for (int j = boardInstance.tiles[x][y].Y + 10; j < boardInstance.tiles[x][y].Y + boardInstance.tileHeight - 10; j++) {
                                    // getting each pixel
                                    int oldPixel = oldBitmap.getPixel(i, j);

                                    // each pixel is made from RED_BLUE_GREEN_ALPHA
                                    // so, getting current values of pixel
                                    int oldRed = Color.red(oldPixel);
                                    int oldBlue = Color.blue(oldPixel);
                                    int oldGreen = Color.green(oldPixel);
                                    int oldAlpha = Color.alpha(oldPixel);

                                    int newRed = (int) (oldRed / 2 + 80);
                                    int newGreen = (int) (oldGreen / 2 + 80);
                                    int newBlue = (int) (100);

                                    // applying new pixel values from above to newBitmap
                                    int newPixel = Color.argb(oldAlpha, newRed, newGreen, newBlue);
                                    newBitmap.setPixel(i, j, newPixel);
                                }
                            }
                        }
                    }
                    boardInstance.board.setImageBitmap(newBitmap);
                }
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}