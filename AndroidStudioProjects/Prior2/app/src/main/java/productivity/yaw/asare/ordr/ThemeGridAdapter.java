package productivity.yaw.asare.ordr;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by yaw on 12/28/15.
 */
public class ThemeGridAdapter extends BaseAdapter{

    Activity mContext;
    int[] mIDs;
    private static LayoutInflater inflater=null;
    SharedPreferences preferences;

    public ThemeGridAdapter(Activity activity, int[] ids ){
        mContext = activity;
        mIDs = ids;

        inflater= ( LayoutInflater )mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mIDs.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = view;
        if(v == null)
            v = inflater.inflate(R.layout.theme_icon, null);

        ImageView gradient= ((ImageView)v.findViewById(R.id.gradient_image));
        gradient.setImageResource(mIDs[position]);

        return v;
    }
}
