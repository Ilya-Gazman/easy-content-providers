package me.everything.providers.android.contacts.accounts;

import android.content.Context;

import me.everything.providers.core.AbstractProvider;
import me.everything.providers.core.Data;

/**
 * Created by Ilya Gazman on 6/21/2016.
 */
public class AccountProvider extends AbstractProvider {

    public AccountProvider(Context context) {
        super(context);
    }

    public Data<Account> geAccounts() {
        return getContentTableData(Account.uri, Account.class);
    }
}
