package pt.iservices.amarelinha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by mariocosme on 02/06/15.
 */
public class MainMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);
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
}