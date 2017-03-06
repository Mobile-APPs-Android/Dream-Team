package com.example.dreamteam.beergram.newsfeed;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.Toast;

import com.example.dreamteam.beergram.R;
import com.example.dreamteam.beergram.search.SearchActivity;


public class NewsFeedFragment extends Fragment implements NewsfeedContract.View {
    private final String SEARCH_VALUE = "searchValue";

    private View root;
    private NewsfeedContract.Presenter presenter;
    private Context context;

    public NewsFeedFragment() {
    }

    public static NewsFeedFragment newInstance() {
        return new NewsFeedFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.root = inflater.inflate(R.layout.fragment_news_feed, container, false);

        ListView lvPosts = (ListView) this.root.findViewById(R.id.lv_posts);

        Button btnSearch = (Button) this.root.findViewById(R.id.btn_search);
        Button btnPost = (Button) this.root.findViewById(R.id.btn_post_location);
        EditText etSearch = (EditText) this.root.findViewById(R.id.et_search);

        btnSearch.setOnClickListener((view) -> {
            String searchValue = etSearch.getText().toString();
            this.showSearchActivity(searchValue);
        });

        btnPost.setOnClickListener((view) ->{
            this.presenter.postLocationToFriends();
        });


//        ArrayAdapter<Book> booksAdapter =
//                new ArrayAdapter<Book>(root.getContext(), -1, books) {
//                    @NonNull
//                    @Override
//                    public View getView(int position, View convertView, ViewGroup parent) {
//                        View view = convertView;
//                        if (view == null) {
//                            LayoutInflater inflater = LayoutInflater.from(this.getContext());
//                            view = inflater.inflate(R.layout.item_book, parent, false);
//                        }
//
//                        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
//
//                        String title = this.getItem(position).getTitle();
//                        tvTitle.setText(title);
//
//                        return view;
//                    }
//                };
//
//        lvBooks.setAdapter(booksAdapter);




        return this.root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }

    @Override
    public void setPresenter(NewsfeedContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void notifyPostShared() {
        Toast.makeText(this.context, "Location shared to friends.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSearchActivity(String searchValue) {
        Intent intent = new Intent(this.context, SearchActivity.class);

        intent.putExtra(SEARCH_VALUE, searchValue);

        startActivity(intent);
    }
}
