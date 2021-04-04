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
import android.widget.Button;
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

        Button button = (Button) view.findViewById(R.id.button_start);
        button.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v)
            {
                Coords[][] coordinates = new Board().tileCoords;

                notation.setText(null);

                int top = board.getTop()/16;
                int left = board.getLeft()/16;
                int bottom = board.getBottom()/16;
                int right = board.getRight()/16;

                for (int x=0;x<8;x++){
                    for (int y=0;y<8;y++){
                        coordinates[x][y]=new Coords(bottom*(x+1), right*(x+1));
                    }
                }

            }
        });


        super.onViewCreated(view, savedInstanceState);

    }
}