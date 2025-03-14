package id.co.rsnasionaldiponegoro.epublic.Views.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.PendaftaranBooking;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.Pendaftaran.ActivityDetailPendaftaran;

/**
 * Created by xsanz on 1/15/2019.
 */

public class AdapterBooking extends RecyclerView.Adapter<AdapterBooking.MyViewHolder> {
    private ArrayList<PendaftaranBooking> arrayList = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public AdapterBooking(Context context, ArrayList<PendaftaranBooking> arrayList){
        this.arrayList     = arrayList;
        this.context         = context;
        try {
            inflater        = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }catch (Exception e){
            Test.look(e.getMessage());
        }
    }

    @Override
    public AdapterBooking.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.adapter_booking, parent, false);
        return new AdapterBooking.MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(AdapterBooking.MyViewHolder holder, int position) {
        PendaftaranBooking pendaftaranBooking = arrayList.get(position);
        String tgl[]=pendaftaranBooking.getfAPTGLAPPOITMENT().split(" ");
        holder.info_tgl.setText(""+tgl[0]);
        holder.info_notrans.setText(""+pendaftaranBooking.getfAPNOTRANSAKSI());
        holder.info_poli.setText(""+pendaftaranBooking.getfMPKLINIKN());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView info_tgl;
        private TextView info_notrans;
        private TextView info_poli;
        private MyViewHolder(View itemView) {
            super(itemView);
            info_tgl = itemView.findViewById(R.id.info_tgl);
            info_notrans = itemView.findViewById(R.id.info_notrans);
            info_poli = itemView.findViewById(R.id.info_poli);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,ActivityDetailPendaftaran.class);
                    intent.putExtra("notrans",""+arrayList.get(getAdapterPosition()).getfAPNOTRANSAKSI());
                    context.startActivity(intent);
                }
            });
        }
    }
}
