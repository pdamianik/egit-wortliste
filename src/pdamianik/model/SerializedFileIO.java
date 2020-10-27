package pdamianik.model;

import java.io.*;
import java.net.URL;
import java.util.Arrays;

public class SerializedFileIO {
	public static final File DEFAULT_FILE = new File("wordTrainer.sav");

	public static void save(File file, WordTrainer wordTrainer) throws IOException {
		try (ObjectOutputStream writer = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
			writer.writeObject(wordTrainer);
		}
	}

	public static void save(WordTrainer wordTrainer) throws IOException {
		save(DEFAULT_FILE, wordTrainer);
	}

	public static WordTrainer load(File file) throws IOException {
		try (ObjectInputStream reader = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
			return (WordTrainer) reader.readObject();
		} catch (ClassNotFoundException e) {
			throw new IOException("Could not read Save file: Wrong class type", e);
		}
	}

	public static WordTrainer load() throws IOException {
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
