package pl.ksdev.slugify;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class SimpleSlugServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnSlugifiedString() {
        // given
        String text1 = "Zażółć" + (char) 32 + "gęślą" + (char) 160 + "jaźń";
        String text2 = " # !@ $ ZażóŁć\tGęślą jaźŃ Лорем ипсум ; : > < / \\ ." +
            " * % $ file.java\rDès Noël où un zéphyr\nABCDEFGHIJKLMNOPQRSTUVW" +
            "XYZ 0123456789 # *(@# # @ œžŸ ¡¢£ ¤¥ ¦ § ";

        SlugService slugService = new SimpleSlugService();

        // when
        String result1 = slugService.slugify(text1);
        String result2 = slugService.slugify(text2);

        // then
        assertEquals("zazolc-gesla-jazn", result1);
        assertEquals("zazolc-gesla-jazn-lorem-ipsum-file-java-des-noel-ou-un-" +
            "zephyr-abcdefghijklmnopqrstuvwxyz-0123456789-oezy", result2);
    }

    @Test
    public void shouldReturnEmptyString() {
        // given
        String text1 = "";
        String text2 = " @ \n#* \\ $/&) !(&*\r# . ..";

        // when
        String result1 = new SimpleSlugService().slugify("");
        String result2 = new SimpleSlugService().slugify("");

        // then
        assertEquals("", result1);
        assertEquals("", result2);
    }

    @Test
    public void shouldFailFastWhenNull() {
        // given
        String text = null;

        // then
        thrown.expect(NullPointerException.class);

        // when
        String result = new SimpleSlugService().slugify(text);
    }

    @Test
    public void shouldLimitSlugLength() {
        // given
        String text1 = "Pójdźże, kiń tę chmurność w głąb flaszy!";
        String text2 = "Whyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy" +
            "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy" +
            "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy" +
            "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy" +
            "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy";

        // when
        String result1 = new SimpleSlugService(5).slugify(text1);
        String result2 = new SimpleSlugService().slugify(text2);

        // then
        assertEquals(result1.length(), 5);
        assertEquals(result2.length(), 200);
    }

    @Test
    public void shouldThrowExceptionWhenInvalidLimit() {
        // given
        String text = "Dość błazeństw, żrą mój pęk luźnych fig";

        // then
        thrown.expect(IllegalArgumentException.class);

        // when
        String result = new SimpleSlugService(-5).slugify(text);
    }
}