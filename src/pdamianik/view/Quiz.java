package pdamianik.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * A component to ask the user for a word
 * @author pdamianik
 * @version 2020-09-29
 */

public class Quiz extends JPanel {
	JTextField wordInput;

	/**
	 * Creates the quiz ui
	 * @param listener the listener to call when the use hits enter inside of the text field
	 */

	public Quiz(ActionListener listener) {
		setLayout(new GridLayout(2, 1));

		JLabel actionLabel = new JLabel("Which word is being displayed by the image below (Enter to check)?");
		actionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		actionLabel.setBorder(new EmptyBorder(0, 0, 5, 0));

		add(actionLabel);
		add(wordInput = new JTextField());

		wordInput.addActionListener(listener);
		wordInput.setActionCommand("Quiz-wordInput");
	}

	/**
	 * Returns the word entered by the user
	 * @return the content of the text field
	 */

	public String getWord() {
		return wordInput.getText();
	}
}
