package me.everything.providers.android.contacts.email;

import android.net.Uri;
import android.provider.ContactsContract;

import me.everything.providers.android.contacts.BaseEntity;
import me.everything.providers.core.FieldMapping;
import me.everything.providers.core.IgnoreMapping;

/**
 * Created by Ilya Gazman on 6/20/2016.
 */
public class Email extends BaseEntity {

    @IgnoreMapping
    public static Uri uri = ContactsContract.CommonDataKinds.Email.CONTENT_URI;

    @FieldMapping(columnName = ContactsContract.CommonDataKinds.Email.DISPLAY_NAME, physicalType = FieldMapping.PhysicalType.String)
    public String displayName;

    @FieldMapping(columnName = ContactsContract.CommonDataKinds.Email.ADDRESS, physicalType = FieldMapping.PhysicalType.String)
    public String email;
}
