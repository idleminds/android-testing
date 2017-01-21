package com.example.android.testing.notes.custom;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.testing.notes.notes.NotesActivity;

import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class TestNotesActivity
{
    @Test
    public void test()
    {
        Context context = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(context, NotesActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
