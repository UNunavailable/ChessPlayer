package com.example.chessplayer;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class BoardFragment extends Fragment {

    ImageView board;
    TextView notation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_board, container, false);
        board = (ImageView) rootView.findViewById(R.id.image_board);
        notation = (TextView) rootView.findViewById(R.id.text_notation);

        return rootView;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        notation.setText(null);

        int[] boardCordinates = new int[2];
        board.getLocationOnScreen(boardCordinates);

        int x = boardCordinates[0];
        int y = boardCordinates[1];


        notation.setText(x + " " + y);

        System.out.println(x + " " + y);


        Log.e("onStart", x + " " + y);
    }



}