package com.example.sasaja.sasaja;

/**
 * Created by vaido on 01/11/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class CustomAdapter extends BaseAdapter implements Filterable {
    ArrayList<Preke> prekes;
    ArrayList<Preke> vaizduojamosPrekes;
    Context context;
    LayoutInflater inflater;

    public CustomAdapter(Context c, ArrayList<Preke> s) {
        // TODO Auto-generated constructor stub
        this.prekes=s;
        this.vaizduojamosPrekes=s;
        this.context=c;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return vaizduojamosPrekes.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        ConstraintLayout clKonteineris;
        TextView tv;
        TextView tv2;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Holder holder = null;

        if (convertView == null) {

            holder = new Holder();
            convertView = inflater.inflate(R.layout.prekes_vaizdas, null);
            holder.clKonteineris = (ConstraintLayout)convertView.findViewById(R.id.clKonteineris);
            holder.tv = (TextView) convertView.findViewById(R.id.textView6);
            holder.tv2 = (TextView) convertView.findViewById(R.id.textView7);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.tv.setText(vaizduojamosPrekes.get(position).pavadinimas);
        holder.tv2.setText(vaizduojamosPrekes.get(position).kaina+"");

        holder.clKonteineris.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                Toast.makeText(context, vaizduojamosPrekes.get(position).pavadinimas, Toast.LENGTH_SHORT).show();
                String pav = vaizduojamosPrekes.get(position).pavadinimas;
                double kain = vaizduojamosPrekes.get(position).kaina;
                if(context instanceof PrekiuPaieska) {
                    ((PrekiuPaieska) context).baigtiSuReiksme(pav, kain);
                }
            }
        });

        return convertView;
    }


    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                vaizduojamosPrekes = (ArrayList<Preke>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<Preke> FilteredArrList = new ArrayList<Preke>();

                if (prekes == null) {
                    prekes = new ArrayList<Preke>(vaizduojamosPrekes); // saves the original data in preke
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the preke(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = prekes.size();
                    results.values = prekes;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < prekes.size(); i++) {
                        String data = prekes.get(i).pavadinimas;
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(new Preke(prekes.get(i).pavadinimas, prekes.get(i).kaina));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }

}