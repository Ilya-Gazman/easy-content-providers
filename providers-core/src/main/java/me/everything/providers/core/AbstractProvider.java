package me.everything.providers.core;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by sromku
 */
public abstract class AbstractProvider {

    protected String TAG;
    protected ContentResolver mContentResolver;
    private String selection;

    public void setSelection(String selection) {
        this.selection = selection;
    }

    protected AbstractProvider(Context context) {
        TAG = getClass().getName();
        mContentResolver = context.getContentResolver();
    }

    public void registerContentObserver(Uri uri, ContentObserver observer) {
        mContentResolver.registerContentObserver(uri, false, observer);
    }

    protected <T extends Entity> Data<T> getContentTableData(Uri uri, Class<T> cls) {
        Cursor cursor = mContentResolver.query(uri, Entity.getColumns(cls), selection, null, null /*BaseColumns._ID + " DESC " + " LIMIT 50" */);

        return new Data<>(cursor, cls);
    }

    protected <T extends Entity> Data<T> getContentTableData(Uri uri, String selection, String[] selectionArgs, String sortOrder, Class<T> cls) {
        Cursor cursor = mContentResolver.query(uri, Entity.getColumns(cls), selection, selectionArgs, sortOrder);
        if (cursor == null) {
            return null;
        }

        return new Data<>(cursor, cls);
    }

    protected <T extends Entity> T getContentRowData(Uri uri, String selection, String[] selectionArgs, String sortOrder, Class<T> cls) {
        T t = null;
        Cursor cursor = mContentResolver.query(uri, Entity.getColumns(cls), selection, selectionArgs, sortOrder);
        if (cursor == null) {
            return null;
        }
        try {
            if (cursor.moveToNext()) {
                t = Entity.create(cursor, cls);
            }
        } finally {
            cursor.close();
        }
        return t;
    }

    protected int updateTableRow(Uri uri, Entity entity) {
        Long id = Entity.getId(entity);
        if (id != null) {
            String[] columns = Entity.getWriteColumns(entity.getClass());
            if (columns.length > 0) {
                ContentValues values = Entity.getContentValues(columns, entity);
                Uri updateUri = ContentUris.withAppendedId(uri, id);
                return mContentResolver.update(updateUri, values, null, null);
            }
        }
        return 0;
    }

}
