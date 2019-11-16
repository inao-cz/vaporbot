package me.inao.botforgod.classes;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.collections.Collection;
import com.flickr4java.flickr.test.TestInterface;
import me.inao.botforgod.NewMain;

import java.util.Collections;

public class FlickrHelper {
    private NewMain instance;
    public FlickrHelper(NewMain instance){
        this.instance = instance;
    }
    public void getCollection(){
        Flickr f = new Flickr(instance.getConfig().getApi("FlickrApiKey"), instance.getConfig().getApi("FlickrSharedSecret"), new REST());

    }
}
