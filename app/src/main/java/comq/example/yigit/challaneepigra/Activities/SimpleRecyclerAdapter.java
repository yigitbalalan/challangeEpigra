package comq.example.yigit.challaneepigra.Activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import comq.example.yigit.challaneepigra.R;
import comq.example.yigit.challaneepigra.data.model.photosResult;

/**
 * Created by yigit on 25.01.2018.
 */
public  class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHold> {
    private int siraLayout;
    private ArrayList<photosResult> photos;
    private Context context;
    private OnClickListener onImageClickListener;







    public static class ViewHold extends RecyclerView.ViewHolder{
        TextView body;
        ImageView imageView;
        ImageView imageView1;
        LinearLayout layout1;




        public ViewHold(View v) {
            super(v);

            body = (TextView) v.findViewById(R.id.tvCaption);
            imageView = (ImageView) v.findViewById(R.id.ivMainImage);
            layout1 = (LinearLayout)v.findViewById(R.id.linearone);


        }
    }

    public SimpleRecyclerAdapter(ArrayList<photosResult> photos,int siraLayout,OnClickListener onImageClickListener ){
        this.photos = photos;
        this.siraLayout = siraLayout;
        this.onImageClickListener = onImageClickListener;
    }

    @Override
    public ViewHold onCreateViewHolder(ViewGroup v, int viewType){

        View view = LayoutInflater.from(v.getContext()).inflate(siraLayout,v,false);
        return new ViewHold(view);

    }

    @Override
    public void onBindViewHolder(final ViewHold holder, final int position){

        holder.body.setText(photos.get(position).getTitle());

        Picasso.with(holder.itemView.getContext()).load(photos.get(position).getUrl()).into(holder.imageView);


        holder.layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onImageClickListener.onImageClick(photos.get(position).getUrl());
            }
        });









    }






    @Override
    public int getItemCount(){
        return photos.size();
    }



}
