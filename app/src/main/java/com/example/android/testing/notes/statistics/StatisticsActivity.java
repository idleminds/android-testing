/*
 * Copyright 2015, The Android Open Source Project
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

package com.example.android.testing.notes.statistics;

import com.example.android.testing.notes.Injection;
import com.example.android.testing.notes.R;
import com.example.android.testing.notes.data.Note;
import com.example.android.testing.notes.data.NotesRepository;
import com.example.android.testing.notes.notes.NotesFragment;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * Show statistics for notes. At this point this is just a dummy implementation.
 */
public class StatisticsActivity extends AppCompatActivity implements NotesRepository.LoadNotesCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_statistics);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle(R.string.statistics_title);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        NotesRepository repository = Injection.provideNotesRepository();
        repository.getNotes(this);
    }

    @Override
    public void onNotesLoaded(List<Note> notes) {
        if (notes.size() == 0)
            return;

        findViewById(R.id.no_statistics).setVisibility(View.GONE);
        findViewById(R.id.statistics_layout).setVisibility(View.VISIBLE);
        TextView tv = (TextView) findViewById(R.id.total_notes);
        tv.setText(getString(R.string.total_notes, notes.size()));

        int totalWords = 0;

        for (Note note : notes)
        {
            if (!TextUtils.isEmpty(note.getTitle()))
                totalWords += note.getTitle().split("\\s+").length;

            if (!TextUtils.isEmpty(note.getDescription()))
                totalWords += note.getDescription().split("\\s+").length;
        }

        tv = (TextView) findViewById(R.id.total_words);
        tv.setText(getString(R.string.total_words, totalWords));

        float averageWords = (float) totalWords / (float) notes.size();

        tv = (TextView) findViewById(R.id.average_words);
        tv.setText(getString(R.string.average_words, averageWords));
    }
}
