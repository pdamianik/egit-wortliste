package pdamianik.view;

import javax.swing.*;
import java.io.File;

/**
 * A universal JFrame to house any JPanel
 *
 * @author pdamianik
 * @version 2020-09-26
 */

public class Window extends JFrame {
	public final JFileChooser fileChooser;

	/**
	 * Initializes the window
	 *
	 * @param title        the window title
	 * @param contentPanel the panel that shall be shown inside of the window
	 */

	public Window(String title, JPanel contentPanel) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(contentPanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		fileChooser = new JFileChooser(".");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	}

	/**
	 * Shows a file chooser dialog so that the user can choose a file to load from
	 * @return the file chosen by the user
	 */

	public File getLoadFile() {
		if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(this))
			return fileChooser.getSelectedFile();
		return null;
	}

	/**
	 * Shows a file chooser dialog for the user to decide where to save to
	 * @return the file location chosen by the user
	 */

	public File getSaveFile() {
		if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(this))
			return fileChooser.getSelectedFile();
		return null;
	}

	/**
	 * Displays an error to the user
	 * @param e the error to display
	 */

	public void showException(Exception e) {
		JOptionPane.showMessageDialog(this, e.getMessage(), e.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Asks the user for data to create a new {@link pdamianik.model.WordEntry}
	 * @return a string array containing the word and an URL to a image showing the thing described by the word
	 */

	public String[] getNewWord() {
		JTextField word = new JTextField();
		JTextField url = new JTextField();
		final JComponent[] dialogParts = new JComponent[] {
				new JLabel("Word:"),
				word,
				new JLabel("URL:"),
				url
		};
		if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(this, dialogParts, "Add new word", JOptionPane.OK_CANCEL_OPTION)) {
			return new String[] {
					word.getText(),
					url.getText()
			};
		}
		return null;
	}
}
