package pdamianik;

import pdamianik.controller.Controller;
import pdamianik.model.WordTrainer;
import pdamianik.view.ImageLoadingError;

/**
 * Run the {@link WordTrainer}
 *
 * @author pdamianik
 * @version 2020-09-16
 */

public class Main {
	public static void main(String[] args) throws ImageLoadingError {
		new Controller();
	}
}
