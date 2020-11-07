package pdamianik.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

/**
 * A component that displays an image from the internet to the user
 *
 * @author pdamianik
 * @version 2020-09-29
 */

public class ImageDisplay extends JPanel {
	private final ImageIcon imageHolder;
	private final JLabel imageHolderWrapper;
	private final JLabel loadingMessage;

	private static final int IMAGE_HEIGHT = 250;

	/**
	 * Creates the Image display
	 */

	public ImageDisplay() {
		EmptyBorder margin = new EmptyBorder(new Insets(15, 10, 15, 10));
		add(imageHolderWrapper = new JLabel(imageHolder = new ImageIcon()));
		imageHolderWrapper.setBorder(margin);
		add(loadingMessage = new JLabel("loading..."));
		loadingMessage.setBorder(margin);
	}

	/**
	 * Updates the URL for the image to show
	 *
	 * @param imageUrl the URL for the image to show
	 * @throws ImageLoadingError when the image couldn't be loaded
	 */

	public void setImage(URL imageUrl) throws ImageLoadingError {
		loadingMessage.setVisible(true);
		imageHolderWrapper.setVisible(false);
		ImageIcon icon = new ImageIcon(imageUrl);
		if (icon.getImageLoadStatus() == MediaTracker.ERRORED || icon.getImageLoadStatus() == MediaTracker.ABORTED)
			throw new ImageLoadingError("The image couldn't be loaded!");
		int width = (int) ((float) (icon.getIconWidth()) / icon.getIconHeight() * IMAGE_HEIGHT);
		imageHolder.setImage(icon.getImage().getScaledInstance(width, IMAGE_HEIGHT, Image.SCALE_SMOOTH));
		setPreferredSize(new Dimension(width + 30, IMAGE_HEIGHT + 30));
		loadingMessage.setVisible(false);
		imageHolderWrapper.setVisible(true);
	}
}
