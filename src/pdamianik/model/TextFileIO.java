package pdamianik.model;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class TextFileIO {
	public static final File DEFAULT_FILE = new File("wordTrainer.txt");

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

	public static void save(WordTrainer wordTrainer) throws IOException {
		save(DEFAULT_FILE, wordTrainer);
	}

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

	public static WordTrainer load() throws FileNotFoundException, MalformedURLException {
		return load(DEFAULT_FILE);
	}

	public static void main(String[] args) throws IOException {
		WordList wordList = new WordList();
		wordList.add("cat", new URL("https://google.at/"));
		wordList.add("dog", new URL("https://google.com/"));
		WordTrainer wordTrainer = new WordTrainer(wordList, null, new int[]{1, 2});
		save(wordTrainer);
		WordTrainer loaded = load();
		System.out.println(loaded.getSelectedWordEntry());
		System.out.println(Arrays.toString(loaded.getStats()));
		System.out.println(loaded.getWordList());
	}
}
