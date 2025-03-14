package id.co.rsnasionaldiponegoro.epublic.Views.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.TopUpHistory;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;

/**
 * Created by xsanz on 24-Jun-2020.
 */

public class AdapterTelemedicineTopUpHistory   extends RecyclerView.Adapter<AdapterTelemedicineTopUpHistory.MyViewHolder>{
    private ArrayList<TopUpHistory> topUpHistories = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public AdapterTelemedicineTopUpHistory(Context context, ArrayList<TopUpHistory> topUpHistories){
        this.topUpHistories     = topUpHistories;
        this.context         = context;
        try {
            inflater        = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }catch (Exception e){
            Test.look(e.getMessage());
        }
    }


    @Override
    public AdapterTelemedicineTopUpHistory.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.adapter_telemedicine_topuphistory, parent, false);
        return new AdapterTelemedicineTopUpHistory.MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(AdapterTelemedicineTopUpHistory.MyViewHolder holder, int position) {
        TopUpHistory topUpHistory = topUpHistories.get(position);
//
        if(topUpHistory.getsTATUS().equals("1")){
            holder.adapter_status.setText("Status: Terbayar");
            holder.adapter_judul.setText("TopUp +"+topUpHistory.getnOMINAL()+" Points");
            holder.layout_topup.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }else if(topUpHistory.getsTATUS().equals("2")){
            holder.adapter_status.setText("Status: Pengurangan");
            holder.adapter_judul.setText("TopUp +"+topUpHistory.getnOMINAL()+" Points");
            holder.layout_topup.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
        }else if(topUpHistory.getsTATUS().equals("3")){
            holder.adapter_judul.setText("TopUp +"+topUpHistory.getnOMINAL()+" Points");
            holder.adapter_status.setText("Status: Belum Terbayar");
            holder.layout_topup.setBackgroundColor(context.getResources().getColor(R.color.localBackground));
        }else{
            holder.adapter_status.setText("Status: Di Batalkan");
            holder.adapter_judul.setText("TopUp "+topUpHistory.getnOMINAL()+" Points");
            holder.layout_topup.setBackgroundColor(context.getResources().getColor(R.color.localBackground));
        }

        holder.adapter_ket.setText(""+topUpHistory.getkETERANGAN());
        holder.adapter_tanggal.setText(""+topUpHistory.getuPDATERS());

    }


    @Override
    public int getItemCount() {
        return topUpHistories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView adapter_judul;
        private TextView adapter_ket;
        private TextView adapter_tanggal;
        private TextView adapter_status;
        private RelativeLayout layout_topup;

        private MyViewHolder(View itemView) {
            super(itemView);
            adapter_judul    = itemView.findViewById(R.id.adapter_judul);
            adapter_ket    = itemView.findViewById(R.id.adapter_ket);
            adapter_tanggal     = itemView.findViewById(R.id.adapter_tanggal);
            adapter_status     = itemView.findViewById(R.id.adapter_status);
            layout_topup     = itemView.findViewById(R.id.layout_topup);

        }
    }
}
