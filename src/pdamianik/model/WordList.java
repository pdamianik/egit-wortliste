package pdamianik.model;

import java.io.Serializable;
import java.net.URL;

/**
 * Stores a list of {@link WordEntry}
 *
 * @author pdamianik
 * @version 2020-09-16
 */

public class WordList implements Serializable {
	private WordEntry[] wordEntries = new WordEntry[1];
	private int length = 0;

	/**
	 * Getter method for the length of the list
	 *
	 * @return the length
	 */

	public int getLength() {
		return length;
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
		wordEntries[length++] = new WordEntry(word, imageUrl);
		if (length == wordEntries.length) {
			WordEntry[] tmpArray = new WordEntry[wordEntries.length * 2];
			for (int i = 0; i < wordEntries.length; i++)
				tmpArray[i] = wordEntries[i];
			wordEntries = tmpArray;
		}
	}

	/**
	 * Gets a {@link WordEntry} from the list
	 *
	 * @param index the index of the {@link WordEntry} to get
	 * @return the {@link WordEntry} at the specified index
	 */

	public WordEntry get(int index) {
		if (index < 0 || index >= length)
			throw new IndexOutOfBoundsException("Index " + index + " out of range for length " + length);
		return wordEntries[index];
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
		for (int i = 0; i < length; i++) {
			if (wordEntries[i].getWord().equals(word)) {
				for (; i < --length; i++)
					wordEntries[i] = wordEntries[i + 1];
				wordEntries[length] = null;
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
		if (wordEntries.length == 0)
			return "";
		String firstElement = wordEntries[0] == null ? "" : wordEntries[0].toString();
		StringBuilder builder = new StringBuilder(firstElement);
		for (int i = 1; i < wordEntries.length; i++)
			if (wordEntries[i] != null)
				builder.append('\n').append(wordEntries[i]);
		return builder.toString();
	}

	/**
	 * Get all the word entries in this word list
	 *
	 * @return the word entries in this word list as an array
	 */

	public WordEntry[] getWordEntries() {
		return wordEntries;
	}
}
