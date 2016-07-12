package me.everything.providers.android.contacts.address;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import me.everything.providers.core.AbstractProvider;
import me.everything.providers.core.Data;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class AddressProvider extends AbstractProvider {

    public AddressProvider(Context context) {
        super(context);
    }

    /**
     * Get all calls.
     *
     * @return List of calls
     */
    public Data<Address> getAddresses() {
        return getContentTableData(Address.uri, Address.class);
    }

}
