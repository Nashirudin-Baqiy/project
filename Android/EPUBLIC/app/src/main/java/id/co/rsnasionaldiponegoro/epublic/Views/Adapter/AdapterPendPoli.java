package id.co.rsnasionaldiponegoro.epublic.Views.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


import id.co.rsnasionaldiponegoro.epublic.Model.Models.PendaftaranPoli;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.Pendaftaran.ActivityPendDokter;

/**
 * Created by xsanz on 1/16/2019.
 */

public class AdapterPendPoli extends RecyclerView.Adapter<AdapterPendPoli.MyViewHolder> {
    private ArrayList<PendaftaranPoli> pendaftaranPolis = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    private Session session;

    public AdapterPendPoli(Context context, ArrayList<PendaftaranPoli> pendaftaranPolis){
        this.pendaftaranPolis     = pendaftaranPolis;
        this.context         = context;
        this.session= new Session(context);
        try {
            inflater        = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }catch (Exception e){
            Test.look(e.getMessage());
        }
    }

    @Override
    public AdapterPendPoli.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.adapter_pend_poli, parent, false);
        return new AdapterPendPoli.MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(AdapterPendPoli.MyViewHolder holder, int position) {
        PendaftaranPoli pendaftaranPoli = pendaftaranPolis.get(position);
        holder.pend_poli.setText(pendaftaranPoli.getfMPKLINIKN());
    }

    @Override
    public int getItemCount() {
        return pendaftaranPolis.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView pend_poli;
        private MyViewHolder(View itemView) {
            super(itemView);
            pend_poli     = itemView.findViewById(R.id.pend_poli);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ActivityPendDokter.class);
                    session.setSessionString("poliPeriksa",pendaftaranPolis.get(getAdapterPosition()).getfMPKLINIKN());
                    i.putExtra("poli", pendaftaranPolis.get(getAdapterPosition()).getfMPKLINIKN());
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            });
        }
    }

}
