package productivity.yaw.asare.ordr;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
/**
 * Created by yaw on 12/3/15.
 */
public class CreateTaskFragment extends DialogFragment implements OnSeekBarChangeListener{
    View view;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        view = inflater.inflate(R.layout.create_priority,null);
        SeekBar deadlineBar = (SeekBar)view.findViewById(R.id.deadline_bar);
        deadlineBar.setOnSeekBarChangeListener(this);


        SeekBar durationBar = (SeekBar)view.findViewById(R.id.duration_bar);
        durationBar.setOnSeekBarChangeListener(this);

        SeekBar importanceBar = (SeekBar)view.findViewById(R.id.importance_bar);
        importanceBar.setOnSeekBarChangeListener(this);


        builder.setView(view)
            .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    // create new task
                }
            })
            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    CreateTaskFragment.this.getDialog().cancel();
                }
            });

        return builder.create();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch(seekBar.getId()){
            case(R.id.deadline_bar): {
                TextView tv = (TextView) view.findViewById(R.id.description1);
                tv.setText(getResources().getStringArray(R.array.deadlines)[progress]);
            }
            break;
            case(R.id.duration_bar): {
                TextView tv2 = (TextView) view.findViewById(R.id.description3);
                tv2.setText(getResources().getStringArray(R.array.durations)[progress]);
            }
            break;
            case(R.id.importance_bar): {
                TextView tv3 = (TextView) view.findViewById(R.id.description2);
                tv3.setText(getResources().getStringArray(R.array.importances)[progress]);
            }
            break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
