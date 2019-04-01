package a1door.listviewrecyclertest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static a1door.listviewrecyclertest.MainActivity.EXTRA_CREATOR;
import static a1door.listviewrecyclertest.MainActivity.EXTRA_DOWNLOADS;
import static a1door.listviewrecyclertest.MainActivity.EXTRA_LIKES;
import static a1door.listviewrecyclertest.MainActivity.EXTRA_URL;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Item> mDataset;
    private Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView textView1;
        TextView textView2;
        ImageView imageView;

        RelativeLayout parentLayout;

        public MyViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.image_view_list_item);
            textView1 = v.findViewById(R.id.text_view_list_item1);
            textView2 = v.findViewById(R.id.text_view_list_item2);

            parentLayout = v.findViewById(R.id.list_item);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)


    public MyAdapter(ArrayList<Item> mDataset, Context mContext) {
        this.mDataset = mDataset;
        this.mContext = mContext;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
       // ...
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
       Item currentItem = mDataset.get(position);

        holder.textView1.setText("Creator: "+currentItem.getmCreator());
        holder.textView2.setText("Likes: "+currentItem.getmLikes());
        Picasso.get().load(currentItem.getmImageUrl()).fit().centerInside().into(holder.imageView);

        //Add click listener here to parent layout
        holder.parentLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
               // Toast.makeText(mContext, mDataset.get, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext,DetailActivity.class);
                intent.putExtra(EXTRA_URL,mDataset.get(position).getmImageUrl());
                intent.putExtra(EXTRA_CREATOR,mDataset.get(position).getmCreator());
                intent.putExtra(EXTRA_LIKES,mDataset.get(position).getmLikes());
                intent.putExtra(EXTRA_DOWNLOADS,mDataset.get(position).getmDownloads());

                mContext.startActivity(intent);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}