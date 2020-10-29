package pdamianik.controller;

import pdamianik.model.SerializedFileIO;
import pdamianik.model.WordTrainer;
import pdamianik.view.Layout;
import pdamianik.view.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.net.MalformedURLException;
import java.net.URL;

import static pdamianik.model.SerializedFileIO.load;

public class Controller implements ActionListener {
	private final Window window;
	private final Layout layout;
	private WordTrainer trainer;

	public Controller() {
		// window creation
		window = new Window("Word Trainer", layout = new Layout(this));
		reset();
	}

	public void reset() {
		trainer = new WordTrainer();
		try {
			trainer.add("dog", new URL("https://www.pinclipart.com/picdir/middle/20-206356_wenn-hund-clipart.png"));
		} catch (MalformedURLException malformedURLException) {
			window.showException(malformedURLException);
		}
		layout.setImage(trainer.getRandomEntry().getImageUrl());
		window.pack();
		window.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String path = "";
		int[] stats;
		try {
			switch (e.getActionCommand()) {
				case "GameControl-save":
					path = window.getSaveFilePath();
					if (path.length() == 0)
						SerializedFileIO.save(trainer);
					else
						SerializedFileIO.save(new File(path), trainer);
					break;
				case "GameControl-load":
					path = window.getSaveFilePath();
					if (path.length() == 0)
						trainer = SerializedFileIO.load();
					else
						trainer = load(new File(path));
					layout.clearWordInput();
					layout.setImage(trainer.getSelectedWordEntry().getImageUrl());
					stats = trainer.getStats();
					layout.updateStatus(stats[0], stats[1]);
					window.pack();
					window.setLocationRelativeTo(null);
					break;
				case "Quiz-wordInput":
					trainer.check(layout.getWord());
					layout.clearWordInput();
					stats = trainer.getStats();
					layout.updateStatus(stats[0], stats[1]);
					layout.setImage(trainer.getRandomEntry().getImageUrl());
					window.pack();
					window.setLocationRelativeTo(null);
					break;
				case "GameControl-reset":
					reset();
					layout.updateStatus(0, 0);
					layout.clearWordInput();
					break;
				case "GameControl-addWord":
					String[] newWord = window.getNewWord();
					if (newWord == null) {
						break;
					}
					layout.clearWordInput();
					trainer.add(newWord[0], new URL(newWord[1]));
					layout.setImage(trainer.getRandomEntry().getImageUrl());
					window.pack();
					window.setLocationRelativeTo(null);
					break;
				default:
					System.out.println("Does nothing");
					break;
			}
		} catch (StreamCorruptedException streamCorruptedException) {
			if (streamCorruptedException.getMessage().startsWith("invalid stream header: "))
				window.showException(new IOException(path + " (wrong file type)", streamCorruptedException));
		} catch (Exception exception) {
			window.showException(exception);
		}
	}
}
