package lesson6;

import com.alexsid.lesson6.TextGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextGeneratorTest {

    private TextGenerator generator;


    @Before
    public void initGenerator() {
        generator = new TextGenerator(15, 15, 20, 5);
    }

    @After
    public void dropGenerator() {
        generator = null;
    }

    @Test
    public void testSentenceStartWithCapitalLetter() {
        String sentence = generator.generateSentence();
        String firstLetter = sentence.substring(0, 1);
        assertEquals(firstLetter.toUpperCase(), firstLetter);
    }

    @Test
    public void testIsCommaComesRightAfterWord() {
        char[] charsSentence = generator.generateSentence().toCharArray();
        for (int i = 0; i < charsSentence.length; i++) {
            if (charsSentence[i] == ',') {
                assertNotEquals(' ', charsSentence[i - 1]);
            }
        }
    }

    @Test
    public void testIsCommaComesRightBeforeSpace() {
        char[] charsSentence = generator.generateSentence().toCharArray();
        for (int i = 0; i < charsSentence.length; i++) {
            if (charsSentence[i] == ',') {
                assertEquals(' ', charsSentence[i + 1]);
            }
        }
    }

}