package pt.iservices.amarelinha;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mariocosme on 04/06/15.
 */
public class FoodMenuActivity extends Activity {

    private GridView grid;
    private ArrayList<Food> foods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_menu_activity);

        foods = new MySQLiteHelper(this).getAllFoods();

        setUpLayout();
    }

    private void setUpLayout() {
        Typeface chalkboardBold = Typeface.createFromAsset(getAssets(), "fonts/Chalkboard-Bold.ttf");
        TextView titleTV = (TextView) findViewById(R.id.titleTV);
        titleTV.setTypeface(chalkboardBold);

        grid = (GridView) findViewById(R.id.gridview);
        grid.setAdapter(new FoodGridViewAdapter(this, foods));
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("teste", "Menu: " + foods.get(position).getName());
            }
        });
    }
}
