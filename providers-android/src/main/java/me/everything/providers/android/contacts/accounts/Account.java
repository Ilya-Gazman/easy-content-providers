package me.everything.providers.android.contacts.accounts;

import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.ContactsContract.RawContacts;

import me.everything.providers.core.Entity;
import me.everything.providers.core.FieldMapping;
import me.everything.providers.core.IgnoreMapping;

/**
 * Created by Ilya Gazman on 6/21/2016.
 */
public class Account extends Entity {

    @FieldMapping(columnName = RawContacts.ACCOUNT_NAME, physicalType = FieldMapping.PhysicalType.String)
    public String accountName;

    @FieldMapping(columnName = BaseColumns._ID, physicalType = FieldMapping.PhysicalType.Long)
    public long id;


    @FieldMapping(columnName = RawContacts.ACCOUNT_TYPE, physicalType = FieldMapping.PhysicalType.String)
    public String accountType;

    @FieldMapping(columnName = RawContacts.DIRTY, physicalType = FieldMapping.PhysicalType.String)
    public String dirty;

    @FieldMapping(columnName = RawContacts.SOURCE_ID, physicalType = FieldMapping.PhysicalType.String)
    public String sourceId;

    @FieldMapping(columnName = RawContacts.VERSION, physicalType = FieldMapping.PhysicalType.String)
    public String version;

    /**************************************************************************************************/

    @IgnoreMapping
    public static Uri uri = RawContacts.CONTENT_URI;


}
