package me.everything.providers.android.contacts.phone;

import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds;

import me.everything.providers.android.contacts.BaseEntity;
import me.everything.providers.core.FieldMapping;
import me.everything.providers.core.IgnoreMapping;

/**
 * Created by Ilya Gazman on 6/20/2016.
 */
public class Phone extends BaseEntity {

    @IgnoreMapping
    public static Uri uri = CommonDataKinds.Phone.CONTENT_URI;

    @FieldMapping(columnName = "data4", physicalType = FieldMapping.PhysicalType.String)
    public String normalizedNumber;

    @FieldMapping(columnName = CommonDataKinds.Phone.NUMBER, physicalType = FieldMapping.PhysicalType.String)
    public String number;
}
