package com.bluecirclesquare.projecthelper2.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.bluecirclesquare.projecthelper2.R;
import com.bluecirclesquare.projecthelper2.view.InvoicesFragment;
import com.bluecirclesquare.projecthelper2.view.JobsFragment;
import com.bluecirclesquare.projecthelper2.view.QuotesFragment;

/**
* Main Activity class that populates the main view after sign in.
*/

public class MainActivity extends AppCompatActivity implements
    OnNavigationItemSelectedListener {

  private TextView fragmentTitle;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);
//    fragmentTitle = findViewById(R.id.fragment_title);
    BottomNavigationView navigation = findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(this);
    navigation.setSelectedItemId(R.id.navigation_quotes);


    /**
    *Allows user to write emails
     */
//    FloatingActionButton emailButton = findViewById(R.id.create_email);
//    emailButton.setOnClickListener(new View.OnClickListener(){

//    @Override
//    public void onClick(View view) {
//      Snackbar.make(view, "Email", Snackbar.LENGTH_LONG).setAction("Action", null).show();
//    }
//                                   }
//    );
  }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      boolean handled = true;
      Fragment fragment = null;
  switch (item.getItemId()) {
        case R.id.navigation_quotes:
          fragment = new QuotesFragment();
          break;
        case R.id.navigation_invoice:
          fragment = new InvoicesFragment();
          break;
        case R.id.navigation_job:
          fragment = new JobsFragment();
          break;
        default:
          handled = false;

      }
      if (handled) {

        //fragmentTitle.setText(item.getTitle());

        if (fragment != null) {
          getSupportFragmentManager().beginTransaction()
              .replace(R.id.fragment_container, fragment)
              .commit();

        }

      }
      return handled;
    }
    }

