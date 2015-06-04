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
        AlertDialog.Builder b = new AlertDialog.Builder(new ContextThemeWrapper(this, android.R.style.Theme_Holo_Light));
        b.setIcon(R.mipmap.ic_launcher);
        b.setTitle("Amarelinha Hamburguer's");
        b.setMessage("Amarelinha Hamburguer's é uma pequena empresa de jovens angolanos que nasceu de um sonho... Fazer os melhores lanches da cidade (Hamburgueres, Bifanas, Pregos, Fahitas, entre outros) e prestar um bom serviço aos seus clientes!\n\nEm 2012 começavamos esta aventura!\n\nO nosso objectivo é de proporcionar aos Angolanos, um serviço fidedigno e com qualidade, prestar um serviço personalizado e eficiente.\n\nTemos sempre como objectivo, a qualidade da oferta, a eficiência do serviço e a satisfação do cliente, por isso, colocamos todo o nosso amor no nosso producto.\n\n\nOs nossos Produtos são artesanais (produzimos a nossa própria carne e muito em breve os seremos nós a produzir o nosso pão), tudo feito com bastante amor e carinho.\n\n\nAgora que já sabe um pouco a nosso respeito esteja atento às nossas redes sociais, participe nos nossos passatempos com direito a lanches de oferta e sempre que fizer uma compra, peça o seu código para inserir na zona do 'cartão de refeição', quando acumular 10 códigos terá direito a um lanche de oferta à sua escolha.");
        b.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // default
            }
        });
        b.show();
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