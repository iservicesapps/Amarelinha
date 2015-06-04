package pt.iservices.amarelinha;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by mariocosme on 02/06/15.
 */
public class MainMenuActivity extends Activity {

    private ProgressDialog pd;
    private MySQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);
        setUpLayout();
        setUpDB();
    }

    private void setUpLayout() {
        Typeface chalkboardBold = Typeface.createFromAsset(getAssets(), "fonts/Chalkboard-Bold.ttf");
        TextView titleTV = (TextView) findViewById(R.id.titleTV);
        titleTV.setTypeface(chalkboardBold);
    }

    private void setUpDB() {
        db = new MySQLiteHelper(this);
        if (!db.isDbFilled()) {
            pd = new ProgressDialog(this);
            pd.setIndeterminate(true);
            pd.setMessage("A cozinhar o menu...");
            pd.setCancelable(false);


            new FillDb().execute("");
        }
    }

    public void openMenu(View v) {
        Intent mainIntent = new Intent(this, FoodMenuActivity.class);
        startActivity(mainIntent);
    }

    public void openCard(View v) {
        Log.d("teste", "card!");
    }

    public void openContacts(View v) {
        Intent mainIntent = new Intent(this, ContactsActivity.class);
        startActivity(mainIntent);
    }

    private class FillDb extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            db.insertCategoria(new Food(R.drawable.cartao_refeicao, "Hamburguer de Frango", 800));
            db.insertCategoria(new Food(R.drawable.cartao_refeicao, "Hamburguer Tuneza", 1000));
            db.insertCategoria(new Food(R.drawable.cartao_refeicao, "Hamburguer Amarelinha", 800));
            db.insertCategoria(new Food(R.drawable.cartao_refeicao, "Hamburguer de Carne", 900));
            db.insertCategoria(new Food(R.drawable.cartao_refeicao, "Fahita Mista", 0)); // TODO preço deste
            db.insertCategoria(new Food(R.drawable.cartao_refeicao, "Fahita de Atum", 900));
            db.insertCategoria(new Food(R.drawable.cartao_refeicao, "Fahita de Carne", 900));
            db.insertCategoria(new Food(R.drawable.cartao_refeicao, "Fahita de Frango", 900));
            db.insertCategoria(new Food(R.drawable.cartao_refeicao, "Prego no Pão", 900));
            db.insertCategoria(new Food(R.drawable.cartao_refeicao, "Bifana no Pão", 900));
            db.insertCategoria(new Food(R.drawable.cartao_refeicao, "Cachorro Quente", 800));
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.hide();
        }
    }
}