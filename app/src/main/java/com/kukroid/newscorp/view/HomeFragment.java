package com.kukroid.newscorp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kukroid.newscorp.Interface.NewsClickListener;
import com.kukroid.newscorp.R;
import com.kukroid.newscorp.Util.Util;
import com.kukroid.newscorp.adapter.NewsGridAdapter;
import com.kukroid.newscorp.Interface.HomeFragmentContract;
import com.kukroid.newscorp.model.News;
import com.kukroid.newscorp.presenter.HomeFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kukresa on 12/26/2018.
 */

public class HomeFragment extends Fragment implements NewsClickListener, HomeFragmentContract {
    RecyclerView recyclerView;
    NewsGridAdapter gridAdapter;
    SwipeRefreshLayout swiperefresh;
    ArrayList<News> newsList;
    LinearLayout linearLayout;
    View view;
    private HomeFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment,container,false);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        presenter = new HomeFragmentPresenter(this);
        initView();
    }

    private void initView() {

        newsList = new ArrayList<>();

        linearLayout = view.findViewById(R.id.linearLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        swiperefresh = view.findViewById(R.id.swiperefresh);
        swiperefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimaryDark));
        gridAdapter = new NewsGridAdapter(getContext(),newsList,this);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(gridAdapter);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                presenter.swipeToRefresh();

            }
        });

        presenter.getNewsData();

    }

    @Override
    public void onItemClick(View view, int position) {

        Fragment fragment = new DetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("NEWS",newsList.get(position));
        fragment.setArguments(bundle);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container,fragment,"DetailsFragment");
        transaction.addToBackStack("DetailsFragment");
        transaction.commit();
    }


    @Override
    public void refreshOnSwipe() {

        newsList.clear();
        gridAdapter.notifyDataSetChanged();
    }

    @Override
    public void setDataToRecyclerView(List<News> body) {
        newsList.addAll(body);
        gridAdapter.notifyDataSetChanged();
        swiperefresh.setRefreshing(false);
    }

    @Override
    public void onFail() {
        Util.showSnackBar(linearLayout,R.string.error);
        swiperefresh.setRefreshing(false);
    }

    @Override
    public void onOffline() {
        Util.showSnackBar(linearLayout,R.string.offline);
        swiperefresh.setRefreshing(false);
    }

    @Override
    public boolean checkNetworkAvailability() {

        if(Util.isNetworkAvailable(getContext())){
            return true;
        }
        return false;
    }

}
