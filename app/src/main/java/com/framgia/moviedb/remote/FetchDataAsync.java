package com.framgia.moviedb.remote;

import android.os.AsyncTask;
import com.framgia.moviedb.untils.Constant;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by nttungg on 3/1/18.
 */

public class FetchDataAsync extends AsyncTask<String, String, String> {

  private MovieDataSource.Callback<String> mCallback;

  public FetchDataAsync(MovieDataSource.Callback<String> callback) {
    mCallback = callback;
  }

  @Override
  protected void onPreExecute() {
    mCallback.onStartLoading();
  }

  @Override
  protected String doInBackground(String... strings) {
    String urlStr = strings[0];
    String result = null;
    try {
      URL url = new URL(urlStr);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setConnectTimeout(Constant.CONNECT_TIME_OUT);
      connection.setReadTimeout(Constant.READ_TIME_OUT);
      connection.setRequestMethod(Constant.REQUEST_METHOD);
      InputStream inputStream = connection.getInputStream();
      result = readStream(inputStream);
    } catch (MalformedURLException e) {
      mCallback.onGetFailure(e.getMessage());
    } catch (IOException e) {
      mCallback.onGetFailure(e.getMessage());
    }
    return result;
  }

  private String readStream(InputStream inputStream) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    StringBuilder builder = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      builder.append(line).append("\n");
    }
    inputStream.close();
    return builder.toString();
  }

  @Override
  protected void onPostExecute(String result) {
    mCallback.onGetSuccess(result);
    mCallback.onComplete();
  }
}
