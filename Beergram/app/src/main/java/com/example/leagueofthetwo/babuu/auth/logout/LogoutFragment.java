package com.example.leagueofthetwo.babuu.auth.logout;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextDirectionHeuristic;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leagueofthetwo.babuu.R;
import com.example.leagueofthetwo.babuu.utils.BabuuProgressDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogoutFragment extends Fragment implements LogoutContract.View {

    private Button btnLogout;
    private TextView tvUserNames;

    private LogoutContract.Presenter mPresenter;
    private BabuuProgressDialog mDialog;
    private Context mContext;

    public LogoutFragment() {
        // Required empty public constructor
    }

    public static LogoutFragment newInstance() {
        return new LogoutFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_logout, container, false);

        btnLogout = (Button) mRootView.findViewById(R.id.btn_logout);
        tvUserNames = (TextView) mRootView.findViewById(R.id.tv_user_names);

        btnLogout.setOnClickListener(v -> mPresenter.onBtnLogout());

        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mContext = context;
    }

    @Override
    public void onResume() {
        super.onResume();

        mPresenter.start();
    }

    @Override
    public void setPresenter(LogoutContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setUserNames(String firsName, String lastName) {
        tvUserNames.setText(firsName + " " + lastName);
    }

    @Override
    public void setDialog(BabuuProgressDialog dialog) {
        mDialog = dialog;
    }

    @Override
    public void showDialogLoading() {
        mDialog.showProgress(getString(R.string.loading_text));
    }

    @Override
    public void showDialogLoggingOut() {
        mDialog.showProgress(getString(R.string.loading_logging_out_text));
    }

    @Override
    public void dismissDialog() {
        mDialog.dismissProgress();
    }

    @Override
    public void showLoginActivity() {
        getActivity().finish();
    }

    @Override
    public void notifyUserLogout() {
        Toast.makeText(mContext, "User logged out", Toast.LENGTH_SHORT).show();
    }
}
