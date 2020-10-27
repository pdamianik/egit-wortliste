package pdamianik.model;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A word and a picture (in form of an URL to the image) that shows the meaning of the word
 *
 * @author pdamianik
 * @version 2020-09-15
 */

public class WordEntry implements Serializable {
	private String word;
	private URL imageUrl;

	/**
	 * A new word image pair
	 *
	 * @param word     the word
	 * @param imageUrl the URL to the image
	 */

	public WordEntry(String word, URL imageUrl) {
		setWord(word);
		setImageUrl(imageUrl);
	}

	/**
	 * Returns the word
	 *
	 * @return the word
	 */

	public String getWord() {
		return word;
	}

	/**
	 * Sets the word
	 *
	 * @param word The word. Mustn't be null and has to contain at least two letters.
	 */

	public void setWord(String word) {
		if (word == null || !word.matches("^[a-zA-Z]{2,}$"))
			throw new IllegalArgumentException("The word mustn't be null and has to contain at least two letters");
		this.word = word;
	}

	/**
	 * Returns the URL for the image showing the meaning of the word
	 *
	 * @return the URL for the image showing the meaning of the word
	 */

	public URL getImageUrl() {
		return imageUrl;
	}

	/**
	 * Sets the URL for the image showing the meaning of the word
	 *
	 * @param imageUrl The URL for the image showing the meaning of the word. Mustn't be null and has to have either http or https as it's protocol
	 */

	public void setImageUrl(URL imageUrl) {
		if (!checkURL(imageUrl))
			throw new IllegalArgumentException("The image url mustn't be null and the protocol has to be either http or https");
		this.imageUrl = imageUrl;
	}

	/**
	 * Checks if the passed URL meets the criteria for the imageUrl
	 *
	 * @param imageUrl The URL to check. Mustn't be null and has to have either http or https as it's protocol
	 * @return if the passed URL meets the criteria for the imageUrl
	 */

	public static boolean checkURL(URL imageUrl) {
		return imageUrl != null && (imageUrl.getProtocol().equals("http") || imageUrl.getProtocol().equals("https"));
	}

	/**
	 * Checks if the passed URL meets the criteria for the imageUrl
	 *
	 * @param imageUrl The URL to check. Mustn't be null and has to have either http or https as it's protocol and has to be a valid URL (according to {@link URL})
	 * @return if the passed URL meets the criteria for the imageUrl
	 */

	public static boolean checkURL(String imageUrl) {
		try {
			return checkURL(new URL(imageUrl));
		} catch (MalformedURLException exception) {
			return false;
		}
	}

	/**
	 * Returns a string with the contents of this entry
	 *
	 * @return "word;imageUrl"
	 */

	@Override
	public String toString() {
		return word + ';' + imageUrl;
	}
}
