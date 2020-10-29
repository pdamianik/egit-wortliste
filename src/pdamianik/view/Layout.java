package pdamianik.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Initializes and positions all parts of the UI in a border layout
 * @author pdamianik
 * @version 2020-09-26
 */

public class Layout extends JPanel {
	private final Quiz quiz;
	private final ImageDisplay imageDisplay;
	private final GameControl gameControl;

	/**
	 * Initializes the overall layout of the word trainer
	 * @param listener the listener to call when any action event happens
	 */

	public Layout(ActionListener listener) {
		setLayout(new BorderLayout());

		int border_amount = 5;
		setBorder(new EmptyBorder(
				border_amount,
				border_amount,
				border_amount,
				border_amount));

		add(quiz = new Quiz(listener), BorderLayout.PAGE_START);
		add(imageDisplay = new ImageDisplay(), BorderLayout.CENTER);
		add(gameControl = new GameControl(listener), BorderLayout.PAGE_END);
	}

	/**
	 * Returns the word that the user entered
	 * @return the word that the user entered into the text field
	 */

	public String getWord() {
		return quiz.getWord();
	}

	/**
	 * Clears the word input
	 */

	public void clearWordInput() {
		quiz.clearInput();
	}

	/**
	 * Updates the counters to show the updated statistics
	 * @param correct the count of correct words
	 * @param total the total count of words
	 */

	public void updateStatus(int correct, int total) {
		this.gameControl.updateStatus(correct, total);
	}

	/**
	 * Sets the URL for a new image to show
	 * @param imageUrl the URL of a new image to show
	 */

	public void setImage(URL imageUrl) {
		imageDisplay.setImage(imageUrl);
	}
}
