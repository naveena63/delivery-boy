package com.iprismtech.delivery_boy.network.listener;

import com.iprismtech.delivery_boy.dao.CountryDao;


import java.util.ArrayList;

public interface PassCountryIDs {

    void passCountry(ArrayList<CountryDao> countryDaos);
}
