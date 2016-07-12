package me.everything.providers.android.contacts;

import android.provider.ContactsContract.CommonDataKinds.Phone;

import me.everything.providers.core.Entity;
import me.everything.providers.core.FieldMapping;

/**
 * Created by Ilya Gazman on 6/20/2016.
 */
public class BaseContactEntity extends Entity {



    @FieldMapping(columnName = "contact_last_updated_timestamp", physicalType = FieldMapping.PhysicalType.String)
    public String lastUpdatedTimestamp;

    @FieldMapping(columnName = Phone.HAS_PHONE_NUMBER, physicalType = FieldMapping.PhysicalType.String)
    public String phoneNumber;

    @FieldMapping(columnName = "in_default_directory", physicalType = FieldMapping.PhysicalType.String)
    public String defaultDirectory;

    @FieldMapping(columnName = Phone.IN_VISIBLE_GROUP, physicalType = FieldMapping.PhysicalType.String)
    public String visibleGroup;

    @FieldMapping(columnName = Phone.IS_USER_PROFILE, physicalType = FieldMapping.PhysicalType.String)
    public String userProfile;

    @FieldMapping(columnName = Phone.LOOKUP_KEY, physicalType = FieldMapping.PhysicalType.String)
    public String lookupKey;



    @FieldMapping(columnName = Phone.DATA_VERSION, physicalType = FieldMapping.PhysicalType.String)
    public String dataVersion;

    @FieldMapping(columnName = Phone.IS_PRIMARY, physicalType = FieldMapping.PhysicalType.String)
    public String isPrimary;

    @FieldMapping(columnName = Phone.IS_READ_ONLY, physicalType = FieldMapping.PhysicalType.String)
    public String isReadOnly;

    @FieldMapping(columnName = Phone.IS_SUPER_PRIMARY, physicalType = FieldMapping.PhysicalType.String)
    public String isSuperPrimary;

    @FieldMapping(columnName = Phone.MIMETYPE, physicalType = FieldMapping.PhysicalType.String)
    public String mimetype;



    @FieldMapping(columnName = "res_package", physicalType = FieldMapping.PhysicalType.String)
    public String resPackage;



    @FieldMapping(columnName = "metadata_dirty", physicalType = FieldMapping.PhysicalType.String)
    public String metadataDirty;


    @FieldMapping(columnName = Phone.DISPLAY_NAME_ALTERNATIVE, physicalType = FieldMapping.PhysicalType.String)
    public String displayNameAlternative;

    @FieldMapping(columnName = Phone.DISPLAY_NAME_PRIMARY, physicalType = FieldMapping.PhysicalType.String)
    public String displayNamePrimary;

    @FieldMapping(columnName = Phone.DISPLAY_NAME_SOURCE, physicalType = FieldMapping.PhysicalType.String)
    public String displayNameSource;

    @FieldMapping(columnName = Phone.PHONETIC_NAME, physicalType = FieldMapping.PhysicalType.String)
    public String phoneticName;

    @FieldMapping(columnName = Phone.PHONETIC_NAME_STYLE, physicalType = FieldMapping.PhysicalType.String)
    public String phoneticNameStyle;

    @FieldMapping(columnName = Phone.SORT_KEY_ALTERNATIVE, physicalType = FieldMapping.PhysicalType.String)
    public String sortKeyAlternative;

    @FieldMapping(columnName = Phone.SORT_KEY_PRIMARY, physicalType = FieldMapping.PhysicalType.String)
    public String sortKeyPrimary;

    @FieldMapping(columnName = Phone.CUSTOM_RINGTONE, physicalType = FieldMapping.PhysicalType.String)
    public String customRingtone;



    @FieldMapping(columnName = Phone.SEND_TO_VOICEMAIL, physicalType = FieldMapping.PhysicalType.String)
    public String sendToVoiceMail;




}
