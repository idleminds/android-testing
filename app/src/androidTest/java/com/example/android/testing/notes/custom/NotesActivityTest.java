package com.example.android.testing.notes.custom;


import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import com.example.android.testing.notes.notes.NotesActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class NotesActivityTest
{
    @Test
    public void testRotate() throws Exception
    {
        Context context = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(context, NotesActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.setOrientationLeft();
        device.unfreezeRotation();
        Thread.sleep(3000);

        device.setOrientationNatural();
        device.unfreezeRotation();
        Thread.sleep(3000);

        device.setOrientationRight();
        device.unfreezeRotation();
        Thread.sleep(3000);

        device.setOrientationNatural();
        Thread.sleep(3000);
    }
}
