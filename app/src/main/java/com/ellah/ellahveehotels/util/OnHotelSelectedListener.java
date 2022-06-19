package com.ellah.ellahveehotels.util;

import com.ellah.ellahveehotels.models.Business;

import java.util.List;

public interface OnHotelSelectedListener {
    public void onHotelSelected(Integer position, List<Business> hotels);
}
