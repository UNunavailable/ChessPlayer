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

        Button button = (Button) view.findViewById(R.id.button_start);
        button.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v)
            {

                boardInstance.addFigure(0,6,Constants.PAWN, true, true);
                boardInstance.addFigure(1,6,Constants.PAWN, true, true);
                boardInstance.addFigure(2,6,Constants.PAWN, true, true);
                boardInstance.addFigure(3,6,Constants.PAWN, true, true);
                boardInstance.addFigure(4,6,Constants.PAWN, true, true);
                boardInstance.addFigure(5,6,Constants.PAWN, true, true);
                boardInstance.addFigure(6,6,Constants.PAWN, true, true);
                boardInstance.addFigure(7,6,Constants.PAWN, true, true);
                boardInstance.addFigure(0,1,Constants.PAWN, false, false);
                boardInstance.addFigure(1,1,Constants.PAWN, false, false);
                boardInstance.addFigure(2,1,Constants.PAWN, false, false);
                boardInstance.addFigure(3,1,Constants.PAWN, false, false);
                boardInstance.addFigure(4,1,Constants.PAWN, false, false);
                boardInstance.addFigure(5,1,Constants.PAWN, false, false);
                boardInstance.addFigure(6,1,Constants.PAWN, false, false);
                boardInstance.addFigure(7,1,Constants.PAWN, false, false);

                boardInstance.addFigure(0,7,Constants.ROOK, true, true);
                boardInstance.addFigure(7,7,Constants.ROOK, true, true);
                boardInstance.addFigure(0,0,Constants.ROOK, false, false);
                boardInstance.addFigure(7,0,Constants.ROOK, false, false);

                boardInstance.addFigure(1,7,Constants.KNIGHT, true, true);
                boardInstance.addFigure(6,7,Constants.KNIGHT, true, true);
                boardInstance.addFigure(1,0,Constants.KNIGHT, false, false);
                boardInstance.addFigure(6,0,Constants.KNIGHT, false, false);

                boardInstance.addFigure(2,7,Constants.BISHOP, true, true);
                boardInstance.addFigure(5,7,Constants.BISHOP, true, true);
                boardInstance.addFigure(2,0,Constants.BISHOP, false, false);
                boardInstance.addFigure(5,0,Constants.BISHOP, false, false);

                boardInstance.addFigure(3,7,Constants.QUEEN, true, true);
                boardInstance.addFigure(3,0,Constants.QUEEN, false, false);

                boardInstance.addFigure(4,7,Constants.KING, true, true);
                boardInstance.addFigure(4,0,Constants.KING, false, false);
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