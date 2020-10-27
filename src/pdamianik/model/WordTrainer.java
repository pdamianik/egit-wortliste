package pdamianik.model;

import java.io.Serializable;
import java.net.URL;
import java.util.Random;

/**
 * A word trainer that stores combinations of a word in form of a string and a image displaying the meaning of the
 * word as an URL and compares a provided word with the selected word
 *
 * @author Philip Damianik
 * @version 2020-09-16
 */

public class WordTrainer implements Serializable {
	private final WordList wordList;
	private final Random generator = new Random();
	private WordEntry selectedWordEntry;
	private final int[] stats;

	/**
	 * Initializes the word trainer with values (to load the word trainer from a save file)
	 *
	 * @param wordList          the words that are known to the word trainer initially
	 * @param selectedWordEntry the initial selected word entry
	 * @param stats             the initial stats
	 */

	public WordTrainer(WordList wordList, WordEntry selectedWordEntry, int[] stats) {
		this.selectedWordEntry = selectedWordEntry;
		this.stats = stats;
		this.wordList = wordList;
	}

	/**
	 * Initializes the stats array with two zero values
	 */

	public WordTrainer() {
		stats = new int[2];
		wordList = new WordList();
	}

	/**
	 * Adds a new entry to the pool for the random selectable entries
	 *
	 * @param word     the text part of the {@link WordEntry} to add
	 * @param imageUrl the URL part of the {@link WordEntry} to add
	 */

	public void add(String word, URL imageUrl) {
		wordList.add(word, imageUrl);
	}

	/**
	 * Get all the word entries known to this word trainer
	 *
	 * @return all known word entries
	 */

	public WordList getWordList() {
		return wordList;
	}

	/**
	 * Returns and selects a random {@link WordEntry} from the pool
	 *
	 * @return a random {@link WordEntry} from the pool
	 */

	public WordEntry getRandomEntry() {
		return selectedWordEntry = wordList.get(generator.nextInt(wordList.getLength()));
	}

	/**
	 * Returns the selected {@link WordEntry}
	 *
	 * @return the selected {@link WordEntry}
	 */

	public WordEntry getSelectedWordEntry() {
		return selectedWordEntry;
	}

	/**
	 * Checks if a string matches with the word from the selected {@link WordEntry} (case-sensitive)
	 *
	 * @param word the string to check
	 * @return if a string matches with the word from the selected {@link WordEntry} (case-sensitive)
	 */

	public boolean check(String word) {
		stats[0]++;
		if (selectedWordEntry.getWord().equals(word)) {
			stats[1]++;
			return true;
		}
		return false;
	}

	/**
	 * Checks if a string matches with the word from the selected {@link WordEntry} (case-insensitive)
	 *
	 * @param word the string to check
	 * @return if a string matches with the word from the selected {@link WordEntry} (case-insensitive)
	 */

	public boolean checkIgnoreCase(String word) {
		stats[0]++;
		if (selectedWordEntry.getWord().equalsIgnoreCase(word)) {
			stats[1]++;
			return true;
		}
		return false;
	}

	/**
	 * Returns the statistics of the current game of word trainer
	 *
	 * @return an array of integers that contains the total count of checked words and the
	 * amount of checked words that have been wrong
	 */

	public int[] getStats() {
		return stats;
	}
}
