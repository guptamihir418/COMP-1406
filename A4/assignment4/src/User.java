
import java.util.ArrayList;

public class User {

    private String userName;
    private boolean online;
    private ArrayList<Song> songList;

    public User() {
        this("");
    }

    public User(String u) {
        userName = u;
        online = false;
        songList = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public boolean isOnline() {
        return online;
    }

    public void logon() {
        online = true;
    }

    public void logoff() {
        online = false;
    }

    public ArrayList<Song> getSongList() {
        return songList;
    }

    public void addSong(Song s) {
        s.setOwner(this);
        songList.add(s);
    }

    public int totalSongTime() {
        int totalTime = 0; // total time in seconds 
        for (Song s : songList) {
            totalTime += s.getDuration();
        }
        return totalTime;
    }

    public void register(MusicExchangeCenter m) {
        m.registerUser(this);
    }

    public ArrayList<String> requestCompleteSonglist(MusicExchangeCenter m) {

        ArrayList<String> list = new ArrayList<>();
        String tableHeader = String.format("    %-30s%-20s%-10s%s", "TITLE",
                "ARTIST", "TIME", "OWNER");
        list.add(tableHeader);
        ArrayList<Song> allAvailableSongs = m.allAvailableSongs();
        int number = 1;
        for (Song s : allAvailableSongs) {
            String info = String.format("%2d. %-30s%-20s%-10s%s",
                    number, s.getTitle(), s.getArtist(),
                    String.format("%d:%02d", s.getMinutes(), s.getSeconds()), s.getOwner().getUserName());
            list.add(info);
            number++;
        }

        return list;
    }

    public ArrayList<String> requestSonglistByArtist(MusicExchangeCenter m, String artist) {
        ArrayList<String> list = new ArrayList<>();
        String tableHeader = String.format("    %-30s%-20s%-10s%s", "TITLE",
                "ARTIST", "TIME", "OWNER");
        list.add(tableHeader);
        ArrayList<Song> allAvailableSongs = m.availableSongsByArtist(artist);
        int number = 1;
        for (Song s : allAvailableSongs) {
            String info = String.format("%2d. %-30s%-20s%-10s%s",
                    number, s.getTitle(), s.getArtist(),
                    String.format("%d:%02d", s.getMinutes(), s.getSeconds()), s.getOwner().getUserName());
            list.add(info);
            number++;
        }

        return list;
    }

    public Song songWithTitle(String title) {
        for (Song s : songList) {
            if (s.getTitle().equals(title)) {
                return s;
            }
        }
        return null;
    }
    
    public void downloadSong(MusicExchangeCenter m, String title, String ownerName) {
        Song song = m.getSong(title, ownerName);
        if(song != null)
            songList.add(song);
    }

    public String toString() {
        String s = "" + userName + ": " + songList.size() + " songs (";
        if (!online) {
            s += "not ";
        }
        return s + "online)";
    }
    
}
