package pt.iservices.amarelinha;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by mariocosme on 02/06/15.
 */
public class MainMenuActivity extends Activity {

    private ProgressDialog pd;
    private MySQLiteHelper db;
    private ImageView contactosImage, cartaoRefeicaoImage, menusImage;
    private LinearLayout ll;

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
        ll = (LinearLayout) findViewById(R.id.ll);
        contactosImage = (ImageView) findViewById(R.id.contactosImage);
        cartaoRefeicaoImage = (ImageView) findViewById(R.id.cartaoRefeicaoImage);
        menusImage = (ImageView) findViewById(R.id.menusImage);

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

    public void showInfo(View v) {
        startActivity(new Intent(this, InfoActivity.class));
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        Picasso.with(this).load(R.drawable.menus).resize(ll.getMeasuredWidth(), getHeightWithRacio(R.drawable.menus, ll.getMeasuredWidth())).into(menusImage);
        Picasso.with(this).load(R.drawable.contactos).resize(ll.getMeasuredWidth(), getHeightWithRacio(R.drawable.contactos, ll.getMeasuredWidth())).into(contactosImage);
        Picasso.with(this).load(R.drawable.cartao_refeicao).resize(ll.getMeasuredWidth(), getHeightWithRacio(R.drawable.cartao_refeicao, ll.getMeasuredWidth())).into(cartaoRefeicaoImage);
    }

    private int getHeightWithRacio(int image, int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), image, options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;

        return (imageHeight*width/imageWidth);
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
            db.insertCategoria(new Food(R.drawable.hamb_tuneza, "Hamburguer Tuneza", 1000));
            db.insertCategoria(new Food(R.drawable.hamb_amarelinha, "Hamburguer Amarelinha", 800));
            db.insertCategoria(new Food(R.drawable.hamb_carne, "Hamburguer de Carne", 900));
            db.insertCategoria(new Food(R.drawable.fahita_mista, "Fahita Mista", 0)); // TODO preço deste
            db.insertCategoria(new Food(R.drawable.fahita_atum, "Fahita de Atum", 900));
            db.insertCategoria(new Food(R.drawable.fahita_carne, "Fahita de Carne", 900));
            db.insertCategoria(new Food(R.drawable.fahita_frango, "Fahita de Frango", 900));
            db.insertCategoria(new Food(R.drawable.prego_pao, "Prego no Pão", 900));
            db.insertCategoria(new Food(R.drawable.bifana_pao, "Bifana no Pão", 900));
            db.insertCategoria(new Food(R.drawable.cachorro_quente, "Cachorro Quente", 800));
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.hide();
        }
    }
}