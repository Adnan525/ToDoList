package com.app.mytodolist;

import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private AdapterTask adapter;

    public RecyclerItemTouchHelper(AdapterTask adapter)
    {
        super(0, ItemTouchHelper.RIGHT);
        this.adapter = adapter;
    }
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getBindingAdapterPosition();
        if(direction == ItemTouchHelper.RIGHT)
//        {
//            AlertDialog.Builder builder = new AlertDialog.Builder(adapter.getContext());
//            builder.setTitle("Confirm task completed");
//            builder.setMessage("Do you want to mark the activity as done?");
//            builder.setPositiveButton("Yes",
//                    new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
                            adapter.changeStatus(position);
                            adapter.notifyDataSetChanged();
//                        }
//                    });
//            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                }
//            });
//            AlertDialog dialog = builder.create();
//            dialog.show();
//        }

    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        ColorDrawable background;

        View itemView = viewHolder.itemView;
        int offset = 20;

//        if(dX>0)
//        {
//            icon = ContextCompat.getDrawable(adapter.getContext(), R.drawable.ic_baseline_library_add_check_24);
//            background = new ColorDrawable(Color.RED);
//        }
        Drawable icon = ContextCompat.getDrawable(adapter.getContext(), R.drawable.ic_baseline_library_add_check_24);
        background = new ColorDrawable(Color.GREEN);

        int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
        int iconTop = itemView.getTop() + (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
        int iconBottom = iconTop + icon.getIntrinsicHeight();

        if (dX > 0) { // Swiping to the right
            int iconLeft = itemView.getLeft() + iconMargin;
            int iconRight = itemView.getLeft() + iconMargin + icon.getIntrinsicWidth();
            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

            background.setBounds(itemView.getLeft(), itemView.getTop(),
                    itemView.getLeft() + ((int) dX) + offset, itemView.getBottom());
        }
        else { // view is unSwiped
            background.setBounds(0, 0, 0, 0);
        }
        background.draw(c);
        icon.draw(c);
    }
}
