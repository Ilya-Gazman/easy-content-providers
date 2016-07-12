package me.everything.providers.android.contacts;

import android.provider.BaseColumns;
import android.provider.ContactsContract;

import me.everything.providers.core.Entity;
import me.everything.providers.core.FieldMapping;

/**
 * Created by Ilya Gazman on 7/3/2016.
 */
public class BaseEntity extends Entity {
    @FieldMapping(columnName = BaseColumns._ID, physicalType = FieldMapping.PhysicalType.Long)
    public long id;

    @FieldMapping(columnName = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, physicalType = FieldMapping.PhysicalType.String)
    public String displayName;

    @FieldMapping(columnName = ContactsContract.CommonDataKinds.Phone.PHOTO_FILE_ID, physicalType = FieldMapping.PhysicalType.String)
    public String photoFileId;

    @FieldMapping(columnName = ContactsContract.CommonDataKinds.Phone.PHOTO_ID, physicalType = FieldMapping.PhysicalType.String)
    public String photoId;

    @FieldMapping(columnName = ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI, physicalType = FieldMapping.PhysicalType.String)
    public String photoThumbnailUri;

    @FieldMapping(columnName = ContactsContract.CommonDataKinds.Phone.PHOTO_URI, physicalType = FieldMapping.PhysicalType.String)
    public String photoUri;

    @FieldMapping(columnName = ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID, physicalType = FieldMapping.PhysicalType.Int)
    public int rawContactId;
}
