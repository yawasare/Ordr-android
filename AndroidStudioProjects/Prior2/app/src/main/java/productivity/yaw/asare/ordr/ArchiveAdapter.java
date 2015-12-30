package productivity.yaw.asare.ordr;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by yaw on 12/29/15.
 */
public class ArchiveAdapter extends BaseAdapter {
    ArrayList<Priority> mPriorities;
    Context mContext;
    private static LayoutInflater inflater=null;

    public ArchiveAdapter(Activity activity, ArrayList<Priority> priorities){
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
            vi = inflater.inflate(R.layout.archive_list_item, null);

        TextView title  = (TextView)vi.findViewById(R.id.archive_item_text);
        title.setText(mPriorities.get(position).getTaskname());


        return vi;
    }
}
