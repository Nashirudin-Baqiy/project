package id.co.rsnasionaldiponegoro.epublic.Views.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.Keluarga;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsKeluarga;

/**
 * Created by xsanz on 2/1/2020.
 */

public class AdapterKeluarga extends RecyclerView.Adapter<AdapterKeluarga.MyViewHolder>  {
    private ArrayList<Keluarga> keluargas = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    private ViewsKeluarga viewsKeluargas;

    public AdapterKeluarga(Context context, ArrayList<Keluarga> keluargas,ViewsKeluarga viewsKeluarga){
        this.viewsKeluargas=viewsKeluarga;
        this.keluargas     = keluargas;
        this.context         = context;
        try {
            inflater        = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }catch (Exception e){
            Test.look("Error On: ",e.getMessage());
        }
    }

    @Override
    public AdapterKeluarga.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.adapter_keluarga, parent, false);
        return new AdapterKeluarga.MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(AdapterKeluarga.MyViewHolder holder, int position) {
        Keluarga keluarga = keluargas.get(position);
        holder.family_name.setText(keluarga.getNamaKeluarga());
        holder.family_nocm.setText(keluarga.getNocm());
    }

    @Override
    public int getItemCount() {
        return keluargas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView family_name;
        private TextView family_nocm;

        private MyViewHolder(View itemView) {
            super(itemView);
            family_name    = itemView.findViewById(R.id.family_name);
            family_nocm     = itemView.findViewById(R.id.family_nocm);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewsKeluargas.onHapusKeluarga(keluargas.get(getAdapterPosition()));
                }
            });
        }
    }
}
