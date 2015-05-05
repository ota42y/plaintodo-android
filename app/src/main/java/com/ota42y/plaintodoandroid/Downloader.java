package com.ota42y.plaintodoandroid;

import android.os.AsyncTask;
import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.exception.DropboxException;

import java.io.ByteArrayOutputStream;

/**
 * Created by ota42y on 2015/05/04.
 */
public class Downloader extends AsyncTask<Void, Long, Boolean> {
    private DropboxAPI<?> mApi;

    public Downloader(DropboxAPI<?> api) {
        mApi = api;
    }

    @Override
    protected Boolean doInBackground(Void... params) {

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        try {
            mApi.getFile("test.txt", null, buf, null);
            System.out.println(buf.toString());
        } catch (DropboxException e) {
            e.printStackTrace();
        }

        return null;
    }
}
