package pdamianik.model;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores a list of {@link WordEntry}
 *
 * @author pdamianik
 * @version 2020-09-16
 */

public class WordList implements Serializable {
	private List<WordEntry> wordEntries = new ArrayList<>();

	/**
	 * Getter method for the length of the list
	 *
	 * @return the length
	 */

	public int getLength() {
		return wordEntries.size();
	}

	/**
	 * Adds a new {@link WordEntry} to the list
	 *
	 * @param word     the text part of the {@link WordEntry} to add
	 * @param imageUrl the URL part of the {@link WordEntry} to add
	 */

	public void add(String word, URL imageUrl) {
		if (word == null)
			throw new IllegalArgumentException("The word shall not be null");
		if (imageUrl == null)
			throw new IllegalArgumentException("The image URL shall not be null");
		wordEntries.add(new WordEntry(word, imageUrl));
	}

	/**
	 * Gets a {@link WordEntry} from the list
	 *
	 * @param index the index of the {@link WordEntry} to get
	 * @return the {@link WordEntry} at the specified index
	 */

	public WordEntry get(int index) {
		return wordEntries.get(index);
	}

	/**
	 * Deletes an entry from the word list
	 *
	 * @param word the word of the word entry to delete
	 * @return whether the operation was successful
	 */

	public boolean delete(String word) {
		if (word == null)
			throw new IllegalArgumentException("The word shall not be null");
		for (int i = 0, length = wordEntries.size(); i < length; i++) {
			if (wordEntries.get(i).getWord().equals(word)) {
				wordEntries.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Outputs all entries separated by a newline character
	 *
	 * @return all entries of the list separated by a newline character
	 */

	@Override
	public String toString() {
		if (wordEntries.size() == 0)
			return "";
		WordEntry currentEntry;
		String firstElement = wordEntries.get(0) == null ? "" : wordEntries.get(0).toString();
		StringBuilder builder = new StringBuilder(firstElement);
		for (int i = 1; i < wordEntries.size(); i++)
			if ((currentEntry = wordEntries.get(i)) != null)
				builder.append('\n').append(currentEntry);
		return builder.toString();
	}

	/**
	 * Get all the word entries in this word list
	 *
	 * @return the word entries in this word list as an array
	 */

	public WordEntry[] getWordEntries() {
		return wordEntries.toArray(new WordEntry[0]);
	}
}
