package pdamianik.controller;

import pdamianik.model.SerializedFileIO;
import pdamianik.model.WordTrainer;
import pdamianik.view.ImageLoadingError;
import pdamianik.view.Layout;
import pdamianik.view.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * The controller for the word trainer (a word guessing game)
 *
 * @author pdamianik
 * @version 2020-10-29
 */

public class Controller implements ActionListener {
	private final Window window;
	private final Layout layout;
	private WordTrainer trainer;

	/**
	 * Creates and initializes the view and the model for the word trainer
	 *
	 * @throws ImageLoadingError when the image couldn't be loaded
	 */

	public Controller() throws ImageLoadingError {
		// window creation
		window = new Window("Word Trainer", layout = new Layout(this));
		reset();
	}

	/**
	 * Resets the state of the program to its initial state
	 *
	 * @throws ImageLoadingError when the image couldn't be loaded
	 */

	public void reset() throws ImageLoadingError {
		trainer = new WordTrainer();
		try {
			trainer.add("dog", new URL("https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png"));
		} catch (MalformedURLException malformedURLException) {
			window.showException(malformedURLException);
		}
		trainer.getRandomEntry();
		showSelectedEntry();
	}

	/**
	 * Updates all parts of the view to match the model
	 *
	 * @throws ImageLoadingError when the image couldn't be loaded
	 */

	public void showSelectedEntry() throws ImageLoadingError {
		layout.clearWordInput();
		int[] stats = trainer.getStats();
		layout.updateStatus(stats[0], stats[1]);
		layout.setImage(trainer.getSelectedWordEntry().getImageUrl());
		window.pack();
		window.setLocationRelativeTo(null);
	}

	/**
	 * The action Handler that handles all actions from the view and makes the communication between the model and the view possible
	 *
	 * @param e the action event sent by the UI
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		File file = null;
		try {
			switch (e.getActionCommand()) {
				case "GameControl-save":
					file = window.getSaveFile();
					if (file == null)
						break;
					SerializedFileIO.save(file, trainer);
					break;
				case "GameControl-load":
					file = window.getLoadFile();
					if (file == null)
						break;
					trainer = SerializedFileIO.load(file);
					showSelectedEntry();
					break;
				case "Quiz-wordInput":
					trainer.check(layout.getWord());
					trainer.getRandomEntry();
					showSelectedEntry();
					break;
				case "GameControl-reset":
					reset();
					break;
				case "GameControl-addWord":
					String[] newWord = window.getNewWord();
					if (newWord == null) {
						break;
					}
					trainer.add(newWord[0], new URL(newWord[1]));
					trainer.getRandomEntry();
					break;
				default:
					System.out.println("Does nothing");
					break;
			}
		} catch (StreamCorruptedException streamCorruptedException) {
			if (streamCorruptedException.getMessage().startsWith("invalid stream header: "))
				window.showException(new IOException(file.getPath() + " (wrong file type)", streamCorruptedException));
		} catch (Exception exception) {
			window.showException(exception);
		}
	}
}
