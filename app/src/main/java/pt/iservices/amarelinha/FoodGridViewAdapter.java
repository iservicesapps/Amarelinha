package pt.iservices.amarelinha;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mariocosme on 25/05/15.
 */
public class FoodGridViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Food> menu;
    private int width;

    public FoodGridViewAdapter(Context c, ArrayList<Food> menu, int width) {
        this.context = c;
        this.menu = menu;
        this.width = width;
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
        Picasso.with(context).load(f.getImage()).resize(width, getHeightWithRacio(f.getImage())).into(foodImage);

        TextView foodNameTv = (TextView) convertView.findViewById(R.id.foodNameTv);
        foodNameTv.setTypeface(chalkduster);
        foodNameTv.setText(f.getName());

        TextView foodPriceTv = (TextView) convertView.findViewById(R.id.foodPriceTv);
        foodPriceTv.setTypeface(chalkboardBold);
        foodPriceTv.setText(String.valueOf(f.getPrice()) + " kz");

        return convertView;
    }

    private int getHeightWithRacio(int image) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), image, options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;

        return (imageHeight*width/imageWidth);
    }
}