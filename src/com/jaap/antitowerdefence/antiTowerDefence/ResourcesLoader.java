package com.jaap.antitowerdefence.antiTowerDefence;

import java.io.InputStream;

/**
 * Get path for files in jar-file. Take a String path and try to create a input
 * stream.
 * 
 * @author id10abk Andreas Bolzyk
 *
 */
final public class ResourcesLoader {

    /**
     * 
     * @param String
     *            path the path of the files in the jar-file.
     * @return input stream created on the path.
     */
    public static InputStream load(String path) {
	InputStream input = ResourcesLoader.class.getResourceAsStream(path);

	if (input == null) {
	    input = ResourcesLoader.class.getResourceAsStream("/" + path);
	}

	return input;
    }

}
