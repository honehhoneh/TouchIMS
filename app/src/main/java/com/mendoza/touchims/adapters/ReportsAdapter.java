package com.mendoza.touchims.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mendoza.touchims.R;
import com.mendoza.touchims.models.Report;

import java.util.List;

public class ReportsAdapter extends RecyclerView.Adapter<ReportsAdapter.ReportViewHolder> {


    private List<Report> reports;
    private Context context;

    public ReportsAdapter(List<Report> reports, Context context) {
        this.reports = reports;
        this.context = context;
    }

    @Override
    public ReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_reports, parent, false);
        ReportViewHolder holder = new ReportViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ReportViewHolder holder, int position) {
        holder.cardView.setTag(position);
        Report current = reports.get(position);
        holder.repSubject.setText(current.getSubj_no());
        holder.repDate.setText(current.getDate());
        holder.repRoom.setText(current.getRoom());


//        RoomRequest current = roomRequests.get(position);
//        holder.reqTitle.setText(current.getClassActivities());
//        holder.reqDate.setText(current.getDateOfNotif());
//        if (current.getAdminRemark().toUpperCase().equals("DENIED")) {
//            holder.icon.setImageResource(R.drawable.icon_cancel);
//        } else if (current.getAdminRemark().toUpperCase().equals("APPROVED")) {
//            holder.icon.setImageResource(R.drawable.icon_approved);
//        } else {
//            holder.icon.setImageResource(R.drawable.icon_waiting);
//        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ReportViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView icon, counterRemark;
        TextView repSubject, repDate, repTime, repRoom;

        public ReportViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.report_cardView);
            icon = itemView.findViewById(R.id.reportIcon);
            counterRemark = itemView.findViewById(R.id.editCounterRemark);
            repSubject = itemView.findViewById(R.id.tvSubject);
            repDate = itemView.findViewById(R.id.tvDate);
            repRoom = itemView.findViewById(R.id.tvRoom);
        }
    }
}
