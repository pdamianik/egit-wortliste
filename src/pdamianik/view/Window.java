package pdamianik.view;

import javax.swing.*;

/**
 * A universal JFrame to house any JPanel
 *
 * @author pdamianik
 * @version 2020-09-26
 */

public class Window extends JFrame {
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
	}

	public String getSaveFilePath() {
		return JOptionPane.showInputDialog(this, "Please enter the path to the save file:", "Save file path", JOptionPane.QUESTION_MESSAGE);
	}

	public void showException(Exception e) {
		JOptionPane.showMessageDialog(this, e.getMessage(), e.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
	}
}
