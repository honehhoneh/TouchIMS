package com.mendoza.touchims.adapters;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mendoza.touchims.R;
import com.mendoza.touchims.models.RoomRequest;

import java.util.List;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.RequestViewHolder> {

    private List<RoomRequest> roomRequests;
    private Context context;

    public RequestsAdapter(Context context, List<RoomRequest> roomRequests) {
        this.roomRequests = roomRequests;
        this.context = context;
    }

    @Override
    public RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_requests, parent, false);
        RequestViewHolder holder = new RequestViewHolder(view);
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(RequestViewHolder holder, int position) {
        RoomRequest current = roomRequests.get(position);
        holder.reqTitle.setText(current.getClassActivities());
        holder.reqDate.setText(current.getDateOfNotif());
        if(current.getAdminRemark().toUpperCase().equals("DENIED")){
            holder.icon.setImageResource(R.drawable.icon_cancel);
        }else if(current.getAdminRemark().toUpperCase().equals("APPROVED")){
            holder.icon.setImageResource(R.drawable.icon_approved);
        }else{
            holder.icon.setImageResource(R.drawable.icon_waiting);
        }
    }


    @Override
    public int getItemCount() {
        return roomRequests.size();
    }

    class RequestViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView reqTitle, reqDate;

        public RequestViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.requestIcon);
            reqTitle = itemView.findViewById(R.id.requestTitle);
            reqDate = itemView.findViewById(R.id.requestDate);
        }
    }
}
