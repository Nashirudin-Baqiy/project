package id.co.rsnasionaldiponegoro.epublic.Views.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.JadwalDokter;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.Pendaftaran.ActivityPendLogin;

/**
 * Created by xsanz on 9/26/2018.
 */

public class AdapterDokter  extends RecyclerView.Adapter<AdapterDokter.MyViewHolder> {
    private ArrayList<JadwalDokter> jadwalDokters = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public AdapterDokter(Context context, ArrayList<JadwalDokter> jadwalDokters){
        this.jadwalDokters     = jadwalDokters;
        this.context         = context;
        try {
            inflater        = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }catch (Exception e){
            Test.look(e.getMessage());
        }
    }

    @Override
    public AdapterDokter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.adapter_dokter, parent, false);
        return new AdapterDokter.MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(AdapterDokter.MyViewHolder holder, int position) {
        JadwalDokter jadwalDokter = jadwalDokters.get(position);
        holder.dokter_nama.setText(jadwalDokter.getDokter());
        holder.dokter_hari.setText(jadwalDokter.getHari());
        holder.dokter_jam.setText(jadwalDokter.getJam());
    }

    @Override
    public int getItemCount() {
        return jadwalDokters.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView dokter_photo;
        private TextView dokter_nama;
        private TextView dokter_hari;
        private TextView dokter_jam;
        private Button button_daftar;

        private MyViewHolder(View itemView) {
            super(itemView);
            dokter_photo    = itemView.findViewById(R.id.dokter_photo);
            button_daftar    = itemView.findViewById(R.id.button_daftar);
            dokter_nama     = itemView.findViewById(R.id.dokter_nama);
            dokter_hari     = itemView.findViewById(R.id.dokter_hari);
            dokter_jam      = itemView.findViewById(R.id.dokter_jam);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent i = new Intent(context, DetailPegawaiActivity.class);
//                    i.putExtra("data", jadwalDokters.get(getAdapterPosition()));
//                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(i);
                }
            });

            button_daftar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    context.startActivity(new Intent(context, ActivityPendaftaranWeb.class));
                    context.startActivity(new Intent(context, ActivityPendLogin.class));
                }
            });
        }
    }
}
