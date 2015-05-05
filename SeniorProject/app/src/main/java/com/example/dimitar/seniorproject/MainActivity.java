package com.example.dimitar.seniorproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    DBAdapter myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DBAdapter(this);

        myDB.open();
    }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void displayDCHeroes(View view) {
        switch (view.getId()){
            case R.id.dc_comics_button:
                new CreateDCDB().execute("");
                break;
        }
    }

    private class CreateDCDB extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            myDB.deleteAll();
            myDB.insertRow(
                    "Batman",
                    "Bruce Wayne",
                    "Gotham",
                    "Batman is the secret identity of Bruce Wayne. Witnessing the murder of his parents as a child leads him to train himself to physical and intellectual perfection and don a bat-themed costume in order to fight crime. Unlike most superheroes, he does not possess any superpowers; he makes use of intellect, detective skills, science and technology, wealth, physical prowess, and intimidation in his war on crime.",
                    "The Joker",
                    "Thomas and Martha Wayne(parents) - deceased",
                    "Robin, Alfred Pennyworth",
                    R.drawable.batman,
                    "https://www.youtube.com/watch?v=EXeTwQWrcwY",
                    R.drawable.batman_details
            );
            myDB.insertRow(
                    "Superman",
                    "Kal El, Clark Kent",
                    "Metropolis, Krypton(birth)",
                    "Superman's planet was destroy in a war, when he was just a baby. With last efforts his father managed to send him away on a vessel just before Krypton exploded. Superman's destination was Earth, where he was adopted and raised. His powers has close connections with the Sun and include - flying, superhuman strength, speed, hearing, longevity, stamina, and intelligence, invulnerability, heat vision, x-ray vision, freezing breath, healing factor.",
                    "Lex Luthor",
                    "Jonathan and Martha Kent(Earth parents), Jor-El and Lara - deceased",
                    "Supergirl, Superboy, Nightwing, Wonder Woman",
                    R.drawable.superman,
                    "https://www.youtube.com/watch?v=-DaPBBOHfsA",
                    R.drawable.superman_details
            );
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Intent intent = new Intent(getBaseContext(), DisplayHeroesActivity.class);
            startActivity(intent);
        }

        @Override
        protected void onPreExecute() {
            ToastFunction();
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

    public void displayMarvelHeroes(View view) throws InterruptedException {
        switch (view.getId()){
            case R.id.marvel_button:
                new CreateMarvelDB().execute("");
                break;
        }
    }

    private class CreateMarvelDB extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            myDB.deleteAll();
            myDB.insertRow(
                    "Iron Man",
                    "Anthony \"Tony\" Stark",
                    "New York City",
                    "An American billionaire playboy, industrialist, and ingenious engineer, Tony Stark suffers a severe chest injury during a kidnapping in which his captors attempt to force him to build a weapon of mass destruction. He instead creates a powered suit of armor to save his life and escape captivity. Later, Stark augments his suit with weapons and other technological devices he designed through his company, Stark Industries. He uses the suit and successive versions to protect the world as Iron Man, while at first concealing his true identity.",
                    "Justin Hammer",
                    "Howard and Maria Stark(parents) - deceased",
                    "War Machine",
                    R.drawable.ironman,
                    "https://www.youtube.com/watch?v=Ke1Y3P9D0Bc",
                    R.drawable.ironman_details
            );
            myDB.insertRow(
                    "Captain America",
                    "Steven \"Steve\" Rogers",
                    "New York City",
                    "Captain America wears a costume that bears an American flag motif, and is armed with a nearly indestructible shield that he throws at foes. The character is usually depicted as the alter ego of Steve Rogers, a frail young man enhanced to the peak of human perfection by an experimental serum to aid the United States government's imminent efforts in World War II. Near the end of the war, he was trapped in ice and survived in suspended animation until he was revived in the present day to subsequently become the long-time leader of the Avengers.",
                    "The Red Skull",
                    "Sarah and Joseph Rodgers",
                    "James Barnes(Winter Soldier), Falcon, Sharon Carter",
                    R.drawable.captain_america,
                    "https://www.youtube.com/watch?v=7SlILk2WMTI",
                    R.drawable.captainamerica_details
            );
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Intent intent = new Intent(getBaseContext(), DisplayHeroesActivity.class);
            startActivity(intent);
        }

        @Override
        protected void onPreExecute() {
            ToastFunction();
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

    public void displayOtherHeroes(View view) throws InterruptedException {
        switch (view.getId()){
            case R.id.other_button:
                new CreateOtherDB().execute("");
                break;
        }
    }

    private class CreateOtherDB extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            myDB.deleteAll();
            myDB.insertRow(
                    "Naruto",
                    "Naruto Uzumaki",
                    "Leaf Village, Naruto Universe",
                    "Naruto is a Japanese manga series written and illustrated by Masashi Kishimoto. It tells the story of Naruto Uzumaki, an adolescent ninja who constantly searches for recognition and dreams to become the Hokage, the ninja in his village who is acknowledged as the leader and the strongest of all.",
                    "Madara Uchiha, Tobi",
                    "Minato and Kushina Uzumaki(parents) - deceased",
                    "Sasuke Uchiha, Kakashi Hatake, Sakura Haruno",
                    R.drawable.naruto,
                    "https://www.youtube.com/watch?v=LkyOM4GpkRw",
                    R.drawable.naruto_details
            );
            myDB.insertRow(
                    "Yoh",
                    "Asakura Yoh",
                    "Patch Village, Shaman King Universe",
                    "The series primarily focuses on a teenager named Yoh Asakura, who reveals to his classmate Manta Oyamada that he is a shaman when fighting a group delinquents led by Ryu. Wishing to lead a peaceful life, Yoh has been training from an early age to become the titular \"Shaman King\", who will be able to change the world according to his will. During Yoh's training, Manta meets Yoh's demanding fiancee, Anna Kyoyama and Yoh's spirit partner, the samurai Amidamaru. In his journey to become Shaman King, Yoh also meets with a number of rival shamans who seek to become Shaman King for their own reasons and visions of the future, some who become his allies and others who become his enemies.",
                    "Asakura Hao",
                    "Asakura Mikihisa and Asakura Keiko(parents), Yohmei(grandfather), Anna Kyoyama(fiancee)",
                    "Amidamaru",
                    R.drawable.yoh,
                    "https://www.youtube.com/watch?v=8qLl145FqQg",
                    R.drawable.yoh_details
            );
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Intent intent = new Intent(getBaseContext(), DisplayHeroesActivity.class);
            startActivity(intent);
        }

        @Override
        protected void onPreExecute() {
            ToastFunction();
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

    public void changeToQuizActivity (View view) {
        Intent intent = new Intent(getBaseContext(), QuizActivity.class);
        startActivity(intent);
    }

    //Toast function
    private void ToastFunction() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,(ViewGroup) findViewById(R.id.toast_layout_id));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myDB.close();
    }


}
