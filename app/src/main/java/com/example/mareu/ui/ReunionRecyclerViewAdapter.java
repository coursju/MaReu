package com.example.mareu.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.events.DeleteReunionEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReunionRecyclerViewAdapter extends RecyclerView.Adapter<ReunionRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<ArrayList<String>>  mList;

    public ReunionRecyclerViewAdapter(ArrayList<ArrayList<String>>  list){
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reunion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<String> reunionContent = mList.get(position);
        switch(reunionContent.get(2)){
            case "salle 1":
                holder.color.setImageResource(R.drawable.ic_color_1); break;
            case "salle 2":
                holder.color.setImageResource(R.drawable.ic_color_2); break;
            case "salle 3":
                holder.color.setImageResource(R.drawable.ic_color_3); break;
            case "salle 4":
                holder.color.setImageResource(R.drawable.ic_color_4); break;
            case "salle 5":
                holder.color.setImageResource(R.drawable.ic_color_5); break;
            case "salle 6":
                holder.color.setImageResource(R.drawable.ic_color_6); break;
            case "salle 7":
                holder.color.setImageResource(R.drawable.ic_color_7); break;
            case "salle 8":
                holder.color.setImageResource(R.drawable.ic_color_8); break;
            case "salle 9":
                holder.color.setImageResource(R.drawable.ic_color_9); break;
            case "salle 10":
                holder.color.setImageResource(R.drawable.ic_color_10); break;
            default:
                holder.color.setImageResource(R.drawable.ic_color_1); break;
        }
        holder.title.setText(reunionContent.get(0)+"-"+reunionContent.get(1)+"-"+reunionContent.get(2));
        holder.content.setText(reunionContent.get(3));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteReunionEvent(
                        reunionContent.get(4),
                        reunionContent.get(2),
                        reunionContent.get(1),
                        reunionContent.get(0)
                ));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_reunion_color)
        public ImageView color;
        @BindView(R.id.item_list_title)
        public TextView title;
        @BindView(R.id.item_list_content)
        public TextView content;
        @BindView(R.id.item_reunion_delete_button)
        public ImageButton delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
