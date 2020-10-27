package pdamianik.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * A component that displays the user some statistics for the word trainer and provides buttons to control the game
 *
 * @author pdamianik
 * @version 2020-09-29
 */

public class GameControl extends JPanel {
	private final JLabel wordsCorrect;
	private final JLabel totalWordCount;

	/**
	 * Creates the game control ui
	 *
	 * @param listener the listener to call when the user hits any of the buttons
	 */

	public GameControl(ActionListener listener) {
		setLayout(new GridLayout(2, 4));

		JButton reset = new JButton("Reset");
		JButton addWord = new JButton("Add a new word");
		JButton save = new JButton("Save");
		JButton load = new JButton("Load");

		add(new JLabel("Words correct:"));
		add(wordsCorrect = new JLabel("0"));
		add(reset);
		add(save);
		add(new JLabel("Words total:"));
		add(totalWordCount = new JLabel("0"));
		add(addWord);
		add(load);

		reset.addActionListener(listener);
		reset.setActionCommand("GameControl-reset");
		addWord.addActionListener(listener);
		addWord.setActionCommand("GameControl-addWord");
		save.addActionListener(listener);
		save.setActionCommand("GameControl-save");
		load.addActionListener(listener);
		load.setActionCommand("GameControl-load");
	}

	public void updateStatus(int correct, int total) {
		wordsCorrect.setText(Integer.toString(correct));
		totalWordCount.setText(Integer.toString(total));
	}
}
