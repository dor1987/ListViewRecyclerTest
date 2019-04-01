package a1door.listviewrecyclertest;

public class Item {
    private String mImageUrl;
    private String mCreator;
    private int mLikes;
    private int mDownloads;

    public Item(String mImageUrl, String mCreator, int mLikes,int mDownloads) {
        this.mImageUrl = mImageUrl;
        this.mCreator = mCreator;
        this.mLikes = mLikes;
        this.mDownloads = mDownloads;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmCreator() {
        return mCreator;
    }

    public int getmLikes() {
        return mLikes;
    }

    public int getmDownloads() {
        return mDownloads;
    }
}
