package com.mendoza.touchims.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mendoza.touchims.R;
import com.mendoza.touchims.fragments.ChangeRoomRequestFragment;
import com.mendoza.touchims.fragments.RequestsFragment;
import com.mendoza.touchims.models.RoomRequest;
import com.mendoza.touchims.views.RequestDetailsActivity;

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
        holder.cardView.setTag(position);

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
        CardView cardView;
        ImageView icon;
        TextView reqTitle, reqDate;

        public RequestViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.request_cardView);
            icon = itemView.findViewById(R.id.requestIcon);
            reqTitle = itemView.findViewById(R.id.requestTitle);
            reqDate = itemView.findViewById(R.id.requestDate);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = (int) cardView.getTag();
                    Intent i = new Intent(context, RequestDetailsActivity.class);
                    i.putExtra("department", roomRequests.get(position).getDepartment());
                    i.putExtra("dateOfNotif", roomRequests.get(position).getDateOfNotif());
                    i.putExtra("subject", roomRequests.get(position).getSubject());
                    i.putExtra("sch_time", roomRequests.get(position).getSch_time());
                    i.putExtra("sch_days", roomRequests.get(position).getSch_days());
                    i.putExtra("room", roomRequests.get(position).getRoom());
                    i.putExtra("classActivities", roomRequests.get(position).getClassActivities());
                    i.putExtra("actDate", roomRequests.get(position).getActDate());
                    i.putExtra("actTime", roomRequests.get(position).getActTime());
                    i.putExtra("actVenue", roomRequests.get(position).getActVenue());
                    i.putExtra("instructor", roomRequests.get(position).getInstructor());
                    i.putExtra("adminRemark", roomRequests.get(position).getAdminRemark());
                    i.putExtra("adminName", roomRequests.get(position).getAdminName());
                    context.startActivity(i);

                }
            });
        }
    }
}
