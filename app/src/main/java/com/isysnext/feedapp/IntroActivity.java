package com.isysnext.feedapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.WindowManager;
import com.github.paolorotolo.appintro.AppIntro;

/**
 * Created by Anuved on 29/08/18.
 */
public class IntroActivity extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        Bundle bundle = new Bundle();
        bundle.putInt("code", 0);
        addSlide(getFragment(bundle));
        bundle = new Bundle();
        bundle.putInt("code", 1);
        addSlide(getFragment(bundle));
        bundle = new Bundle();
        bundle.putInt("code", 2);
        addSlide(getFragment(bundle));
        setBarColor(getResources().getColor(android.R.color.transparent));
        showSkipButton(false);
        setProgressButtonEnabled(true);
        setDoneText("Done");
    }

    private Fragment getFragment(Bundle bundle) {
        IntroFragment introFragment = new IntroFragment();
        introFragment.setArguments(bundle);
        return introFragment;
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }


}
