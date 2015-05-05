package com.example.dimitar.seniorproject;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class InfoActivity extends ActionBarActivity {
    DBAdapter myDB;
    private String videoURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        Long id = intent.getLongExtra(DisplayHeroesActivity.EXTRA_MESSAGE, 0);

        openDB();
        Cursor cursor = myDB.getRow(id);
        if (cursor.moveToFirst()) {
            String name = cursor.getString(DBAdapter.COL_NAME);
            String real_name = cursor.getString(DBAdapter.COL_REALNAME);
            String universe = cursor.getString(DBAdapter.COL_UNIVERSE);
            String history = cursor.getString(DBAdapter.COL_HISTORY);
            String nemesis = cursor.getString(DBAdapter.COL_NEMESIS);
            String relatives = cursor.getString(DBAdapter.COL_RELATIVES);
            String accomplices = cursor.getString(DBAdapter.COL_ACCOMPLICES);
            int iconid = cursor.getInt(DBAdapter.COL_ICONID);
            videoURL = cursor.getString(DBAdapter.COL_VIDEOURL);
            int pictureId = cursor.getInt(DBAdapter.COL_PICTUREID);

            TextView nameTextView = (TextView) findViewById(R.id.txtName);
            nameTextView.setText(name);
            TextView realNameTextView = (TextView) findViewById(R.id.txtRealName);
            realNameTextView.setText(real_name);
            TextView universeTextView = (TextView) findViewById(R.id.txtUniverse);
            universeTextView.setText(universe);
            TextView historyTextView = (TextView) findViewById(R.id.txtHistory);
            historyTextView.setText(history);
            TextView nemesisTextView = (TextView) findViewById(R.id.txtNemesis);
            nemesisTextView.setText(nemesis);
            TextView relativesTextView = (TextView) findViewById(R.id.txtRelatives);
            relativesTextView.setText(relatives);
            TextView accomplicesTextView = (TextView) findViewById(R.id.txtAccomplices);
            accomplicesTextView.setText(accomplices);
            ImageView iconImageView = (ImageView) findViewById(R.id.id_icon);
            iconImageView.setImageResource(pictureId);

            cursor.close();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myDB.close();
    }


    private void openDB() {
        myDB = new DBAdapter(this);
        myDB.open();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info, menu);
        return true;
    }


    public void goToLink(View view){
        Uri uriUrl = Uri.parse(videoURL);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}
