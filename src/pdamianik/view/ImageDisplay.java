package pdamianik.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

/**
 * A component that displays an image from the internet to the user
 * @author pdamianik
 * @version 2020-09-29
 */

public class ImageDisplay extends JPanel {
	private final ImageIcon imageHolder;
	private final JLabel loadingMessage;

	private static final int IMAGE_HEIGHT = 250;

	/**
	 * Creates the Image display
	 */

	public ImageDisplay() {
		JLabel imageLabel;
		EmptyBorder margin = new EmptyBorder(new Insets(15, 10, 15, 10));
		add(imageLabel = new JLabel(imageHolder = new ImageIcon()));
		imageLabel.setBorder(margin);
		add(loadingMessage = new JLabel("loading..."));
		loadingMessage.setBorder(margin);
	}

	/**
	 * Updates the URL for the image to show
	 * @param imageUrl the URL for the image to show
	 */

	public void setImage(URL imageUrl) {
		loadingMessage.setVisible(true);
		ImageIcon icon = new ImageIcon(imageUrl);
		int width = (int) ((float)(icon.getIconWidth())/icon.getIconHeight()*IMAGE_HEIGHT);
		imageHolder.setImage(icon.getImage().getScaledInstance(width, IMAGE_HEIGHT, Image.SCALE_SMOOTH));
		setPreferredSize(new Dimension(width + 30, IMAGE_HEIGHT + 30));
		loadingMessage.setVisible(false);
	}
}
