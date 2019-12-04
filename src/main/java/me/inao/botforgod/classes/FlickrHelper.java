package me.inao.botforgod.classes;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.SearchParameters;
import me.inao.botforgod.NewMain;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;

public class FlickrHelper {
    private NewMain instance;
    public FlickrHelper(NewMain instance){
        this.instance = instance;
    }
    public PhotoList<Photo> getCollection(String toSearch){
        Flickr f = new Flickr(instance.getConfig().getApi("FlickrApiKey"), instance.getConfig().getApi("FlickrSharedSecret"), new REST());
        SearchParameters parameters = new SearchParameters();
        parameters.setText(Arrays.toString(toSearch.split("_")));
        try{
            return f.getPhotosInterface().search(parameters, 200, 1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public String getUrl(PhotoList<Photo> list){
        Photo photo = list.get(new SecureRandom().nextInt(list.size()));
        return String.format("https://farm%s.staticflickr.com/%s/%s_%s.jpg", photo.getFarm(), photo.getServer(), photo.getId(), photo.getSecret());
    }
}
