package com.example.android.testing.notes.custom;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.view.Gravity;

import com.example.android.testing.notes.R;
import com.example.android.testing.notes.addnote.AddNoteActivity;
import com.example.android.testing.notes.notes.NotesActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class AddNoteTest
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

    @Test
    public void testDrawer() throws Exception
    {
        Context context = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(context, NotesActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

        ViewInteraction drawer = onView(withId(R.id.drawer_layout));
        drawer.check(matches(isClosed(Gravity.LEFT)));
        drawer.perform(open());
        drawer.check(matches(isOpen()));

        onView(withChild(withText("Statistics"))).perform(click());
        onView(withText("No statistics")).check(matches(isDisplayed()));
    }
}
