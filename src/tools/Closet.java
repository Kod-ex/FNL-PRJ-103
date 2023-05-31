package tools;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Closet {
	// list of clothes
	private List<Clothes> clothes;
	// a set of categories (list pero di nauulit)
	private Set<String> categories;
	
	public Closet(){
		this.clothes = new ArrayList<>();
		this.categories = new HashSet<>();
		
		try {
			// get the absolute path of the './products' folder
			String path = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getPath() + "\\products";
			// show the path (for debugging)
			// System.out.println(path);
			
			// get all the files inside the path
			File[] imageFiles = (new File(path)).listFiles();
			
			// loop for every file
			for (File file : imageFiles) {
				// create the Clothes variable from the imagePath
				Clothes c = Clothes.fromImageName(file);
				
				// add the current clothes category to the list of categories
				categories.add(c.getCategory());
				// add the newly created Clothes variable to the list of clothes
				clothes.add(c);
			}
			
			// shot the categories (for debugging)
			// System.out.println(categories.toString());
		
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// function to get the iterator for the clothes
	public Iterator<Clothes> getClothesIter(){
		return clothes.iterator();
	}
	
	// function to get the clothes at specific index
	public Clothes getClothes(int index) {
		return clothes.get(index);
	}
	
	// function to get the closet size
	public int closetSize() {
		return clothes.size();
	}

	// function to get the iterator for the category
	public Iterator<String> getCategoryIter(){
		return categories.iterator();
	}
	
	// function to get the category at specific index
	public String getCategory(int index) {
		int i = 0;
		for (String v: categories) {
			// loop for the item until we reached the specified index
			if (i == index)
		     	return v;
			i++;
		}
		return null;		
	}
	
	// function to get the list of category size
	public int categoriesSize() {
		return categories.size();
	}
	
	
}
