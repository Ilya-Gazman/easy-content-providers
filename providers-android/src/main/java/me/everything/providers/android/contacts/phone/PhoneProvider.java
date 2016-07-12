package me.everything.providers.android.contacts.phone;

import android.content.Context;

import me.everything.providers.core.AbstractProvider;
import me.everything.providers.core.Data;


public class PhoneProvider extends AbstractProvider {

    public PhoneProvider(Context context) {
        super(context);
    }

    /**
     * Get all calls.
     *
     * @return List of calls
     */
    public Data<Phone> getPhones() {
        return getContentTableData(Phone.uri, Phone.class);
    }

}
