package com.ellah.ellahveehotels.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.ellah.ellahveehotels.R;
import com.ellah.ellahveehotels.models.Business;
import com.ellah.ellahveehotels.util.ItemTouchHelperAdapter;
import com.ellah.ellahveehotels.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class FirebaseHotelListAdapter extends FirebaseRecyclerAdapter<Business, FirebaseHotelViewHolder> implements ItemTouchHelperAdapter {
    private Query mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;


    public FirebaseHotelListAdapter(@NonNull FirebaseRecyclerOptions<Business> options,Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }


    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        getRef(position).removeValue();

    }

    @Override
    protected void onBindViewHolder(@NonNull FirebaseHotelViewHolder holder, int position, @NonNull Business model) {
        holder.bindHotel(model);
        holder.mHotelImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(holder);
                }
                return false;
            }
        });

    }

    @NonNull
    @Override
    public FirebaseHotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_list_item_drag, parent, false);
        return new FirebaseHotelViewHolder(view);
    }
}