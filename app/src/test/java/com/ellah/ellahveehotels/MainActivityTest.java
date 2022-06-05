package com.ellah.ellahveehotels;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.ellah.ellahveehotels.ui.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
    private MainActivity activity;

    @Before
    public void setup() {
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(activity);
    }

    @Test
    public void shouldHaveLocationEditText() throws Exception {
        assertNotNull(activity.findViewById(R.id.locationEditText));
    }

    @Test
    public void shouldHaveFindHotelButton() throws Exception {
        assertNotNull(activity.findViewById(R.id.findHotel));
    }

}

