package id.co.rsnasionaldiponegoro.epublic.Views.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.UnitStaff;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.ActivityStaffDokterList;

/**
 * Created by xsanz on 10/13/2018.
 */

public class AdapterUnitStaff extends RecyclerView.Adapter<AdapterUnitStaff.MyViewHolder> {
    private ArrayList<UnitStaff> unitStaffs = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public AdapterUnitStaff(Context context, ArrayList<UnitStaff> unitStaffs){
        this.unitStaffs = unitStaffs;
        this.context         = context;
        try {
            inflater        = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }catch (Exception e){
            Test.look(e.getMessage());
        }
    }

    @Override
    public AdapterUnitStaff.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.adapter_poliklinik, parent, false);
        return new AdapterUnitStaff.MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(AdapterUnitStaff.MyViewHolder holder, int position) {
        UnitStaff unitStaff = unitStaffs.get(position);
        holder.tv_unitstaff.setText(unitStaff.getUnitStaff());
    }

    @Override
    public int getItemCount() {
        return unitStaffs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_unitstaff;
        private MyViewHolder(View itemView) {
            super(itemView);
            tv_unitstaff     = itemView.findViewById(R.id.poliklinik_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                if(unitStaffs.get(getAdapterPosition()).getUnitStaff().equals("DOKTER")){
                    Intent i = new Intent(context, ActivityStaffDokterList.class);
                    i.putExtra("id", unitStaffs.get(getAdapterPosition()).getIdStaff());
                    i.putExtra("title", unitStaffs.get(getAdapterPosition()).getUnitStaff());
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }else{
                    Intent i = new Intent(context, ActivityStaffDokterList.class);
                    i.putExtra("id", unitStaffs.get(getAdapterPosition()).getIdStaff());
                    i.putExtra("title", unitStaffs.get(getAdapterPosition()).getUnitStaff());
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
                }
            });
        }
    }
}
