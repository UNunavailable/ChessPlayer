package com.example.chessplayer;


import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class BoardFragment extends DialogFragment {
    FrameLayout layout;
    Board boardInstance;
    TextView notation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_board, container, false);
        boardInstance = new Board(this, rootView.findViewById(R.id.image_board));
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
        super.onViewCreated(view, savedInstanceState);
    }

    public void startDialog(int posX, int posY, boolean isWhite, boolean canMove){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Выберите фигуру для превращения");
        builder.setItems(new CharSequence[]
                        {"Ферьзь", "Ладья", "Слон", "Конь"},
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        switch (which) {
                            case 0:
                                boardInstance.changeFigure(posX, posY, 5, isWhite, canMove);
                                break;
                            case 1:
                                boardInstance.changeFigure(posX, posY, 4, isWhite, canMove);
                                break;
                            case 2:
                                boardInstance.changeFigure(posX, posY, 2, isWhite, canMove);
                                break;
                            case 3:
                                boardInstance.changeFigure(posX, posY, 3, isWhite, canMove);
                                break;
                        }
                    }
                });
        builder.create().show();
    }

}