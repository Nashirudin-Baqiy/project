package id.co.rsnasionaldiponegoro.epublic.Views.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.KritikSaran;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;

/**
 * Created by xsanz on 9/27/2018.
 */

public class AdapterKritikSaran  extends RecyclerView.Adapter<AdapterKritikSaran.MyViewHolder> {
    private ArrayList<KritikSaran> kritikSarans = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public AdapterKritikSaran(Context context, ArrayList<KritikSaran> kritikSarans) {
        this.kritikSarans = kritikSarans;
        this.context = context;
        try {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } catch (Exception e) {
            Test.look("Error On: ", e.getMessage());
        }
    }

    @Override
    public AdapterKritikSaran.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.adapter_kritiksaran, parent, false);
        return new AdapterKritikSaran.MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(AdapterKritikSaran.MyViewHolder holder, int position) {
        KritikSaran kritikSaran = kritikSarans.get(position);
        holder.info_judul.setText(kritikSaran.getNama());
        holder.info_tgl.setText(kritikSaran.getJudulKeluhan() +" ("+kritikSaran.getTgl()+")");
        holder.info_desc.setText(kritikSaran.getDeskripsi());
        holder.info_jawaban.setText("Jawaban Admin:\n"+kritikSaran.getJawabanKeluhan());
        holder.info_img.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return kritikSarans.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView info_judul;
        private TextView info_tgl;
        private TextView info_desc;
        private TextView info_jawaban;
        private ImageView info_img;

        private MyViewHolder(View itemView) {
            super(itemView);
            info_judul = itemView.findViewById(R.id.info_judul);
            info_desc = itemView.findViewById(R.id.info_desc);
            info_tgl = itemView.findViewById(R.id.info_tgl);
            info_img = itemView.findViewById(R.id.info_img);
            info_jawaban = itemView.findViewById(R.id.info_jawaban);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
