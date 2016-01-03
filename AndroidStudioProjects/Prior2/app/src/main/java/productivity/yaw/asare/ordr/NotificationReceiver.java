package productivity.yaw.asare.ordr;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by yaw on 1/3/16.
 */
public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        Notification noti = new Notification.Builder(context)
                .setContentTitle("ordr" )
                .setContentText("Time to check in on your to-do list")
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(), 0);

        nm.notify(1, noti);
    }
}
