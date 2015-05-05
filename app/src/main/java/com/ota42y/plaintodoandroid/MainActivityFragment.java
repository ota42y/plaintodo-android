package com.ota42y.plaintodoandroid;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {
    Button button;
    DropboxAPI<AndroidAuthSession> mApi;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        button = (Button)v.findViewById(R.id.button);
        button.setOnClickListener(this);

        // We create a new AuthSession so that we can use the Dropbox API.
        AndroidAuthSession session = buildSession();
        mApi = new DropboxAPI<AndroidAuthSession>(session);

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == button) {
            Downloader d = new  Downloader(mApi);
            d.execute();
        }
    }

    private void loadAuth(AndroidAuthSession session) {
        session.setOAuth2AccessToken(getString(R.string.dropbox_access_token));
    }

    private AndroidAuthSession buildSession() {
        AppKeyPair appKeyPair = new AppKeyPair(getString(R.string.dropbox_app_key), getString(R.string.dropbox_app_secret));

        AndroidAuthSession session = new AndroidAuthSession(appKeyPair);
        loadAuth(session);
        return session;
    }
}
