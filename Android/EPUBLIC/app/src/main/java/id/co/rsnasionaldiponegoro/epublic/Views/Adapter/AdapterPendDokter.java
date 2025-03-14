package id.co.rsnasionaldiponegoro.epublic.Views.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.PendaftaranDokter;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendSend;

/**
 * Created by xsanz on 1/16/2019.
 */

public class AdapterPendDokter  extends RecyclerView.Adapter<AdapterPendDokter.MyViewHolder> {
    private ArrayList<PendaftaranDokter> pendaftaranDokters = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    private Session session;
    private ViewsPendSend viewsPendSend;
    public AdapterPendDokter(Context context, ArrayList<PendaftaranDokter> pendaftaranDokters,ViewsPendSend viewsPendSends){
        this.pendaftaranDokters     = pendaftaranDokters;
        this.viewsPendSend=viewsPendSends;
        this.context         = context;
        this.session= new Session(context);
        try {
            inflater        = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }catch (Exception e){
            Test.look(e.getMessage());
        }
    }

    @Override
    public AdapterPendDokter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.adapter_pend_dokter, parent, false);
        return new AdapterPendDokter.MyViewHolder(rootView);
    }


    @Override
    public void onBindViewHolder(AdapterPendDokter.MyViewHolder holder, int position) {
        PendaftaranDokter pendaftaranDokter = pendaftaranDokters.get(position);
        holder.dokter_nama.setText(pendaftaranDokter.getfMDDOKTERN());
        holder.dokter_time.setText(pendaftaranDokter.getfMJHARI());
        Integer  terpakai=Integer.valueOf(pendaftaranDokter.getTerpakai());
        Integer kuota=Integer.valueOf(pendaftaranDokter.getfMKDLIMIT());
        if(terpakai>=kuota){
            holder.bg_dokter.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
        }else{
            holder.bg_dokter.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        }
    }

    @Override
    public int getItemCount() {
        return pendaftaranDokters.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView dokter_nama;
        private TextView dokter_time;
        private LinearLayout bg_dokter;
        private MyViewHolder(View itemView) {
            super(itemView);
            dokter_nama     = itemView.findViewById(R.id.dokter_nama);
            dokter_time     = itemView.findViewById(R.id.dokter_time);
            bg_dokter     = itemView.findViewById(R.id.bg_dokter);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Integer.valueOf(pendaftaranDokters.get(getAdapterPosition()).getTerpakai())>=Integer.valueOf(pendaftaranDokters.get(getAdapterPosition()).getfMKDLIMIT())){
                        viewsPendSend.onKuotaHabis();
                    }else{
                        viewsPendSend.onProsesSend(pendaftaranDokters.get(getAdapterPosition()).getfMDDOKTERN()
                                ,pendaftaranDokters.get(getAdapterPosition()).getfMJHARI()
                                ,pendaftaranDokters.get(getAdapterPosition()).getShift());
                    }

//                    Intent i = new Intent(context, ActivityPendDokter.class);
//                    session.setSessionString("poliPeriksa",pendaftaranPolis.get(getAdapterPosition()).getfMPKLINIKN());
//                    i.putExtra("poli", pendaftaranPolis.get(getAdapterPosition()).getfMPKLINIKN());
//                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(i);
                }
            });
        }
    }
}
