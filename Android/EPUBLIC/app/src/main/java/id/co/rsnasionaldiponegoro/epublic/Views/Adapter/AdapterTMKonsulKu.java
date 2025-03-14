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
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.Telemedicine.ActivityTMDetailBooking;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.Telemedicine.ActivityTMKonsulDetail;

/**
 * Created by xsanz on 25-Jun-2020.
 */

public class AdapterTMKonsulKu extends RecyclerView.Adapter<AdapterTMKonsulKu.MyViewHolder> {
    private ArrayList<PendaftaranBooking> arrayList = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public AdapterTMKonsulKu(Context context, ArrayList<PendaftaranBooking> arrayList){
        this.arrayList     = arrayList;
        this.context         = context;
        try {
            inflater        = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }catch (Exception e){
            Test.look(e.getMessage());
        }
    }

    @Override
    public AdapterTMKonsulKu.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.adapter_telemedicine_konsulku, parent, false);
        return new AdapterTMKonsulKu.MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(AdapterTMKonsulKu.MyViewHolder holder, int position) {
        PendaftaranBooking pendaftaranBooking = arrayList.get(position);
        String tgl[]=pendaftaranBooking.getfAPTGLAPPOITMENT().split(" ");
        holder.adapter_tgl.setText(""+tgl[0]);
        holder.adapter_noantri.setText("Kode Antri :"+pendaftaranBooking.getFAPANTRIDOKTER());
        holder.adapter_keluhan.setText(""+pendaftaranBooking.getFAPKELUHAN());

        if(pendaftaranBooking.getFAPSTATUSPROSES().equals("0")){
            holder.adapter_status.setText("Status: Dalam Antrian");
        }else  if(pendaftaranBooking.getFAPSTATUSPROSES().equals("1")){
            holder.adapter_status.setText("Status: Siap Konsultasi");
        }else{
            holder.adapter_status.setText("Status: Sudah Konsultasi");
        }


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView adapter_noantri;
        private TextView adapter_keluhan;
        private TextView adapter_tgl;
        private TextView adapter_status;
        private MyViewHolder(View itemView) {
            super(itemView);
            adapter_noantri = itemView.findViewById(R.id.adapter_noantri);
            adapter_keluhan = itemView.findViewById(R.id.adapter_keluhan);
            adapter_tgl = itemView.findViewById(R.id.adapter_tgl);
            adapter_status = itemView.findViewById(R.id.adapter_status);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(arrayList.get(getAdapterPosition()).getFAPSTATUSPROSES().equals("2")){
                        Intent intent = new Intent(context,ActivityTMKonsulDetail.class);
                        intent.putExtra("notrans",""+arrayList.get(getAdapterPosition()).getfAPNOTRANSAKSI());
                        context.startActivity(intent);
                    }else{
                        Intent intent = new Intent(context,ActivityTMDetailBooking.class);
                        intent.putExtra("kdDokter",""+arrayList.get(getAdapterPosition()).getfAPDOKTERID());
                        intent.putExtra("notrans",""+arrayList.get(getAdapterPosition()).getfAPNOTRANSAKSI());
                        context.startActivity(intent);
                    }

                }
            });
        }
    }
}
