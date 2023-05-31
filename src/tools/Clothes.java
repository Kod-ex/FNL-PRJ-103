package tools;

import java.io.File;

public class Clothes {
	private String _category;
	private int _categoryID;
	private String _name;
	private double _price;
	private String _imageURL;
	
	// initializing the clothes file requires the variables category, categoryID, name, price, and imageURL
	public Clothes(String category, int categoryID, String name, double price, String imageURL) {
		this._category = category;
		this._categoryID = categoryID;
		this._name = name;
		this._price = price;
		this._imageURL = imageURL;
	}

	// function to set the category
	public void setCategory(String category) {
		this._category = category;
	}

	// function to set the category ID
	public void setCategoryID(int categoryID) {
		this._categoryID = categoryID;
	}

	// function to set the name
	public void setName(String name) {
		this._name = name;
	}

	// function to set the imageURL
	public void setImageURL(String imageURL) {
		this._imageURL = imageURL;
	}

	// function to set the price
	public void setPrice(double price) {
		this._price = price;
	}
	
	// function to get the category
	public String getCategory() {
		return this._category;
	}

	// function to get the category ID
	public int getCategoryID() {
		return this._categoryID;
	}

	// function to get the name
	public String getName() {
		return this._name;
	}

	// function to get the imageURL
	public String getImageURL() {
		return this._imageURL;
	}

	// function to get the price
	public double getPrice() {
		return this._price;
	}
		
	
	/* Static functions */
	
	// seperates the string by its capitalization
	private static String sepString(String str) {
		// create a string builder for the result
		StringBuilder result = new StringBuilder();
		
		// loop for every character of the string
		for (char c : str.toCharArray()) {
			// check if the character is capitalized
			if (Character.isUpperCase(c)) {
				// add a whitespace and insert the current character on the resulting string
				result.append(" ");
				result.append(c);
			} else {
				// if not just copy the character
				result.append(c);
			}
		}
		
		// return the resulting character, removing the leading whitespace
		return result.toString().substring(1);
	}
		
	// gets the image from a file
	public static Clothes fromImageName(File file) {
		return fromImageName(file.getAbsolutePath());
	}
	
	// gets the image from a url
	public static Clothes fromImageName(String url) {
		// create a file which will be used for getting the file name
		File file = new File(url);
		
		String  fileName = file.getName().substring(0, file.getName().lastIndexOf(".")), // get the file name excluding the file type
				splittedURL[] = fileName.split("_|-");									 // splits the name using the delimeters '_' and '-'
		// splits the first element from the previous splitting function, this string contains the information for the category and its element rank
		// the string will be splitted if the regex identify that there was a numerical figure in this string for example "HELLO23" becomes {"HELLO", "23"}
		String spCategory[] = splittedURL[0].split("(?<=\\D)(?=\\d)");					 
		
		// create a new clothes variable using the variables extracted from the filename				
		Clothes c = new Clothes(
				spCategory[0], 
				Integer.parseInt(spCategory[1]),
				sepString(splittedURL[1]), 
				Double.parseDouble(splittedURL[2]),
				url);
		
		// check for the created clothes (for debugging)
		// System.out.println(c.toString());
		
		// return the created clothes
		return c;
	}
	
	// shows the string representation of the clothes
	@Override
	public String toString() {
		return  "Category: " + this._category + "\n" +
				"CategoryID: " + this._categoryID + "\n" +
				"Name: " + this._name + "\n" +
				"Price: " + this._price + "\n" +
				"Image URL: " + this._imageURL + "\n" ;
	}
	
	
}
