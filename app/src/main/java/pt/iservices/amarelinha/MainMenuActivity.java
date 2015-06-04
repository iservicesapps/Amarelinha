package pt.iservices.amarelinha;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mariocosme on 02/06/15.
 */
public class MainMenuActivity extends Activity {

    private ProgressDialog pd;
    private MySQLiteHelper db;
    private AQuery aq;

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
        aq = new AQuery(this);

        if (!db.isDbFilled()) {
            pd = new ProgressDialog(this);
            pd.setIndeterminate(true);
            pd.setMessage("A cozinhar o menu...");
            pd.setCancelable(false);


            new FillDb().execute("");
        }
    }

    public void openMenu(View v) {
        Log.d("teste", "menu!");
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
            aq.ajax(URL_GET_CONTENT, JSONObject.class, new AjaxCallback<JSONObject>() {

                @Override
                public void callback(String url, JSONObject json, AjaxStatus status) {
                    try {

                        if (json != null) {
                            db.resetDB();

                            JSONArray categorias = json.getJSONArray("categorias");

                            for (int i = 0; i < categorias.length(); i++) {
                                JSONObject obj = categorias.getJSONObject(i);
                                db.insertCategoria(new Categoria(obj.getInt("id_categoria"), obj.getInt("active"), obj.getString("name"), obj.getString("image")));
                            }

                            JSONArray motivos = json.getJSONArray("motivos");
                            for (int i = 0; i < motivos.length(); i++) {
                                JSONObject obj = motivos.getJSONObject(i);
                                db.insertMotivo(new Motivo(obj.getInt("id_motivo"), obj.getInt("id_categoria"), obj.getInt("width"), obj.getInt("height"), obj.getInt("active"), obj.getString("name"), obj.getString("image")));
                            }

                            editor.putInt("db_version", version);
                            editor.commit();

                            // Already skipped login
                            if (PreferenceManager.getDefaultSharedPreferences(LogInActivity.this).getBoolean("skiped_login", false) || PreferenceManager.getDefaultSharedPreferences(LogInActivity.this).getBoolean("facebook_login", false)) {
                                Intent i = new Intent(LogInActivity.this, ChooseCategoryActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.hide();
        }
    }
}