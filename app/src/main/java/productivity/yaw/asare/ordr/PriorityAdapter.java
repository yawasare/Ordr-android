package productivity.yaw.asare.ordr;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by yaw on 12/10/15.
 */
public class PriorityAdapter extends BaseAdapter implements View.OnClickListener {

    ArrayList<Priority> mPriorities;
    Context mContext;
    private static LayoutInflater inflater=null;

    public PriorityAdapter(Activity activity, ArrayList<Priority> priorities){
        mContext = activity;
        mPriorities = priorities;
        inflater = ( LayoutInflater )mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mPriorities.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.priority_list_item, null);

        LinearLayout layout = (LinearLayout)vi.findViewById(R.id.list_item);
        TypedArray ta = mContext.obtainStyledAttributes(Constant.PRIORITY_BACKGROUNDS);
        Drawable drawable = ta.getDrawable(mPriorities.get(position).getPriorityLevel() - 1);
        layout.setBackground(drawable);
        ta.recycle();

        TextView status = (TextView)vi.findViewById(R.id.status_text);
        status.setText(Constant.PRIORITY_LEVEL_STRINGS[mPriorities.get(position).getPriorityLevel() - 1]);

        TextView title  = (TextView)vi.findViewById(R.id.priority_text);
        title.setText(mPriorities.get(position).getTaskname());
        title.setTextSize(Constant.PRIORITY_TEXT_SIZE[mPriorities.get(position).getPriorityLevel() - 1]);
        title.setPadding(0, Constant.PRIORITY_PADDING[mPriorities.get(position).getPriorityLevel() - 1], 0,0);
        title.setHeight(Constant.PRIORITY_LAYOUT_HEIGHT[mPriorities.get(position).getPriorityLevel() - 1]);


        ViewGroup.LayoutParams p = title.getLayoutParams();
        p.height = Constant.PRIORITY_LAYOUT_HEIGHT[mPriorities.get(position).getPriorityLevel() - 1];


        ImageView complete = (ImageView)vi.findViewById(R.id.complete_button);
        complete.setOnClickListener(this);
        complete.setTag(mPriorities.get(position));


        ImageView delete = (ImageView)vi.findViewById(R.id.cancel_button);
        delete.setOnClickListener(this);
        delete.setTag(mPriorities.get(position));

        return vi;
    }

    @Override
    public void onClick(View view) {
        DBHelper helper = new DBHelper(mContext);
        Priority p = ((Priority)view.getTag());

        switch (view.getId()){
            case R.id.priority_text:
                break;
            case R.id.complete_button:
                p.setCompleted(1);
                helper.updatePriority(p);
                mPriorities.remove(p);
                ((MainActivity)mContext).refresh();
                break;
            case R.id.cancel_button:
                helper.deletePriority(p);
                deleteDialog(p);
                break;
        }
        Collections.sort(mPriorities);
        this.notifyDataSetChanged();
    }

    public void deleteDialog(final Priority p){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Delete?");
        builder.setMessage("Will erase Todo");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mPriorities.remove(p);
                ((MainActivity)mContext).refresh();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
