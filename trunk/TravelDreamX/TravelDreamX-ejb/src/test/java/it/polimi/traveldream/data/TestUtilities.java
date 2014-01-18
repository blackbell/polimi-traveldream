package it.polimi.traveldream.data;

/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */



import java.util.Random;

/**
 *
 * @author Dario
 */
public class TestUtilities {

    static final String[] placeHolderImgLinks = {
        "http://lorempixel.com/800/600/nature",
        "http://lorempixel.com/800/600/food",
        "http://lorempixel.com/800/600/abstract",
        "http://lorempixel.com/800/600/people",
        "http://lorempixel.com/800/600/cats",
        "http://lorempixel.com/800/600/business",
        "http://lorempixel.com/800/600/city"
       };
    static Random rnd = new Random();
    public static String getRandomImageLink() {
        int i = rnd.nextInt(placeHolderImgLinks.length);
        return placeHolderImgLinks[i];
    }
    
}
