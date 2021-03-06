package player.music.appsworld.com.musicplayer.LauncherActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import player.music.appsworld.com.musicplayer.Album.AlbumFragment;
import player.music.appsworld.com.musicplayer.Artist.FragmentArtist;
import player.music.appsworld.com.musicplayer.FileDirectory.FolderFragment;
import player.music.appsworld.com.musicplayer.Genres.FragmentGenres;
import player.music.appsworld.com.musicplayer.PlayList.PlaylistFragment;
import player.music.appsworld.com.musicplayer.Songs.SongsFragment;

public class SwipeAdapter extends FragmentPagerAdapter {
    private Map<Integer, String> mFragmentTags;
    private FragmentManager mFragmentManager;
    private String mPageTile[];
    private ArrayList<Fragment> fragments;

    public SwipeAdapter(FragmentManager fm, String pageTiles[]) {
        super(fm);
        mPageTile=pageTiles;
        fragments = new ArrayList<>();

        mFragmentManager = fm;
        mFragmentTags = new HashMap<>();

        for (String tab : mPageTile) {
            if (tab.equalsIgnoreCase("ALBUMS")) {
                fragments.add(new AlbumFragment());
            } else if (tab.equalsIgnoreCase("ARTISTS")) {
                fragments.add(new FragmentArtist());
            } else if (tab.equalsIgnoreCase("PLAYLISTS")) {
                fragments.add(new PlaylistFragment());
            } else if (tab.equalsIgnoreCase("SONGS")) {
                fragments.add(new SongsFragment());
            } else if (tab.equalsIgnoreCase("DIRECTORY")) {
                fragments.add(new FolderFragment());
            } else if (tab.equalsIgnoreCase("GENRES")) {
                fragments.add(new FragmentGenres());
            }
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object obj = super.instantiateItem(container, position);
        if (obj instanceof Fragment) {
            Fragment f = (Fragment) obj;
            String tag = f.getTag();
            mFragmentTags.put(position, tag);
        }
        return obj;
    }

    public Fragment getFragment(int position) {
        String tag = mFragmentTags.get(position);
        if (tag == null)
            return null;
        return mFragmentManager.findFragmentByTag(tag);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPageTile[position];
    }

    @Override
    public int getCount() {
        return 6;
    }
}