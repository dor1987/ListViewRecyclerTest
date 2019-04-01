package a1door.listviewrecyclertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static a1door.listviewrecyclertest.MainActivity.EXTRA_CREATOR;
import static a1door.listviewrecyclertest.MainActivity.EXTRA_DOWNLOADS;
import static a1door.listviewrecyclertest.MainActivity.EXTRA_LIKES;
import static a1door.listviewrecyclertest.MainActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String creatorName = intent.getStringExtra(EXTRA_CREATOR);
        int likeCount = intent.getIntExtra(EXTRA_LIKES,0);
        int downloadCount = intent.getIntExtra(EXTRA_DOWNLOADS,0);

        ImageView imageView = findViewById(R.id.textview_detail_image);
        TextView textView1 = findViewById(R.id.textview_detail_creator);
        TextView textView2 = findViewById(R.id.textview_detail_likes);
        TextView textView3 = findViewById(R.id.textview_detail_downloads);


        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);

        textView1.setText(creatorName);
        textView2.setText("Likes: " + likeCount);
        textView3.setText("Downloads: " + downloadCount);


    }
}
