package com.example.gamega;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

public class GameAdapter  extends BaseAdapter {

    private final Activity context;
    private final String[] scores;
    private final String[] names;

    static class ViewHolder {
        public TextView name;
        public TextView score;
        public ImageView image;
    }

    public GameAdapter(Activity context, String[] names, String[] scores) {

        this.context = context;
        this.scores = scores;
        this.names = names;
    }

    public int getCount() {
        return scores.length;
    }

    public Object getItem(int position) {
        return scores[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        //reuse views
        if(rowView == null){
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.score_adapter, null);

            //Configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = (TextView) rowView.findViewById(R.id.name);
            viewHolder.score = (TextView) rowView.findViewById(R.id.score);
            viewHolder.image =(ImageView) rowView.findViewById(R.id.place);
            rowView.setTag(viewHolder);
        }

        //fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();
        String s = scores[position];
        String n = names[position];
        holder.name.setText(s);
        holder.score.setText(n);

        if(position == 0){
            holder.image.setImageResource(R.drawable.place3);
        } else if(position == 1){
            holder.image.setImageResource(R.drawable.place2);
        } else if(position == 2){
            holder.image.setImageResource(R.drawable.place1);
        } else if(position == 3){
            holder.image.setImageResource(R.drawable.place4);
        } else if(position == 4){
            holder.image.setImageResource(R.drawable.place5);
        } else if(position == 5){
            holder.image.setImageResource(R.drawable.place6);
        } else if(position == 6){
            holder.image.setImageResource(R.drawable.place7);
        } else if(position == 7){
            holder.image.setImageResource(R.drawable.place8);
        } else if(position == 8){
            holder.image.setImageResource(R.drawable.place9);
        } else if(position == 9) {
            holder.image.setImageResource(R.drawable.place10);
        }
        return rowView;
    }
}
