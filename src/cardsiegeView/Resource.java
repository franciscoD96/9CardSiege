package cardsiegeView;

import java.net.URL;

public class Resource {
	
	public static final URL getResourceFile(String name){
		// opens file with path relative to location of the Resources class
		URL url=Resource.class.getResource(name);
		return url; 
	}

}
