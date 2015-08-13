package me.everything.providers.stetho;

import android.content.Context;

import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import me.everything.providers.android.browser.Bookmark;
import me.everything.providers.android.browser.BrowserProvider;
import me.everything.providers.android.browser.Search;
import me.everything.providers.android.calendar.Calendar;
import me.everything.providers.android.calendar.CalendarProvider;
import me.everything.providers.android.calllog.Call;
import me.everything.providers.android.calllog.CallsProvider;
import me.everything.providers.android.contacts.Contact;
import me.everything.providers.android.contacts.ContactsProvider;
import me.everything.providers.android.dictionary.DictionaryProvider;
import me.everything.providers.android.dictionary.Word;
import me.everything.providers.android.media.Album;
import me.everything.providers.android.media.Artist;
import me.everything.providers.android.media.Audio;
import me.everything.providers.android.media.File;
import me.everything.providers.android.media.Genre;
import me.everything.providers.android.media.Image;
import me.everything.providers.android.media.MediaProvider;
import me.everything.providers.android.media.Playlist;
import me.everything.providers.android.media.Video;
import me.everything.providers.android.telephony.*;
import me.everything.providers.android.telephony.Thread;
import me.everything.providers.core.Data;
import me.everything.providers.core.Entity;

/**
 * Created by sromku
 */
public class ProvidersStetho {

    private final Context mContext;
    private final List<String> mCategories;
    private final Map<String, Set<String>> mCategoriesProviders;       // category 1:n providers
    private final Map<String, QueryExecutor> mQueryExecutors; // provider 1:1 query_executor

    public ProvidersStetho(Context context) {
        mContext = context;
        mCategories = new ArrayList<>();
        mCategoriesProviders = new HashMap<>();
        mQueryExecutors = new HashMap<>();
    }

    public <T extends Entity> void registerProvider(String providerCategory, String providerName, QueryExecutor<T> queryExecutor) {

        // register category and name
        Set<String> providers = mCategoriesProviders.get(providerCategory);
        if (providers == null) {
            providers = new HashSet<>();
            mCategoriesProviders.put(providerCategory, providers);
            mCategories.add(providerCategory);
        }
        providers.add(providerName);

        // register query executor
        mQueryExecutors.put(makeExecutorKey(providerCategory, providerName), queryExecutor);
    }

    private String makeExecutorKey(String category, String providerName) {
        return category + "-" + providerName;
    }

    public ChromeDevtoolsDomain getChromeDevtoolsDomain() {
        return new Database(mContext, this);
    }

    List<String> getCategories() {
       return mCategories;
    }

    public List<String> getProviders(String categoryName) {
        Set<String> providers = mCategoriesProviders.get(categoryName);
        List<String> names = new ArrayList<>();
        names.addAll(providers);
        return names;
    }

    public boolean hasCategory(String categoryName) {
        return mCategoriesProviders.containsKey(categoryName);
    }

    public String[] getColumns(String categoryName, String providerName) {
        QueryExecutor queryExecutor = mQueryExecutors.get(makeExecutorKey(categoryName, providerName));
        ParameterizedType genericSuperclass = (ParameterizedType) queryExecutor.getClass().getGenericInterfaces()[0];
        Class entity = (Class) (genericSuperclass).getActualTypeArguments()[0];
        return Entity.getColumns(entity);
    }

    public List<? extends Entity> getEntites(String categoryName, String providerName, String query) {
        Data data = mQueryExecutors.get(makeExecutorKey(categoryName, providerName)).onQuery(query);
        if (data != null) {
            return data.getList();
        }
        return null;
    }

    public interface QueryExecutor<T extends Entity> {
        Data<T> onQuery(String query);
    }

    /**
     * Enable all providers
     */
    public void enableDefaults() {

        // ========================
        // Calendars
        // ========================

        registerProvider("provider-calendar", "calendars", new QueryExecutor<Calendar>() {
            @Override
            public Data<Calendar> onQuery(String query) {
                CalendarProvider calendarProvider = new CalendarProvider(mContext);
                return calendarProvider.getCalendars();
            }
        });

        registerProvider("provider-calendar", "events", new QueryExecutor<Calendar>() {
            @Override
            public Data<Calendar> onQuery(String query) {
                CalendarProvider calendarProvider = new CalendarProvider(mContext);
                // TODO - fetch calendar id  from query
                return null;
            }
        });

        registerProvider("provider-calendar", "instances", new QueryExecutor<Calendar>() {
            @Override
            public Data<Calendar> onQuery(String query) {
                CalendarProvider calendarProvider = new CalendarProvider(mContext);
                // TODO - fetch event id from query
                return null;
            }
        });

        registerProvider("provider-calendar", "reminders", new QueryExecutor<Calendar>() {
            @Override
            public Data<Calendar> onQuery(String query) {
                CalendarProvider calendarProvider = new CalendarProvider(mContext);
                // TODO - fetch event id from query
                return null;
            }
        });

        registerProvider("provider-calendar", "attendees", new QueryExecutor<Calendar>() {
            @Override
            public Data<Calendar> onQuery(String query) {
                CalendarProvider calendarProvider = new CalendarProvider(mContext);
                // TODO - fetch event id from query
                return null;
            }
        });

        // ========================
        // Contacts
        // ========================

        registerProvider("provider-contacts", "contacts", new QueryExecutor<Contact>() {
            @Override
            public Data<Contact> onQuery(String query) {
                ContactsProvider contactsProvider = new ContactsProvider(mContext);
                return contactsProvider.getContacts();
            }
        });

        // ========================
        // Call Logs
        // ========================

        registerProvider("provider-calls", "calls", new QueryExecutor<Call>() {
            @Override
            public Data<Call> onQuery(String query) {
                CallsProvider callsProvider = new CallsProvider(mContext);
                return callsProvider.getCalls();
            }
        });

        // ========================
        // Telephony
        // ========================

        registerProvider("provider-telephony", "carriers", new QueryExecutor<Carrier>() {
            @Override
            public Data<Carrier> onQuery(String query) {
                TelephonyProvider telephonyProvider = new TelephonyProvider(mContext);
                return telephonyProvider.getCarriers();
            }
        });

        registerProvider("provider-telephony", "conversations", new QueryExecutor<Conversation>() {
            @Override
            public Data<Conversation> onQuery(String query) {
                TelephonyProvider telephonyProvider = new TelephonyProvider(mContext);
                return telephonyProvider.getConversations();
            }
        });

        registerProvider("provider-telephony", "mms", new QueryExecutor<Mms>() {
            @Override
            public Data<Mms> onQuery(String query) {
                TelephonyProvider telephonyProvider = new TelephonyProvider(mContext);
                return telephonyProvider.getMms(TelephonyProvider.Filter.ALL);
            }
        });

        registerProvider("provider-telephony", "sms", new QueryExecutor<Sms>() {
            @Override
            public Data<Sms> onQuery(String query) {
                TelephonyProvider telephonyProvider = new TelephonyProvider(mContext);
                return telephonyProvider.getSms(TelephonyProvider.Filter.ALL);
            }
        });

        registerProvider("provider-telephony", "threads", new QueryExecutor<me.everything.providers.android.telephony.Thread>() {
            @Override
            public Data<Thread> onQuery(String query) {
                TelephonyProvider telephonyProvider = new TelephonyProvider(mContext);
                return telephonyProvider.getThreads();
            }
        });

        // ========================
        // Browser
        // ========================

        registerProvider("provider-browser", "bookmarks", new QueryExecutor<Bookmark>() {
            @Override
            public Data<Bookmark> onQuery(String query) {
                BrowserProvider browserProvider = new BrowserProvider(mContext);
                return browserProvider.getBookmarks();
            }
        });

        registerProvider("provider-browser", "searches", new QueryExecutor<Search>() {
            @Override
            public Data<Search> onQuery(String query) {
                BrowserProvider browserProvider = new BrowserProvider(mContext);
                return browserProvider.getSearches();
            }
        });

        // ========================
        // Browser
        // ========================

        registerProvider("provider-dictionary", "words", new QueryExecutor<Word>() {
            @Override
            public Data<Word> onQuery(String query) {
                DictionaryProvider dictionaryProvider = new DictionaryProvider(mContext);
                return dictionaryProvider.getWords();
            }
        });

        // ========================
        // Media - External
        // ========================

        registerProvider("provider-media-external", "albums", new QueryExecutor<Album>() {
            @Override
            public Data<Album> onQuery(String query) {
                MediaProvider mediaProvider = new MediaProvider(mContext);
                return mediaProvider.getAlbums(MediaProvider.Storage.EXTERNAL);
            }
        });

        registerProvider("provider-media-external", "artists", new QueryExecutor<Artist>() {
            @Override
            public Data<Artist> onQuery(String query) {
                MediaProvider mediaProvider = new MediaProvider(mContext);
                return mediaProvider.getArtists(MediaProvider.Storage.EXTERNAL);
            }
        });

        registerProvider("provider-media-external", "audio", new QueryExecutor<Audio>() {
            @Override
            public Data<Audio> onQuery(String query) {
                MediaProvider mediaProvider = new MediaProvider(mContext);
                return mediaProvider.getAudios(MediaProvider.Storage.EXTERNAL);
            }
        });

        registerProvider("provider-media-external", "files", new QueryExecutor<File>() {
            @Override
            public Data<File> onQuery(String query) {
                MediaProvider mediaProvider = new MediaProvider(mContext);
                return mediaProvider.getFiles(MediaProvider.Storage.EXTERNAL);
            }
        });

        registerProvider("provider-media-external", "genres", new QueryExecutor<Genre>() {
            @Override
            public Data<Genre> onQuery(String query) {
                MediaProvider mediaProvider = new MediaProvider(mContext);
                return mediaProvider.getGenres(MediaProvider.Storage.EXTERNAL);
            }
        });

        registerProvider("provider-media-external", "images", new QueryExecutor<Image>() {
            @Override
            public Data<Image> onQuery(String query) {
                MediaProvider mediaProvider = new MediaProvider(mContext);
                return mediaProvider.getImages(MediaProvider.Storage.EXTERNAL);
            }
        });

        registerProvider("provider-media-external", "playlists", new QueryExecutor<Playlist>() {
            @Override
            public Data<Playlist> onQuery(String query) {
                MediaProvider mediaProvider = new MediaProvider(mContext);
                return mediaProvider.getPlaylists(MediaProvider.Storage.EXTERNAL);
            }
        });

        registerProvider("provider-media-external", "videos", new QueryExecutor<Video>() {
            @Override
            public Data<Video> onQuery(String query) {
                MediaProvider mediaProvider = new MediaProvider(mContext);
                return mediaProvider.getVideos(MediaProvider.Storage.EXTERNAL);
            }
        });


        // ========================
        // Media - Internal
        // ========================

        registerProvider("provider-media-internal", "albums", new QueryExecutor<Album>() {
            @Override
            public Data<Album> onQuery(String query) {
                MediaProvider mediaProvider = new MediaProvider(mContext);
                return mediaProvider.getAlbums(MediaProvider.Storage.INTERNAL);
            }
        });

        registerProvider("provider-media-internal", "artists", new QueryExecutor<Artist>() {
            @Override
            public Data<Artist> onQuery(String query) {
                MediaProvider mediaProvider = new MediaProvider(mContext);
                return mediaProvider.getArtists(MediaProvider.Storage.INTERNAL);
            }
        });

        registerProvider("provider-media-internal", "audio", new QueryExecutor<Audio>() {
            @Override
            public Data<Audio> onQuery(String query) {
                MediaProvider mediaProvider = new MediaProvider(mContext);
                return mediaProvider.getAudios(MediaProvider.Storage.INTERNAL);
            }
        });

        registerProvider("provider-media-internal", "files", new QueryExecutor<File>() {
            @Override
            public Data<File> onQuery(String query) {
                MediaProvider mediaProvider = new MediaProvider(mContext);
                return mediaProvider.getFiles(MediaProvider.Storage.INTERNAL);
            }
        });

        registerProvider("provider-media-internal", "genres", new QueryExecutor<Genre>() {
            @Override
            public Data<Genre> onQuery(String query) {
                MediaProvider mediaProvider = new MediaProvider(mContext);
                return mediaProvider.getGenres(MediaProvider.Storage.INTERNAL);
            }
        });

        registerProvider("provider-media-internal", "images", new QueryExecutor<Image>() {
            @Override
            public Data<Image> onQuery(String query) {
                MediaProvider mediaProvider = new MediaProvider(mContext);
                return mediaProvider.getImages(MediaProvider.Storage.INTERNAL);
            }
        });

        registerProvider("provider-media-internal", "playlists", new QueryExecutor<Playlist>() {
            @Override
            public Data<Playlist> onQuery(String query) {
                MediaProvider mediaProvider = new MediaProvider(mContext);
                return mediaProvider.getPlaylists(MediaProvider.Storage.INTERNAL);
            }
        });

        registerProvider("provider-media-internal", "videos", new QueryExecutor<Video>() {
            @Override
            public Data<Video> onQuery(String query) {
                MediaProvider mediaProvider = new MediaProvider(mContext);
                return mediaProvider.getVideos(MediaProvider.Storage.INTERNAL);
            }
        });
    }

}