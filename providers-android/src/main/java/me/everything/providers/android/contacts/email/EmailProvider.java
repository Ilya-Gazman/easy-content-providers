package me.everything.providers.android.contacts.email;

import android.content.Context;

import me.everything.providers.core.AbstractProvider;
import me.everything.providers.core.Data;

public class EmailProvider extends AbstractProvider {

    public EmailProvider(Context context) {
        super(context);
    }

    /**
     * Get all calls.
     *
     * @return List of calls
     */
    public Data<Email> getEmails() {
        return getContentTableData(Email.uri, Email.class);
    }

}
