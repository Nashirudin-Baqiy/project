package id.co.rsnasionaldiponegoro.epublic.Views.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.PendaftaranDokter;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendSend;

/**
 * Created by xsanz on 24-Jun-2020.
 */

public class AdapterTMPendDokter extends RecyclerView.Adapter<AdapterTMPendDokter.MyViewHolder> {
    private ArrayList<PendaftaranDokter> pendaftaranDokters = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    private Session session;
    private ViewsPendSend viewsPendSend;
    public AdapterTMPendDokter(Context context, ArrayList<PendaftaranDokter> pendaftaranDokters,ViewsPendSend viewsPendSends){
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
    public AdapterTMPendDokter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.adapter_pend_dokter, parent, false);
        return new AdapterTMPendDokter.MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(AdapterTMPendDokter.MyViewHolder holder, int position) {
        PendaftaranDokter pendaftaranDokter = pendaftaranDokters.get(position);
        holder.dokter_nama.setText(pendaftaranDokter.getfMDDOKTERN());
        Integer  terpakai=Integer.valueOf(pendaftaranDokter.getTerpakai());
        Integer kuota=Integer.valueOf(pendaftaranDokter.getfMKDLIMIT());
        Boolean Liburs=null;
        Liburs=pendaftaranDokter.getLibur();
        if (Liburs==null){
            Liburs=false;
        }
        if(Liburs){
            holder.dokter_time.setText(pendaftaranDokter.getfMJHARI()+" (CUTI)");
            holder.bg_dokter.setBackgroundColor(context.getResources().getColor(R.color.colorY));
        }else{
            if(terpakai>=kuota){
                holder.dokter_time.setText(pendaftaranDokter.getfMJHARI()+" (KUOTA HABIS)");
                holder.bg_dokter.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
            }else{
                holder.dokter_time.setText(pendaftaranDokter.getfMJHARI());
                holder.bg_dokter.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            }
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
//                    viewsPendSend.onProsesSend(pendaftaranDokters.get(getAdapterPosition()).getfMDDOKTERN()
//                            ,pendaftaranDokters.get(getAdapterPosition()).getfMJHARI()
//                            ,pendaftaranDokters.get(getAdapterPosition()).getShift());
//                    if(pendaftaranDokters.get(getAdapterPosition()).getLibur()){
                        Boolean Liburs=null;
                        Liburs=pendaftaranDokters.get(getAdapterPosition()).getLibur();
                        if (Liburs==null){
                            Liburs=false;
                        }
                        if(Liburs){
                        Toast.makeText(context,"Dokter Sedang Cuti",Toast.LENGTH_LONG).show();
                    }else{
                        if(Integer.valueOf(pendaftaranDokters.get(getAdapterPosition()).getTerpakai())>=Integer.valueOf(pendaftaranDokters.get(getAdapterPosition()).getfMKDLIMIT())){
                            viewsPendSend.onKuotaHabis();
                        }else{
                            viewsPendSend.onProsesSend(pendaftaranDokters.get(getAdapterPosition()).getfMDDOKTERN()
                                    ,pendaftaranDokters.get(getAdapterPosition()).getfMJHARI()
                                    ,pendaftaranDokters.get(getAdapterPosition()).getShift());
                        }
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
