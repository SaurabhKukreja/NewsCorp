package com.kukroid.newscorp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kukroid.newscorp.model.News;
import com.kukroid.newscorp.R;
import com.kukroid.newscorp.Util.Util;

/**
 * Created by kukresa on 12/26/2018.
 */

public class DetailsFragment  extends Fragment {

    ImageView newsImage ;
    TextView newsDesription;
    News news;
    LinearLayout linearLayout;
    View view;
    RequestOptions options;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.details_fragment, container, false);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        linearLayout = view.findViewById(R.id.linearLayout);
        options = new RequestOptions()
                .centerCrop()
                .error(R.mipmap.ic_launcher_round);

        Bundle bundle = getArguments();
        if(bundle !=null) {
            news = (News) bundle.getSerializable("NEWS");
            initView();
        }else {
            Util.showSnackBar(linearLayout,R.string.error);
        }

    }

    private void initView() {

        try {
            newsImage = view.findViewById(R.id.newsImage);
            newsDesription = view.findViewById(R.id.newsDescription);

            Glide.with(getContext())
                    .load(news.getUrl())
                    .apply(options)
                    .into(newsImage);

            newsDesription.setText(news.getTitle());
        }catch(Throwable t){
            Util.showSnackBar(linearLayout,R.string.error);
        }
    }

}