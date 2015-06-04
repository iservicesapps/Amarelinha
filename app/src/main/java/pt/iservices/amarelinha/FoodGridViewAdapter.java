package pt.iservices.amarelinha;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mariocosme on 25/05/15.
 */
public class FoodGridViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Food> menu;

    public FoodGridViewAdapter(Context c, ArrayList<Food> menu) {
        this.context = c;
        this.menu = menu;
    }

    @Override
    public int getCount() {
        return menu.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Typeface chalkboardBold = Typeface.createFromAsset(context.getAssets(), "fonts/Chalkboard-Bold.ttf");
        Typeface chalkduster = Typeface.createFromAsset(context.getAssets(), "fonts/Chalkduster.ttf");

        Food f = menu.get(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.food_item, null);
        ImageView foodImage = (ImageView) convertView.findViewById(R.id.foodImage);
        // TODO add image

        TextView foodNameTv = (TextView) convertView.findViewById(R.id.foodNameTv);
        foodNameTv.setTypeface(chalkduster);
        foodNameTv.setText(f.getName());

        TextView foodPriceTv = (TextView) convertView.findViewById(R.id.foodPriceTv);
        foodPriceTv.setTypeface(chalkboardBold);
        foodPriceTv.setText(String.valueOf(f.getPrice()) + " kz");

        return convertView;
    }
}