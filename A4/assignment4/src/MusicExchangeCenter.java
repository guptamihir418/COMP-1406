
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class MusicExchangeCenter {

    private ArrayList<User> users;
    private HashMap<String, Float> royalties;
    private ArrayList<Song> downloadedSongs;

    public MusicExchangeCenter() {
        users = new ArrayList<>();
        royalties = new HashMap<>();
        downloadedSongs = new ArrayList<>();
    }

    public ArrayList<Song> getDownloadedSongs() {
        return downloadedSongs;
    }

    public ArrayList<User> onlineUsers() {
        ArrayList<User> onlineUsers = new ArrayList<>();
        for (User user : users) {
            if (user.isOnline()) {
                onlineUsers.add(user);
            }
        }
        return onlineUsers;
    }

    public ArrayList<Song> allAvailableSongs() {
        ArrayList<Song> allAvailableSongs = new ArrayList<>();
        for (User user : users) {
            if (user.isOnline()) {
                allAvailableSongs.addAll(user.getSongList());
            }
        }
        return allAvailableSongs;
    }

    public User userWithName(String s) {
        for (User user : users) {
            if (user.getUserName().equals(s)) {
                return user;
            }
        }
        return null;
    }

    public void registerUser(User x) {
        if (userWithName(x.getUserName()) == null) {
            users.add(x);
        }
    }

    public ArrayList<Song> availableSongsByArtist(String artist) {
        ArrayList<Song> allAvailableSongs = new ArrayList<>();
        for (User user : users) {
            if (user.isOnline()) {
                ArrayList<Song> songList = user.getSongList();
                for (Song s : songList) {
                    if (s.getArtist().equals(artist)) {
                        allAvailableSongs.add(s);
                    }
                }
            }
        }
        return allAvailableSongs;
    }

    public Song getSong(String title, String ownerName) {
        Song song = null;
        for (User user : users) {
            if (user.isOnline() && user.getUserName().equals(ownerName)) {
                song = user.songWithTitle(title);
                break;
            }
        }
        if (song != null) {
            downloadedSongs.add(song);
            String artist = song.getArtist();
            if (royalties.containsKey(artist)) {
                royalties.put(artist, royalties.get(artist) + 0.25f);
            } else {
                royalties.put(artist, 0.25f);
            }
        }

        return song;
    }

    public void displayRoyalties() {
        System.out.printf("%-10s%s\n", "Amount", "Artist");
        System.out.println("-----------------");
        for (Map.Entry<String, Float> entrySet : royalties.entrySet()) {
            String artist = entrySet.getKey();
            float amount = entrySet.getValue();
            System.out.printf("$%-10.2f%s\n", amount, artist);
        }
    }

    public TreeSet<Song> uniqueDownloads() {
        TreeSet<Song> uniqueDownloads = new TreeSet<>();
        uniqueDownloads.addAll(downloadedSongs);
        return uniqueDownloads;
    }

    public ArrayList<Pair<Integer, Song>> songsByPopularity() {

        ArrayList<Pair<Integer, Song>> list = new ArrayList<>();
        TreeSet<Song> uniqueDownloads = uniqueDownloads();

        for (Song uniqueSong : uniqueDownloads) {
            int numOfDownloads = 0;
            for (Song song : downloadedSongs) {
                if (song == uniqueSong) {
                    numOfDownloads++;
                }
            }
            list.add(new Pair<>(numOfDownloads, uniqueSong));
        }

        Collections.sort(list, new Comparator<Pair<Integer, Song>>() {
            public int compare(Pair<Integer, Song> p1, Pair<Integer, Song> p2) {
                if (p1.getKey() == p2.getKey().intValue()) {
                    return 0;
                } else if (p1.getKey() < p2.getKey().intValue()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        return list;
    }

    public String toString() {
        return "Music Exchange Center (" + onlineUsers().size() + " users on line, "
                + allAvailableSongs().size() + " songs available)";
    }

}
