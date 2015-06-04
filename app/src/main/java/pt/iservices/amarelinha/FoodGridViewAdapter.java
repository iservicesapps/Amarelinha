package pt.iservices.amarelinha;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

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
        Categoria c = categorias.get(position);
        String img_url = "http://www.iservicesapps.pt/apps_rest/foto_porto" + c.getImage();

        convertView = LayoutInflater.from(context).inflate(R.layout.category_item, null);
        ImageView iv = (ImageView) convertView.findViewById(R.id.categoryItemImage);
        Picasso.with(context).load(img_url).into(iv);

        return convertView;
    }
}