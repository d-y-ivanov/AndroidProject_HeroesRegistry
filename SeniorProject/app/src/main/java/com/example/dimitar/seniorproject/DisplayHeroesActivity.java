package com.example.dimitar.seniorproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class DisplayHeroesActivity extends ActionBarActivity {
    DBAdapter myDB;
    public final static String EXTRA_MESSAGE = "com.example.dimitar.seniorproject.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_heroes);

        openDB();
        populateListView();
        registerClickCallback();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDB();
    }


    private void openDB() {
        myDB = new DBAdapter(this);
        myDB.open();
    }

    private void closeDB() {
        myDB.close();
    }

    //test to populate ListView
    private void populateListView() {
        Cursor cursor = myDB.getAllRows();

        startManagingCursor(cursor);

        String[] fromFieldNames = new String[] {DBAdapter.KEY_NAME, DBAdapter.KEY_REALNAME, DBAdapter.KEY_UNIVERSE, DBAdapter.KEY_ICONID};
        int[] toViewIDs = new int[] {R.id.heroes_txtName, R.id.heroes_txtRealName, R.id.heroes_txtUniverse, R.id.heroes_icon};

        SimpleCursorAdapter myCursorAdapter = new SimpleCursorAdapter( this, //Context
                R.layout.item_view, //Row layout template
                cursor, //cursor (set DB records to map)
                fromFieldNames, //DB columns names
                toViewIDs); //View ids to put info in

        ListView myList = (ListView) findViewById(R.id.HeroesListView);
        myList.setAdapter(myCursorAdapter);
    }


    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.HeroesListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long idInDb) {
                Intent intent = new Intent(getBaseContext(), InfoActivity.class);
                intent.putExtra(EXTRA_MESSAGE,idInDb);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_heroes, menu);
        return true;
    }

}
