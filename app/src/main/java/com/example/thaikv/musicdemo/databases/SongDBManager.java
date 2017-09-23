package com.example.thaikv.musicdemo.databases;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.database.MergeCursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;

import com.example.thaikv.musicdemo.application.App;
import com.example.thaikv.musicdemo.BuildConfig;
import com.example.thaikv.musicdemo.listenters.OnGetListAlbumResult;
import com.example.thaikv.musicdemo.listenters.OnGetListArtistResult;
import com.example.thaikv.musicdemo.listenters.OnGetListFolderResult;
import com.example.thaikv.musicdemo.listenters.OnGetListGenreResult;
import com.example.thaikv.musicdemo.models.AlbumMusicStruct;
import com.example.thaikv.musicdemo.models.ArtistMusicStruct;
import com.example.thaikv.musicdemo.models.FolderMusicStruct;
import com.example.thaikv.musicdemo.models.GenresMusicStruct;
import com.example.thaikv.musicdemo.models.SongMusicStruct;
import com.example.thaikv.musicdemo.listenters.OnGetListTrackResult;

import java.io.FileDescriptor;
import java.util.ArrayList;

public class SongDBManager {
    private static SongDBManager instance;
    private Context mContext;
//    private Cursor cursor;
//    private static long totalDuration = 0;
//    private static long totalDurationNewlyAdd = 0;

    public static SongDBManager getInstance() {
        if (instance == null) {
            instance = new SongDBManager(App.getInstance());
        }
        return instance;
    }

    private SongDBManager(Context context) {
        this.mContext = context;
    }

    //get all songs
    public ArrayList<SongMusicStruct> getAllSong(OnGetListTrackResult onGetListTrackResult) {
//        totalDuration = 0;
        ArrayList<SongMusicStruct> listSongForArtist = new ArrayList<>();
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ALBUM_ID,
        };

        ContentResolver musicResolver = mContext.getContentResolver();
        Cursor musicCursor = musicResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                MediaStore.Audio.Media.TITLE + " ASC");
        if (musicCursor != null && musicCursor.moveToFirst()) {
            do {
                SongMusicStruct songs = new SongMusicStruct();
                long idMedia = musicCursor.getLong(musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));//id media
                String mArtists = musicCursor.getString(musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));//singer
                long timeDuration = musicCursor.getLong(musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));//time songs
                String songPath = musicCursor.getString(musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));//data
                String nameSong = musicCursor.getString(musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));//title
                String album = musicCursor.getString(musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));//
                long idAlbum = musicCursor.getLong(musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));

//                totalDuration += timeDuration;

                songs.setDuration(timeDuration);
                songs.setArtist(mArtists);
                songs.setPath(songPath);
                songs.setName(nameSong);
                songs.setIdSong(idMedia);
                songs.setAlbum(album);
                songs.setIdAlbum(idAlbum);

                listSongForArtist.add(songs);

            } while (musicCursor.moveToNext());

        }

        onGetListTrackResult.onGetLisTrackSuccess(listSongForArtist);
        return listSongForArtist;
    }

    //get list artist
    public ArrayList<ArtistMusicStruct> getListArtist(OnGetListArtistResult onGetListArtistResult) {
        ArrayList<ArtistMusicStruct> listArtistMusicStruct = new ArrayList<>();
        String[] mProjection =
                {MediaStore.Audio.Artists._ID,
                        MediaStore.Audio.Artists.ARTIST,
                        MediaStore.Audio.Artists.NUMBER_OF_TRACKS,
                        MediaStore.Audio.Artists.NUMBER_OF_ALBUMS
                };

        ContentResolver musicResolver = mContext.getContentResolver();

        Cursor artistCursor = musicResolver.query(
                MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,
                mProjection,
                null,
                null,
                MediaStore.Audio.Artists.ARTIST + " ASC");
        Cursor[] cursors = {artistCursor};
        final MergeCursor mMergeCursor = new MergeCursor(cursors);
        if (mMergeCursor.moveToFirst()) {
            do {
                ArtistMusicStruct artists = new ArtistMusicStruct();
                long thisId = mMergeCursor.getLong(mMergeCursor.getColumnIndexOrThrow(MediaStore.Audio.Artists._ID));
                String thisTrack = mMergeCursor.getString(mMergeCursor.getColumnIndexOrThrow(MediaStore.Audio.Artists.ARTIST));
                long numberID2 = mMergeCursor.getLong(mMergeCursor.getColumnIndexOrThrow(MediaStore.Audio.Artists.NUMBER_OF_ALBUMS));
                long numberID3 = mMergeCursor.getLong(mMergeCursor.getColumnIndexOrThrow(MediaStore.Audio.Artists.NUMBER_OF_TRACKS));
                artists.setId(thisId);
                artists.setName(thisTrack);
                artists.setNumberAlbumOfArtist(numberID2);
                artists.setNumberSongOfArtist(numberID3);
                listArtistMusicStruct.add(artists);
            } while (mMergeCursor.moveToNext());
        }

        mMergeCursor.close();
        onGetListArtistResult.onGetLisArtistSuccess(listArtistMusicStruct);
        return listArtistMusicStruct;
    }

    //get list song from artist
    public ArrayList<SongMusicStruct> getListSongFromArtist(Context mContext, String singerName) {
        ArrayList<SongMusicStruct> listSongForArtist = new ArrayList<>();
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ALBUM_ID
        };
        ContentResolver musicResolver = mContext.getContentResolver();
        Cursor musicCursor = musicResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                MediaStore.Audio.Artists.ARTIST + " ASC");
        if (musicCursor != null && musicCursor.moveToFirst()) {
            do {
                SongMusicStruct songs = new SongMusicStruct();
                long idMedia = musicCursor.getLong(musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));//id media
                String mArtists = musicCursor.getString(musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));//singer
                long timeDuration = musicCursor.getLong(musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));//time songs
                String songPath = musicCursor.getString(musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));//data
                String nameSong = musicCursor.getString(musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));//title
                String album = musicCursor.getString(musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));//
                Long idAlbum = musicCursor.getLong(musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));//

                if (mArtists.toString().equalsIgnoreCase(singerName)) {
                    songs.setDuration(timeDuration);
                    songs.setArtist(mArtists);
                    songs.setPath(songPath);
                    songs.setName(nameSong);
                    songs.setIdSong(idMedia);
                    songs.setAlbum(album);
                    songs.setIdAlbum(idAlbum);
                    listSongForArtist.add(songs);
                }
            } while (musicCursor.moveToNext());
        }

        return listSongForArtist;
    }

    //get genres
    public ArrayList<GenresMusicStruct> getGenres(OnGetListGenreResult onGetListGenreResult) {
        ArrayList<GenresMusicStruct> mListGenres = new ArrayList<>();
        int index;
        long genreId;
        Uri uri;
        Cursor genreCursor;
        Cursor tempCursor;
        String[] proj1 = {MediaStore.Audio.Genres.NAME, MediaStore.Audio.Genres._ID};
        String[] proj2 = {MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM};

        GenresMusicStruct genresMusicStruct = null;
        genreCursor = mContext.getContentResolver().query(MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI, proj1, null, null, MediaStore.Audio.Genres.NAME + " ASC");
        if (genreCursor != null && genreCursor.moveToFirst()) {
            do {
                genresMusicStruct = new GenresMusicStruct();
                index = genreCursor.getColumnIndexOrThrow(MediaStore.Audio.Genres.NAME);
                if (BuildConfig.DEBUG) Log.i("Tag-Genre name", genreCursor.getString(index));
                genresMusicStruct.setName(genreCursor.getString(index));

                index = genreCursor.getColumnIndexOrThrow(MediaStore.Audio.Genres._ID);
                genreId = Long.parseLong(genreCursor.getString(index));
                genresMusicStruct.setId(genreId);
                uri = MediaStore.Audio.Genres.Members.getContentUri("external", genreId);
                tempCursor = mContext.getContentResolver().query(uri, proj2, null, null, null);
                genresMusicStruct.setNumberSongInGenres(tempCursor.getCount());
                if (!mListGenres.contains(genresMusicStruct)) {
                    mListGenres.add(genresMusicStruct);
                }
                if (tempCursor.moveToFirst()) {
                    do {
                        index = tempCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
                        Log.e("songs", tempCursor.getString(index));
                    } while (tempCursor.moveToNext());
                }
            } while (genreCursor.moveToNext());
        }
        onGetListGenreResult.onGetLisGenreSuccess(mListGenres);
        return mListGenres;
    }

    //get list song from genres uri
    public ArrayList<SongMusicStruct> getListSongForGenres(Context context, Uri uri) {
        ArrayList<SongMusicStruct> mListSongsMusicStructForGenres = new ArrayList<>();
        int index;
        Cursor tempCursor;

        String[] projection = {MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM};

        tempCursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (tempCursor.moveToFirst()) {
            do {
                SongMusicStruct songs = new SongMusicStruct();

                long idMedia = tempCursor.getLong(tempCursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));//id media
                String mArtists = tempCursor.getString(tempCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));//singer
                long timeDuration = tempCursor.getLong(tempCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));//time songs
                String songPath = tempCursor.getString(tempCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));//data
                String nameSong = tempCursor.getString(tempCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));//title
                String album = tempCursor.getString(tempCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));//
                songs.setDuration(timeDuration);
                songs.setArtist(mArtists);
                songs.setPath(songPath);
                songs.setName(nameSong);
                songs.setIdSong(idMedia);
                songs.setAlbum(album);
                mListSongsMusicStructForGenres.add(songs);
            } while (tempCursor.moveToNext());

        }
        return mListSongsMusicStructForGenres;
    }

    //get Album
    public ArrayList<AlbumMusicStruct> getAlbumSongs(OnGetListAlbumResult onGetListAlbumResult) {
        ArrayList<AlbumMusicStruct> mListAlbum = new ArrayList<>();
        String where = null;

        final Uri uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
        final String _id = MediaStore.Audio.Albums._ID;
        final String album_name = MediaStore.Audio.Albums.ALBUM;
        final String artist = MediaStore.Audio.Albums.ARTIST;
        final String albumart = MediaStore.Audio.Albums.ALBUM_ART;
        final String tracks = MediaStore.Audio.Albums.NUMBER_OF_SONGS;

        final String[] columns = {_id, album_name, artist, albumart, tracks};
        Cursor cursor = this.mContext.getContentResolver().query(uri, columns, where,
                null, MediaStore.Audio.Albums.ALBUM + " ASC");


        // add playlsit to list

        if (cursor.moveToFirst()) {

            try {
                do {

                    AlbumMusicStruct albumData = new AlbumMusicStruct();

                    albumData
                            .setId(cursor.getLong(cursor.getColumnIndex(_id)));

                    albumData.setName(cursor.getString(cursor
                            .getColumnIndex(album_name)));

                    albumData.setArtist(cursor.getString(cursor
                            .getColumnIndex(artist)));

                    albumData.setAlbArt(cursor.getString(cursor
                            .getColumnIndex(albumart)));

                    albumData.setNumberSong(cursor.getString(cursor
                            .getColumnIndex(tracks)));

                    albumData.setUriThumbnail(SongDBManager.getInstance().getThumbnailAlbumURI(mContext,
                        albumData.getId()));

                    mListAlbum.add(albumData);

                } while (cursor.moveToNext());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        cursor.close();

        onGetListAlbumResult.onGetLisAlbumSuccess(mListAlbum);
        return mListAlbum;
    }

    // get list song from album
    public ArrayList<SongMusicStruct> getListSongFromAlbum(Context mContext, long ID_Album) {
        ArrayList<SongMusicStruct> listSongForArtist = new ArrayList<>();
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ALBUM_ID,
        };
        ContentResolver musicResolver = mContext.getContentResolver();
        Cursor musicCursor = musicResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                null);
        if (musicCursor != null && musicCursor.moveToFirst()) {
            do {
                SongMusicStruct songs = new SongMusicStruct();
                long idMedia = musicCursor.getLong(musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));//id media
                String mArtists = musicCursor.getString(musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));//singer
                long timeDuration = musicCursor.getLong(musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));//time songs
                String songPath = musicCursor.getString(musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));//data
                String nameSong = musicCursor.getString(musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));//title
                String album = musicCursor.getString(musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));//
                long idAlbum = musicCursor.getLong(musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));

                if (ID_Album == idAlbum) {
                    songs.setDuration(timeDuration);
                    songs.setArtist(mArtists);
                    songs.setPath(songPath);
                    songs.setName(nameSong);
                    songs.setIdSong(idMedia);
                    songs.setAlbum(album);
                    songs.setIdAlbum(idAlbum);
                    listSongForArtist.add(songs);
                }
            } while (musicCursor.moveToNext());
        }

        return listSongForArtist;
    }

    // get all folder;
    public ArrayList<FolderMusicStruct> getAllFolders(OnGetListFolderResult onGetListFolderResult) {
        ArrayList<FolderMusicStruct> folderMusicStructs = new ArrayList<>();
        ArrayList<String> arrayNameFolders = new ArrayList<>();
        ArrayList<String> arrayNameFoldersOrigin = new ArrayList<>();
        ArrayList<String> arrayPathFolder = new ArrayList<>();

        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        String[] projection = {
                MediaStore.Audio.Media.DATA,
        };
        ContentResolver musicResolver = mContext.getContentResolver();
        Cursor musicCursor = musicResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                MediaStore.Audio.Media.TITLE + " ASC");
        if (musicCursor != null && musicCursor.moveToFirst()) {
            do {
                String songPath = musicCursor.getString(musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                String nameFolder = getFolder(songPath);

                arrayNameFolders.add(nameFolder);
                arrayNameFoldersOrigin.add(nameFolder);
                arrayPathFolder.add(getPathFolder(songPath));

            } while (musicCursor.moveToNext());

            ArrayList<String> arrFolderDuplicate = getListDuplicate(arrayNameFolders);
            ArrayList<String> arrPathFolderDuplicate = getListDuplicate(arrayPathFolder);

            for (int i = 0; i < arrFolderDuplicate.size(); i++) {
                FolderMusicStruct itemMusicStruct = new FolderMusicStruct();

                itemMusicStruct.setName(arrFolderDuplicate.get(i));
                itemMusicStruct.setPath(getPathFolder(arrPathFolderDuplicate.get(i)));
                itemMusicStruct.setNumberSong(getNumberMusicFromListDuplicate(arrayNameFoldersOrigin, arrFolderDuplicate.get(i)));

                folderMusicStructs.add(itemMusicStruct);
            }
        }
        onGetListFolderResult.onGetLisFolderSuccess(folderMusicStructs);
        return folderMusicStructs;
    }

//    // get list song from folder name
//    public ArrayList<SongMusicStruct> getListSongFromFolderName(String nameFolder) {
//        ArrayList<SongMusicStruct> arrayListResult = new ArrayList<>();
//        ArrayList<SongMusicStruct> arrayListAllSong = getAllSong();
//        for (int i = 0; i < arrayListAllSong.size(); i++) {
//            String nameFolderSong = getFolder(arrayListAllSong.get(i).getPath());
//            if (nameFolderSong.equals(nameFolder)) {
//                arrayListResult.add(arrayListAllSong.get(i));
//            }
//        }
//        return arrayListResult;
//    }

    // get name folder from path song
    public String getFolder(String path) {
        // path = "/storage/emulated/0/Music/Tạm Biệt Nhé - Lynk Lee,Phúc Bằng.mp3"
        String pathFolder = ""; // "/storage/emulated/0/Music"
        String folder = "";
        try {
            pathFolder = path.substring(0, path.lastIndexOf("/"));
            folder = pathFolder.substring(pathFolder.lastIndexOf("/") + 1, pathFolder.length());
            if (folder.equals("0")) {
                folder = "External Storage";
            }
        } catch (Exception e) {
            folder = "<unknown>";
        }
        return folder;
    }

    // get path folder
    public String getPathFolder(String pathSong) {
        return pathSong.substring(0, pathSong.lastIndexOf("/"));
    }

    // get list duplicate
    private static ArrayList<String> getListDuplicate(ArrayList<String> arrOrigin) {
        Object[] st = arrOrigin.toArray();
        for (Object s : st) {
            if (arrOrigin.indexOf(s) != arrOrigin.lastIndexOf(s)) {
                arrOrigin.remove(arrOrigin.lastIndexOf(s));
            }
        }
        return arrOrigin;
    }

    // get number of list duplicate
    public int getNumberMusicFromListDuplicate(ArrayList<String> arrOrigin, String nameFolder) {
        int count = 0;
        for (int i = 0; i < arrOrigin.size(); i++) {
            if (nameFolder.equals(arrOrigin.get(i))) {
                count++;
            }
        }

        return count;
    }

    public Uri getThumbnailAlbumURI(Context context, Long album_id) {
        Bitmap albumArtBitMap = null;
        Uri uri = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        try {

            final Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");

            uri = ContentUris.withAppendedId(sArtworkUri, album_id);

            ParcelFileDescriptor pfd = context.getContentResolver()
                    .openFileDescriptor(uri, "r");

            if (pfd != null) {
                FileDescriptor fd = pfd.getFileDescriptor();
                albumArtBitMap = BitmapFactory.decodeFileDescriptor(fd, null,
                        options);
                pfd = null;
                fd = null;
            }
        } catch (Error ee) {
        } catch (Exception e) {
        }

        if (null != albumArtBitMap) {
            return uri;
        }
        return null;
    }

}
