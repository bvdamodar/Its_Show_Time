package com.example.daredevil07.ItsShowTime;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Splash_ActivityTest {
    @Rule
    public ActivityTestRule<Splash_Activity> mActivityTestRule = new ActivityTestRule<>(Splash_Activity.class);

    @Test
    public void splash_ActivityTest() {
    }
}
