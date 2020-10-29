package pdamianik.model;

import java.io.*;

/**
 * Contains methods to save and load a {@link WordTrainer} via the java internal object serialization
 * @author pdamianik
 * @version 2020-10-29
 */

public class SerializedFileIO {
	public static final File DEFAULT_FILE = new File("wordTrainer.sav");

	/**
	 * Save a {@link WordTrainer} at a specific location
	 * @param file the location to save to
	 * @param wordTrainer the {@link WordTrainer} to save
	 * @throws IOException will be thrown if any errors occur during the saving process
	 */

	public static void save(File file, WordTrainer wordTrainer) throws IOException {
		try (ObjectOutputStream writer = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
			writer.writeObject(wordTrainer);
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
	 * @throws IOException will be thrown if any errors occur during the loading process
	 */

	public static WordTrainer load(File file) throws IOException {
		try (ObjectInputStream reader = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
			return (WordTrainer) reader.readObject();
		} catch (ClassNotFoundException e) {
			throw new IOException("Could not read Save file: Wrong class type", e);
		}
	}

	/**
	 * Loads a {@link WordTrainer} from a default location
	 * @return the loaded {@link WordTrainer}
	 * @throws IOException will be thrown if any errors occur during the loading process
	 */

	public static WordTrainer load() throws IOException {
		return load(DEFAULT_FILE);
	}
}
