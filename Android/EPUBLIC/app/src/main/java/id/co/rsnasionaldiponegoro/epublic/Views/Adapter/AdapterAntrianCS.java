package id.co.rsnasionaldiponegoro.epublic.Views.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.AntrianCS;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;

/**
 * Created by xsanz on 11/25/2018.
 */

public class AdapterAntrianCS extends RecyclerView.Adapter<AdapterAntrianCS.MyViewHolder> {
    private ArrayList<AntrianCS> arrayList = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public AdapterAntrianCS(Context context, ArrayList<AntrianCS> arrayList){
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
        View rootView = inflater.inflate(R.layout.adapter_antriancs, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AntrianCS antrianCS = arrayList.get(position);

        holder.tv_loket.setText("Loket "+antrianCS.getLoket());
        holder.tv_jmAntrian_a.setText("Jumlah Antrian : "+antrianCS.getJmAntrian());
        holder.tv_antrian_a.setText(""+antrianCS.getAntrian());
        holder.tv_jmLayani_a.setText("Terlayani : "+antrianCS.getJmLayani());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_loket;
        private TextView tv_jmAntrian_a;
        private TextView tv_antrian_a;
        private TextView tv_jmLayani_a;
        private MyViewHolder(View itemView) {
            super(itemView);
            tv_loket = itemView.findViewById(R.id.tv_loket);
            tv_jmAntrian_a = itemView.findViewById(R.id.tv_jmAntrian_a);
            tv_antrian_a = itemView.findViewById(R.id.tv_antrian_a);
            tv_jmLayani_a = itemView.findViewById(R.id.tv_jmLayani_a);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }
}
