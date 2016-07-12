package me.everything.providers.android.contacts.address;

import android.net.Uri;
import android.provider.ContactsContract;

import me.everything.providers.android.contacts.BaseEntity;
import me.everything.providers.core.FieldMapping;
import me.everything.providers.core.IgnoreMapping;

/**
 * Created by Ilya Gazman on 6/20/2016.
 */
public class Address  extends BaseEntity {

    @IgnoreMapping
    public static Uri uri = ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI;

    @FieldMapping(columnName = ContactsContract.CommonDataKinds.StructuredPostal.CITY, physicalType = FieldMapping.PhysicalType.String)
    public String city;

    @FieldMapping(columnName = ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY, physicalType = FieldMapping.PhysicalType.String)
    public String country;

    @FieldMapping(columnName = ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS, physicalType = FieldMapping.PhysicalType.String)
    public String formattedAddress;

    @FieldMapping(columnName = ContactsContract.CommonDataKinds.StructuredPostal.NEIGHBORHOOD, physicalType = FieldMapping.PhysicalType.String)
    public String neighborhood;

    @FieldMapping(columnName = ContactsContract.CommonDataKinds.StructuredPostal.POBOX, physicalType = FieldMapping.PhysicalType.String)
    public String po_box;

    @FieldMapping(columnName = ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE, physicalType = FieldMapping.PhysicalType.String)
    public String postcode;

    @FieldMapping(columnName = ContactsContract.CommonDataKinds.StructuredPostal.REGION, physicalType = FieldMapping.PhysicalType.String)
    public String region;

    @FieldMapping(columnName = ContactsContract.CommonDataKinds.StructuredPostal.STREET, physicalType = FieldMapping.PhysicalType.String)
    public String street;

}
