package com.example.eighthlessonnew.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.eighthlessonnew.R;
import com.example.eighthlessonnew.model.CardSource;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    private CardSource dataSource;

    private MyOnClickListener listener;

    public NoteAdapter(CardSource dataSource) {
        this.dataSource = dataSource;
    }



    public void setOnMyOnClickListener(MyOnClickListener listener){
        this.listener = listener;
    }

    public void setData(CardSource dataSource){ this.dataSource = dataSource; }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(dataSource.getCardData(position).getTitle());
        holder.description.setText(dataSource.getCardData(position).getDescription());
        holder.imageView.setImageResource(dataSource.getCardData(position).getPicture());
        holder.like.setChecked(dataSource.getCardData(position).isLike());

    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


         TextView title;
         TextView description;
         ImageView imageView;
         CheckBox like;


        public MyViewHolder( View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            imageView = itemView.findViewById(R.id.imageView);
            like = itemView.findViewById(R.id.like);

            imageView.setOnClickListener(new View.OnClickListener() {   // обработчик нажатий
                @Override
                public void onClick(View v) {
                    listener.onMyClick(v,getAdapterPosition());  // возвращает текущую позицию

                }
            });
        }
    }
}
