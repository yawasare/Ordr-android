package productivity.yaw.asare.ordr;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

/**
 * Created by yaw on 12/10/15.
 */
public class PriorityAdapter extends BaseAdapter {

    Priority[] mPriorities;
    Context mContext;
    private static LayoutInflater inflater=null;

    public PriorityAdapter(Activity activity, Priority[] priorities){
        mContext = activity;
        mPriorities = priorities;
        inflater = ( LayoutInflater )mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mPriorities.length;
    }

    @Override
    public Object getItem(int position) {


        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
