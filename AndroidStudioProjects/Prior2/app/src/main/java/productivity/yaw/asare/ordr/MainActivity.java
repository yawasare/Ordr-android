package productivity.yaw.asare.ordr;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                   PriorityFragment.OnFragmentInteractionListener,
                   ArchiveFragment.OnFragmentInteractionListener,
                    CreateTaskFragment.CreateTaskListener{

    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SharedPreferences preferences = getSharedPreferences("settings",MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();
        setTheme(preferences.getInt("theme", R.style.bluepink));
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

        GridView themeGrid = (GridView)drawer.findViewById(R.id.theme_grid);
        final int[] ids = {R.drawable.pinkbluetheme,R.drawable.bluepurpletheme,
                           R.drawable.greyblacktheme,R.drawable.orangebrowntheme,
                           R.drawable.whitegreytheme, R.drawable.yellowgreentheme};
        themeGrid.setAdapter(new ThemeGridAdapter(this,ids));
        themeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch(position){
                    case 0:
                        getApplicationContext().setTheme(R.style.bluepink);
                        editor.putInt("theme", R.style.bluepink);
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
                        getApplicationContext().setTheme(R.style.lightgrey);
                        editor.putInt("theme", R.style.lightgrey);
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

        try {
            fragment = (Fragment)PriorityFragment.class.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_content, fragment).commit();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        Class fragmentClass = null;

        int id = item.getItemId();

        if (id == R.id.nav_priorities) {
            fragmentClass = PriorityFragment.class;

        } else if (id == R.id.nav_archive) {
            fragmentClass = ArchiveFragment.class;

        }

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


        try {
            fragment = (Fragment)PriorityFragment.class.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_content, fragment).commit();
    }
}
