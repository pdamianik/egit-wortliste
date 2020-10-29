package pdamianik.model;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Contains methods to save and load a {@link WordTrainer} via a custom serialization method
 * @author pdamianik
 * @version 2020-10-29
 */

public class TextFileIO {
	public static final File DEFAULT_FILE = new File("wordTrainer.txt");

	/**
	 * Save a {@link WordTrainer} at a specific location
	 * @param file the location to save to
	 * @param wordTrainer the {@link WordTrainer} to save
	 * @throws IOException will be thrown if any errors occur during the saving process
	 */

	public static void save(File file, WordTrainer wordTrainer) throws IOException {
		try (PrintWriter outputStream = new PrintWriter(file)) {
			int[] stats = wordTrainer.getStats();
			outputStream.println(stats[0]);
			outputStream.println(stats[1]);
			outputStream.println(wordTrainer.getSelectedWordEntry());
			outputStream.println(wordTrainer.getWordList());
		} catch (IOException ioException) {
			throw new IOException("Error while saving", ioException);
		}
	}

	/**
	 * Save a {@link WordTrainer} to a default location
	 * @param wordTrainer the {@link WordTrainer} to save
	 * @throws IOException will be thrown if any errors occur during the saving process
	 */

	public static void save(WordTrainer wordTrainer) throws IOException {
		save(DEFAULT_FILE, wordTrainer);
	}


	/**
	 * Loads a {@link WordTrainer} from a specific location
	 * @param file the location to load the {@link WordTrainer} from
	 * @return the loaded {@link WordTrainer}
	 * @throws FileNotFoundException will be thrown if any errors occur during the loading process
	 * @throws MalformedURLException will be thrown if any errors occur during the loading process
	 */

	public static WordTrainer load(File file) throws FileNotFoundException, MalformedURLException {
		int[] stats;
		WordList wordList = new WordList();
		WordEntry selectedWordEntry;
		try (Scanner s = new Scanner(new BufferedReader(new FileReader(file)))) {
			stats = new int[]{Integer.parseInt(s.nextLine()), Integer.parseInt(s.nextLine())};
			String[] wordEntryData = s.nextLine().split(";");
			if (wordEntryData[0].equals("null"))
				selectedWordEntry = null;
			else
				selectedWordEntry = new WordEntry(wordEntryData[0], new URL(wordEntryData[1]));
			while (s.hasNextLine()) {
				wordEntryData = s.nextLine().split(";");
				wordList.add(wordEntryData[0], new URL(wordEntryData[1]));
			}
		}
		return new WordTrainer(wordList, selectedWordEntry, stats);
	}


	/**
	 * Loads a {@link WordTrainer} from a default location
	 * @return the loaded {@link WordTrainer}
	 * @throws FileNotFoundException will be thrown if any errors occur during the loading process
	 * @throws MalformedURLException will be thrown if any errors occur during the loading process
	 */

	public static WordTrainer load() throws FileNotFoundException, MalformedURLException {
		return load(DEFAULT_FILE);
	}
}
