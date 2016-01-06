package productivity.yaw.asare.ordr;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                   PriorityFragment.OnFragmentInteractionListener,
                   ArchiveFragment.OnFragmentInteractionListener,
                    CreateTaskFragment.CreateTaskListener, View.OnClickListener {

    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SharedPreferences preferences = getSharedPreferences("settings",MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();
        setTheme(preferences.getInt("theme", R.style.lightgrey));
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new CreateTaskFragment();
                newFragment.show(getFragmentManager(), "create priority");

            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        TextView todo = (TextView)drawer.findViewById(R.id.nav_priorities);
        todo.setOnClickListener(this);

        TextView archive = (TextView)drawer.findViewById(R.id.nav_archive);
        archive.setOnClickListener(this);

        TextView about = (TextView)drawer.findViewById(R.id.about);
        about.setOnClickListener(this);

        GridView themeGrid = (GridView)drawer.findViewById(R.id.theme_grid);
        final int[] ids = {R.drawable.whitegreytheme,R.drawable.bluepurpletheme,
                           R.drawable.greyblacktheme,R.drawable.orangebrowntheme,
                           R.drawable.pinkbluetheme, R.drawable.yellowgreentheme};
        themeGrid.setAdapter(new ThemeGridAdapter(this,ids));
        themeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        getApplicationContext().setTheme(R.style.lightgrey);
                        editor.putInt("theme", R.style.lightgrey);
                        break;
                    case 1:
                        getApplicationContext().setTheme(R.style.bluepurple);
                        editor.putInt("theme", R.style.bluepurple);
                        break;
                    case 2:
                        getApplicationContext().setTheme(R.style.darkgrey);
                        editor.putInt("theme", R.style.darkgrey);
                        break;
                    case 3:
                        getApplicationContext().setTheme(R.style.orangebrown);
                        editor.putInt("theme", R.style.orangebrown);
                        break;
                    case 4:
                        getApplicationContext().setTheme(R.style.bluepink);
                        editor.putInt("theme", R.style.bluepink);
                        break;
                    case 5:
                        getApplicationContext().setTheme(R.style.greenyellow);
                        editor.putInt("theme", R.style.greenyellow);
                        break;
                }
                editor.apply();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        refresh();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            showExitDialog();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        Class fragmentClass = null;

        int id = item.getItemId();

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_content, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onConfirm(CreateTaskFragment dialog) {
        Priority p = dialog.getPriority();
        DBHelper helper = new DBHelper(getBaseContext());
        helper.addPriority(p);

        refresh();
    }

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        switch(view.getId()){
            case R.id.nav_priorities:

                try {
                    fragment = (Fragment)PriorityFragment.class.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                 fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_content, fragment).commit();
                drawer.closeDrawers();
                break;
            case R.id.nav_archive:

                try {
                    fragment = (Fragment)ArchiveFragment.class.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_content, fragment).commit();
                drawer.closeDrawers();
                break;

            case R.id.about:
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_content, fragment).commit();
                drawer.closeDrawers();
                showAboutDialog();
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            showExitDialog();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void showExitDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit?");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
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

    public void showAboutDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("About");
        builder.setMessage("This is an open-source application created by Yaw Asare. \n\n" +
                "Project can be found at \n" +
                "github.com/yawasare/Ordr-android");
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onResume(){
        super.onResume();
        initNotificationButton();
    }

    public int getOverallLevel() {
        DBHelper helper = new DBHelper(this);
        ArrayList<Priority> ps = helper.getCurrentPriorities();
        Iterator<Priority> iter = ps.iterator();
        int level = 1;

        while(iter.hasNext()){
            level = Math.max(level, iter.next().getPriorityLevel());
        }

        return level;
    };

    public void initToolbarColor(){
        Toolbar bar = (Toolbar)findViewById(R.id.toolbar);
        TypedArray ta =  obtainStyledAttributes(Constant.PRIORITY_BACKGROUNDS_FULL);
        Drawable drawable = ta.getDrawable(getOverallLevel() - 1);
        bar.setBackground(drawable);

        ta.recycle();
    }

    public void initStatusBox(){
        TextView tv =(TextView)findViewById(R.id.overall_status_text);
        TypedArray ta =  obtainStyledAttributes(Constant.PRIORITY_BACKGROUNDS);
        Drawable drawable = ta.getDrawable(getOverallLevel() -1);
        tv.setBackground(drawable);
        ta.recycle();
        tv.setText(Constant.PRIORITY_LEVEL_STRINGS[getOverallLevel() - 1]);
    }

    public void initNotificationButton(){
        final SharedPreferences preferences = getSharedPreferences("settings",MODE_PRIVATE);
        boolean notificationsEnabled = preferences.getBoolean("notifications", false);
        Switch s = (Switch)findViewById(R.id.notification_switch);
        s.setChecked(notificationsEnabled);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor edit = preferences.edit();
                edit.putBoolean("notifications", isChecked);
                edit.apply();

                if(!isChecked){
                    cancelNotifications();
                }
                else{
                    updateNotifications();
                }
            }
        });
    }

    public void updateNotifications(){

        NotificationManager NM;
        NM=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NM.cancelAll();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.add(Calendar.DAY_OF_YEAR, 1);

        Intent i = new Intent(this, MainActivity.class);
        i.setAction("com.yaw.notify.push");

        PendingIntent pintent = PendingIntent.getService(this,Constant.ALARM_ID,
                i,PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pintent);

    }

    public void cancelNotifications(){
        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        Intent i = new Intent(this, MainActivity.class);
        i.setAction("com.yaw.notify.push");
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                Constant.ALARM_ID, i, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntent.cancel();
        manager.cancel(pendingIntent);
    }

    public void refresh(){
        try {
            fragment = (Fragment)PriorityFragment.class.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        initToolbarColor();
        initStatusBox();
        initNotificationButton();

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_content, fragment).commit();
    }
}
