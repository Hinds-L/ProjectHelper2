package com.bluecirclesquare.projecthelper2;

import android.app.Application;
import com.facebook.stetho.Stetho;

public class PHApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
  }
}
