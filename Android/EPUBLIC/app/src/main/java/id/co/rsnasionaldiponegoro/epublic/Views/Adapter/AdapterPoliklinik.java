package id.co.rsnasionaldiponegoro.epublic.Views.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.JadwalDokter;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.ActivityStaffDokterList;

/**
 * Created by xsanz on 9/26/2018.
 */

public class AdapterPoliklinik extends RecyclerView.Adapter<AdapterPoliklinik.MyViewHolder> {
    private ArrayList<JadwalDokter> poliDokters = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public AdapterPoliklinik(Context context, ArrayList<JadwalDokter> poliDokters){
        this.poliDokters     = poliDokters;
        this.context         = context;
        try {
            inflater        = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }catch (Exception e){
            Test.look(e.getMessage());
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.adapter_poliklinik, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        JadwalDokter poliDokter = poliDokters.get(position);
        holder.poliklinik.setText(poliDokter.getPoliklinik());
    }

    @Override
    public int getItemCount() {
        return poliDokters.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView poliklinik;
        private MyViewHolder(View itemView) {
            super(itemView);
            poliklinik     = itemView.findViewById(R.id.poliklinik_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ActivityStaffDokterList.class);
                    i.putExtra("kdPoli", poliDokters.get(getAdapterPosition()).getId());
                    i.putExtra("title", poliDokters.get(getAdapterPosition()).getPoliklinik());
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    Intent i = new Intent(context, ActivityJadwalDokterJadwal.class);
//                    i.putExtra("id", poliDokters.get(getAdapterPosition()).getId());
//                    i.putExtra("poliklinik", poliDokters.get(getAdapterPosition()).getPoliklinik());
//                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            });
        }
    }
}
