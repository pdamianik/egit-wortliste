package pdamianik.model;

import java.net.MalformedURLException;
import java.net.URL;

public class ModelTest {
	public static void main(String[] args) {
		// model test

		WordEntry testWordEntry;
		try {
			testWordEntry = new WordEntry("dog", new URL("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/dog-puppy-on-garden-royalty-free-image-1586966191.jpg?crop=1.00xw:0.669xh;0,0.190xh&resize=1200:*"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new AssertionError();
		}
		WordEntry secondEntry;
		try {
			secondEntry = new WordEntry("horse", new URL("https://test.it/another-image.png"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new AssertionError();
		}
		try {
			testWordEntry.setWord("");
			throw new AssertionError();
		} catch (IllegalArgumentException ignored) {
		}
		try {
			testWordEntry.setWord("12");
			throw new AssertionError();
		} catch (IllegalArgumentException ignored) {
		}
		try {
			testWordEntry.setWord("a");
			throw new AssertionError();
		} catch (IllegalArgumentException ignored) {
		}
		workingAssert(testWordEntry.getWord().equals("dog"));
		testWordEntry.setWord("cat");
		workingAssert(testWordEntry.getWord().equals("cat"));
		try {
			testWordEntry.setImageUrl(new URL("ftp://test.it/the-image.png"));
			throw new AssertionError();
		} catch (IllegalArgumentException | MalformedURLException ignored) {
		}
		try {
			workingAssert(testWordEntry.getImageUrl().equals(new URL("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/dog-puppy-on-garden-royalty-free-image-1586966191.jpg?crop=1.00xw:0.669xh;0,0.190xh&resize=1200:*")));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new AssertionError();
		}
		try {
			testWordEntry.setImageUrl(new URL("https://test.it/the-image.png"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new AssertionError();
		}
		try {
			workingAssert(testWordEntry.getImageUrl().equals(new URL("https://test.it/the-image.png")));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new AssertionError();
		}
		workingAssert(!WordEntry.checkURL("test"));
		workingAssert(!WordEntry.checkURL("ftp://test.it/the-image.png"));
		workingAssert(WordEntry.checkURL("https://test.it/the-image.png"));

		WordEntry testWordEntryTest;
		WordList testWordList = new WordList();
		workingAssert(0 == testWordList.getLength());
		testWordList.add(testWordEntry.getWord(), testWordEntry.getImageUrl());
		workingAssert(1 == testWordList.getLength());
		testWordEntryTest = testWordList.get(0);
		workingAssert(testWordEntry.getWord().equals(testWordEntryTest.getWord()));
		workingAssert(testWordEntry.getImageUrl() == testWordEntryTest.getImageUrl());
		testWordList.add(secondEntry.getWord(), secondEntry.getImageUrl());
		workingAssert(2 == testWordList.getLength());
		testWordEntryTest = testWordList.get(0);
		workingAssert(testWordEntry.getWord().equals(testWordEntryTest.getWord()));
		workingAssert(testWordEntry.getImageUrl() == testWordEntryTest.getImageUrl());
		testWordEntryTest = testWordList.get(1);
		workingAssert(secondEntry.getWord().equals(testWordEntryTest.getWord()));
		workingAssert(secondEntry.getImageUrl() == testWordEntryTest.getImageUrl());
		try {
			testWordList.get(2);
			throw new AssertionError();
		} catch (IndexOutOfBoundsException ignored) {
		}
		try {
			testWordList.get(-1);
			throw new AssertionError();
		} catch (IndexOutOfBoundsException ignored) {
		}
		workingAssert(testWordList.delete("horse"));
		workingAssert(1 == testWordList.getLength());
		testWordEntryTest = testWordList.get(0);
		workingAssert(testWordEntry.getWord().equals(testWordEntryTest.getWord()));
		workingAssert(testWordEntry.getImageUrl() == testWordEntryTest.getImageUrl());
		workingAssert(!testWordList.delete("horse"));
		workingAssert(1 == testWordList.getLength());
		testWordEntryTest = testWordList.get(0);
		workingAssert(testWordEntry.getWord().equals(testWordEntryTest.getWord()));
		workingAssert(testWordEntry.getImageUrl() == testWordEntryTest.getImageUrl());
		workingAssert(!testWordList.delete("Bla bla Entry"));
		workingAssert(1 == testWordList.getLength());
		testWordEntryTest = testWordList.get(0);
		workingAssert(testWordEntry.getWord().equals(testWordEntryTest.getWord()));
		workingAssert(testWordEntry.getImageUrl() == testWordEntryTest.getImageUrl());

		WordTrainer trainer = new WordTrainer();
		trainer.add(testWordEntry.getWord(), testWordEntry.getImageUrl());
		trainer.add(secondEntry.getWord(), secondEntry.getImageUrl());
		int counter = 0;
		for (int i = 0; i < 10; i++) {
			do {
				trainer.getRandomEntry();
				counter++;
			} while (!trainer.getSelectedWordEntry().getWord().equals(testWordEntry.getWord()) && trainer.getSelectedWordEntry().getImageUrl() != testWordEntry.getImageUrl());
		}
		workingAssert(counter > 10, "if this fails then either you were very unlucky (in this case try again) or the getRandomEntry() method isn't as random as it should be");
		testWordEntryTest = trainer.getSelectedWordEntry();
		workingAssert(testWordEntry.getWord().equals(testWordEntryTest.getWord()));
		workingAssert(testWordEntry.getImageUrl() == testWordEntryTest.getImageUrl());
		workingAssert(!trainer.check("bla"));
		workingAssert(!trainer.check("Cat"));
		workingAssert(!trainer.check("caT"));
		workingAssert(trainer.check("cat"));
		workingAssert(trainer.checkIgnoreCase("cat"));
		workingAssert(trainer.checkIgnoreCase("Cat"));
		workingAssert(trainer.checkIgnoreCase("caT"));
		workingAssert(!trainer.checkIgnoreCase("bla"));
	}

	public static void workingAssert(boolean condition) {
		if (!condition)
			throw new AssertionError();
	}

	public static void workingAssert(boolean condition, String msg) {
		if (!condition)
			throw new AssertionError(msg);
	}
}
