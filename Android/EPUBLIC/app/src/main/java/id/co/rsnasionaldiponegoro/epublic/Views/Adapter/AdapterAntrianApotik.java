package id.co.rsnasionaldiponegoro.epublic.Views.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.AntrianApotik;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;

/**
 * Created by xsanz on 11/25/2018.
 */

public class AdapterAntrianApotik extends RecyclerView.Adapter<AdapterAntrianApotik.MyViewHolder> {
    private ArrayList<AntrianApotik> arrayList = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public AdapterAntrianApotik(Context context, ArrayList<AntrianApotik> arrayList){
        this.arrayList     = arrayList;
        this.context         = context;
        try {
            inflater        = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }catch (Exception e){
            Test.look(e.getMessage());
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.adapter_antrian_apotik, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AntrianApotik antrianApotik = arrayList.get(position);
        holder.loket_antrian_loketAntrian.setText(antrianApotik.getLoket());
        holder.loket_antrian_jmAntrian.setText("Jumlah Antrian "+antrianApotik.getJmAntrian());
        holder.loket_antrian_antrian.setText("Antrian "+antrianApotik.getAntrian());
        holder.loket_antrian_jmLayani.setText("Jumlah Terlayani "+antrianApotik.getJmLayani());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView loket_antrian_loketAntrian;
        private TextView loket_antrian_jmAntrian;
        private TextView loket_antrian_antrian;
        private TextView loket_antrian_jmLayani;
        private MyViewHolder(View itemView) {
            super(itemView);
            loket_antrian_loketAntrian     = itemView.findViewById(R.id.loket_antrian_loketAntrian);
            loket_antrian_jmAntrian     = itemView.findViewById(R.id.loket_antrian_jmAntrian);
            loket_antrian_antrian     = itemView.findViewById(R.id.loket_antrian_antrian);
            loket_antrian_jmLayani     = itemView.findViewById(R.id.loket_antrian_jmLayani);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }
}
