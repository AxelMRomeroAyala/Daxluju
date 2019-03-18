package com.yacarex.daxluju;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AldeanImageListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ImageListModel> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aldean_image_list);

        recyclerView = findViewById(R.id.recycledVista);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        listItems = new ArrayList<>();
        ImageListModel imageList1 = new ImageListModel(
                "https://static.qobuz.com/images/covers/4a/ek/ak7g3owxtek4a_300.jpg",
                "Polkadot Stingray",
                "ヒミツ(Secret)");
        listItems.add(imageList1);

        ImageListModel imageList2 = new ImageListModel(
                "https://c-ash.smule.com/sf/s79/arr/0f/55/10f4a108-fa72-4982-8701-e403000becd0_256.jpg",
                "Yorushika",
                "Hitchcock");
        listItems.add(imageList2);

        ImageListModel imageList3 = new ImageListModel(
                "https://c-ash.smule.com/sf/s47/arr/8d/b1/f76869d3-4c5f-44d3-b17f-4b76176f2355_256.jpg",
                "REOL",
                "ちるちる");
        listItems.add(imageList3);

        ImageListModel imageList4 = new ImageListModel(
                "https://images-na.ssl-images-amazon.com/images/I/414QC5JZBDL.jpg",
                "Do As Infinity",
                "Break of Down");
        listItems.add(imageList4);

        ImageListModel imageList5 = new ImageListModel(
                "http://3.bp.blogspot.com/-MC3Aek8U7go/UZ4mLNJZMGI/AAAAAAAAAFk/QITr1muC17M/s320/crossing-field-50203e65d1062.jpg",
                "LiSA",
                "Crossing Field");
        listItems.add(imageList5);

        ImageListModel imageList6 = new ImageListModel(
                "https://is4-ssl.mzstatic.com/image/thumb/Music128/v4/e3/49/9a/e3499afc-9874-a317-1dda-d0f8d4279824/886446920214.jpg/268x0w.jpg",
                "Man with a Mission",
                "My Hero/Find you");
        listItems.add(imageList6);

        ImageListModel imageList7 = new ImageListModel(
                "https://images-na.ssl-images-amazon.com/images/I/51w8YN2Ny9L._SX355_.jpg",
                "Konomi Suzuki",
                "This Game");
        listItems.add(imageList7);

        ImageListModel imageList8 = new ImageListModel(
                "https://c-sf.smule.com/sf/s80/arr/41/88/981412ac-320f-4f4d-b4b1-cce3535c2c0b.jpg",
                "Zutto Mayonaka de ii no ni",
                "Byoushin wo kamu");
        listItems.add(imageList8);

        ImageListModel imageList9 = new ImageListModel(
                "https://c-sf.smule.com/sf/s80/arr/d9/04/8ff7ec5c-2fad-4751-85f7-774fafde5a96.jpg",
                "Mami Kawada",
                "No Buts");
        listItems.add(imageList9);

        ImageListModel imageList10 = new ImageListModel(
                "https://c-sf.smule.com/sf/s79/arr/9a/da/4dac1925-7df2-454d-a08c-edff63d83bd1.jpg",
                "NIKIIE",
                "Morning Glory");
        listItems.add(imageList10);

        ImageListModel imageList11 = new ImageListModel(
                "https://static.myfigurecollection.net/pics/figure/big/85580.jpg",
                "Kotoko",
                "Light my Fire");
        listItems.add(imageList11);

        ImageListModel imageList12 = new ImageListModel(
                "https://i0.wp.com/otakuost.net/wp-content/uploads/vbfngfvm.jpg?fit=416%2C398&ssl=1",
                "Mika Nakashima",
                "Kiss of Death");
        listItems.add(imageList12);

        adapter = new ElAdapter(listItems, getBaseContext());
        recyclerView.setAdapter(adapter);

    }
}
