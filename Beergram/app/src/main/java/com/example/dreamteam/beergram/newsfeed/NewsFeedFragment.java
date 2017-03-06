package com.example.dreamteam.beergram.newsfeed;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import android.widget.Toast;

import com.example.dreamteam.beergram.R;



public class NewsFeedFragment extends Fragment implements NewsfeedContract.View {
    private View rootView;
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
        this.rootView = inflater.inflate(R.layout.fragment_news_feed, container, false);

        ListView lvPosts = (ListView) this.rootView.findViewById(R.id.lv_posts);

        Button btn_post = (Button) this.rootView.findViewById(R.id.btn_post_location);

        btn_post.setOnClickListener((view) ->{
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




        return this.rootView;
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

    public void notifyPostShared() {
        Toast.makeText(this.context, "Location shared to friends.", Toast.LENGTH_SHORT).show();
    }
}
