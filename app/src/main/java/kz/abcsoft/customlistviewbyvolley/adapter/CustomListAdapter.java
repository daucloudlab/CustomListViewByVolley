package kz.abcsoft.customlistviewbyvolley.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import kz.abcsoft.customlistviewbyvolley.R;
import kz.abcsoft.customlistviewbyvolley.app.AppController;
import kz.abcsoft.customlistviewbyvolley.model.Movie;

public class CustomListAdapter extends BaseAdapter{
    private Activity activity ;
    private LayoutInflater inflater ;
    private List<Movie> movieItems ;

    ImageLoader imageLoader = AppController.getInstance().getImageLoader() ;

    public CustomListAdapter(Activity activity, List<Movie>movieItems){
        this.activity = activity ;
        this.movieItems = movieItems ;
    }

    @Override
    public int getCount() {
        return movieItems.size() ;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int i) {
        return movieItems.get(i);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        if(convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null) ;

        if(imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader() ;

        NetworkImageView thumbNail = (NetworkImageView)convertView.findViewById(R.id.thumbnail) ;

        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        TextView genre = (TextView) convertView.findViewById(R.id.genre);
        TextView year = (TextView) convertView.findViewById(R.id.releaseYear);

        Movie m = movieItems.get(position);

        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
        title.setText(m.getTitle());
        rating.setText("Rating: " + String.valueOf(m.getRating()));

        String genreStr = "";
        for (String str : m.getGenre()) {
            genreStr += str + ", ";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
                genreStr.length() - 2) : genreStr;
        genre.setText(genreStr);

        year.setText(String.valueOf(m.getYear()));

        return convertView;
    }
}
