package com.rahimlis.templates.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.AnyRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rahimlis.templates.App;
import com.rahimlis.templates.models.Model;
import com.rahimlis.templates.vendor.Factory;
import com.rahimlis.templates.vendor.JsonParser;

import java.io.Serializable;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public App getApp() {
        return (App) getApplication();
    }

    protected void bindActivityStarter(View view, final Class<?> activityName, final Model... models) {
        bindOnClickListener(view, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (models.length == 0)
                    startActivity(Factory.getIntent(BaseActivity.this, activityName));
                else {
                    startActivity(Factory.getIntent()
                            .from(BaseActivity.this)
                            .to(activityName)
                            .with(models[0])
                            .create()
                    );
                }
            }
        });
    }

    protected void bindOnClickListener(View view, View.OnClickListener listener) {
        if (view == null)
            return;

        view.setOnClickListener(listener);
    }

    protected void bindText(TextView textView, @Nullable String text) {
        if (textView == null)
            return;

        textView.setText(text);
    }


    protected void bindText(@IdRes int textViewId, @Nullable String text) {
        bindText((TextView) findViewById(textViewId), text);
    }

    protected void bindImage(ImageView imageView, @NonNull String url) {
        if (imageView == null)
            return;
        Glide.with(this).load(url).into(imageView);

    }

    protected void bindImage(ImageView imageView, @AnyRes int contentResource) {
        if (imageView == null || contentResource == 0)
            return;
        Glide.with(this).load(contentResource).into(imageView);
    }

    protected void bindImage(@IdRes int imageResource, @AnyRes int contentResource) {
        bindImage((ImageView) findViewById(imageResource), contentResource);
    }


    protected void bindImage(@IdRes int imageResource, @NonNull String contentResource) {
        bindImage((ImageView) findViewById(imageResource), contentResource);
    }

    public <T extends Model> T getModel(Class<? extends Model> className) {
        Intent intent = getIntent();
        String modelJson = intent.getStringExtra(className.getName());
        return (T) JsonParser.parse(modelJson, className);
    }


    public <T extends Serializable> T getModel(String className) {
        Intent intent = getIntent();
        return (T) intent.getSerializableExtra(className);
    }

    public int getColour(int resid) {
        return ContextCompat.getColor(this, resid);
    }

    protected void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }



    protected void showHomeButton() {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void removeToolbarText() {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    protected void setToolbarText(String text) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(text);
    }


}
