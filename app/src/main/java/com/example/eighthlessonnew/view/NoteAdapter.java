package com.example.eighthlessonnew.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eighthlessonnew.R;
import com.example.eighthlessonnew.model.CardData;
import com.example.eighthlessonnew.model.CardSource;

import java.text.SimpleDateFormat;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    private CardSource dataSource;
    private MyOnClickListener listener;
    private Fragment fragment;

    private int menuContextClickPosition;

    public int getMenuContextClickPosition() {
        return menuContextClickPosition;
    }



    public NoteAdapter(CardSource dataSource,Fragment fragment) {
        this.dataSource = dataSource;
        this.fragment = fragment;
    }



    public void setOnMyOnClickListener(MyOnClickListener listener){
        this.listener = listener;
    }




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
       // holder.date.setDate(dataSource.getCardData(position));

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
         TextView date;

        public MyViewHolder( View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            imageView = itemView.findViewById(R.id.imageView);
            like = itemView.findViewById(R.id.like);
            date = itemView.findViewById(R.id.date);

            fragment.registerForContextMenu(imageView);

            imageView.setOnClickListener(new View.OnClickListener() {   // обработчик нажатий
                @Override
                public void onClick(View v) {
                    listener.onMyClick(v,getAdapterPosition());  // возвращает текущую позицию

                }
            });
   //-----------------------------------------------------------------------------------
            imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    menuContextClickPosition = getAdapterPosition();
                    imageView.showContextMenu(200,200);       // меню выпадает при нажатии на картинку
                    return true;
                }
            });
   //--------------------------------------------------------------------------------------
        }
        public void setDate (CardData cardData){
            title.setText(cardData.getTitle());
           // date.setText(cardData.getDate().toString());
            description.setText(cardData.getDescription());
            like.setChecked(cardData.isLike());
            imageView.setImageResource(cardData.getPicture());
            date.setText(new SimpleDateFormat("dd-mm-yy").format(cardData.getDate()));
        }
    }
}
