package com.mendoza.touchims.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.RequestViewHolder>{
    @Override
    public RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RequestViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    class RequestViewHolder extends RecyclerView.ViewHolder{

        public RequestViewHolder(View itemView) {
            super(itemView);
        }
    }
}
