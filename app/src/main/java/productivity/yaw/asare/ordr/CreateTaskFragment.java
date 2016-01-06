package productivity.yaw.asare.ordr;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
/**
 * Created by yaw on 12/3/15.
 */
public class CreateTaskFragment extends DialogFragment implements OnSeekBarChangeListener{
    View view;

    Priority mPriority;


    AlertDialog dialog;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mPriority = new Priority();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        view = inflater.inflate(R.layout.create_priority,null);

        SeekBar deadlineBar = (SeekBar)view.findViewById(R.id.deadline_bar);
        deadlineBar.setOnSeekBarChangeListener(this);


        SeekBar durationBar = (SeekBar)view.findViewById(R.id.duration_bar);
        durationBar.setOnSeekBarChangeListener(this);

        SeekBar importanceBar = (SeekBar)view.findViewById(R.id.importance_bar);
        importanceBar.setOnSeekBarChangeListener(this);

        EditText ed = (EditText)view.findViewById(R.id.editText);
        ed.setImeOptions(EditorInfo.IME_ACTION_DONE);
        ed.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0 && !s.toString().isEmpty()){
                    ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    mPriority.setTaskName(s.toString());
                }
                else
                    ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        builder.setView(view)
            .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    // create new task
                    mListener.onConfirm(CreateTaskFragment.this);
                }
            })
            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    CreateTaskFragment.this.getDialog().cancel();
                }
            });


         dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {
                    ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
            }
             });

        return dialog;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch(seekBar.getId()){
            case(R.id.deadline_bar): {
                TextView tv = (TextView) view.findViewById(R.id.description1);
                tv.setText(getResources().getStringArray(R.array.deadlines)[progress]);
                mPriority.setDeadline(progress);
            }
            break;
            case(R.id.duration_bar): {
                TextView tv2 = (TextView) view.findViewById(R.id.description3);
                tv2.setText(getResources().getStringArray(R.array.durations)[progress]);
                mPriority.setDuration(progress);
            }
            break;
            case(R.id.importance_bar): {
                TextView tv3 = (TextView) view.findViewById(R.id.description2);
                tv3.setText(getResources().getStringArray(R.array.importances)[progress]);
                mPriority.setImportance(progress);
            }
            break;
        }
    }

    public Priority getPriority(){
        return mPriority;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public interface CreateTaskListener{
        public void onConfirm(CreateTaskFragment dialog);
    }

    CreateTaskListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            // Instantiate the CreateTaskListener so we can send events to the host
            mListener = (CreateTaskListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }

    }



}