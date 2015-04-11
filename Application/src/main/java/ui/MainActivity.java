/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ui;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.android.actionbarcompat.styled.R;

/**
 * This sample shows you how to use ActionBarCompat with a customized theme. It utilizes a split
 * action bar when running on a device with a narrow display, and show three tabs.
 *
 * This Activity extends from {@link ActionBarActivity}, which provides all of the function
 * necessary to display a compatible Action Bar on devices running Android v2.1+.
 *
 * The interesting bits of this sample start in the theme files
 * ('res/values/styles.xml' and 'res/values-v14</styles.xml').
 *
 * Many of the drawables used in this sample were generated with the
 * 'Android Action Bar Style Generator': http://jgilfelt.github.io/android-actionbarstylegenerator
 */
public class MainActivity extends TabActivity implements TabHost.TabContentFactory {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_mainactivity);
        TabHost tabHost = getTabHost();
        tabHost.addTab(tabHost.newTabSpec("chat").setIndicator("Chat").setContent(new Intent(this, Chathistory.class)));
        tabHost.addTab(tabHost.newTabSpec("contact").setIndicator("Contact").setContent(new Intent(this, Contact.class)));
        tabHost.addTab(tabHost.newTabSpec("run").setIndicator("Running").setContent(new Intent(this, Mainpage.class)));
        tabHost.addTab(tabHost.newTabSpec("recommend").setIndicator("Recommend").setContent(new Intent(this,Recommend.class)));
        tabHost.addTab(tabHost.newTabSpec("history").setIndicator("History").setContent(new Intent(this, History.class)));
        setupUI();
    }
    @Override
    public View createTabContent(String tag) {
        TextView tv = new TextView(this);
        tv.setTextColor(Color.BLACK);
        tv.setTextSize(20);
//        if (tag.equals("chat")) {
//            tv.setText(R.string.chat);
//        } else if (tag.equals("contact")) {
//            tv.setText(R.string.contact);
//        } else if (tag.equals("recommend")) {
//            tv.setText(R.string.recommend);
//        } else if (tag.equals("history")) {
//            tv.setText(R.string.history);
//        }
        return tv;
    }
    private void setupUI() {
        RadioButton rbFirst = (RadioButton) findViewById(R.id.first);
        RadioButton rbSecond = (RadioButton) findViewById(R.id.second);
        RadioButton rbThird = (RadioButton) findViewById(R.id.third);
        RadioButton rbFourth = (RadioButton) findViewById(R.id.fourth);
        RadioButton rbFifth = (RadioButton) findViewById(R.id.fifth);

        rbFirst.setButtonDrawable(R.drawable.chat);
        rbSecond.setButtonDrawable(R.drawable.contact);
        rbThird.setButtonDrawable(R.drawable.run);
        rbFourth.setButtonDrawable(R.drawable.recommend);
        rbFifth.setButtonDrawable(R.drawable.user);

        RadioGroup rg = (RadioGroup) findViewById(R.id.states);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, final int checkedId) {
                switch (checkedId) {
                    case R.id.first:
                        getTabHost().setCurrentTab(0);
                        break;
                    case R.id.second:
                        getTabHost().setCurrentTab(1);
                        break;
                    case R.id.third:
                        getTabHost().setCurrentTab(2);
                        break;
                    case R.id.fourth:
                        getTabHost().setCurrentTab(3);
                        break;
                    case R.id.fifth:
                        getTabHost().setCurrentTab(4);
                        break;
                }
            }
        });
    }
}


