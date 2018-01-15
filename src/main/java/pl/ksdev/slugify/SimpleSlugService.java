package pl.ksdev.slugify;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SimpleSlugService implements SlugService {

    private static final int DEFAULT_MAX_SLUG_LENGTH = 200;

    private final int maxSlugLength;

    private static final Map<Character, String> charReplMap;

    static {
        charReplMap = new HashMap<>();

        charReplMap.put((char) 32, "-");
        charReplMap.put((char) 160, "-");
        charReplMap.put('\t', "-");
        charReplMap.put('\n', "-");
        charReplMap.put('\r', "-");
        charReplMap.put('-', "-");
        charReplMap.put('_', "-");
        charReplMap.put('.', "-");

        charReplMap.put('0', "0");
        charReplMap.put('1', "1");
        charReplMap.put('2', "2");
        charReplMap.put('3', "3");
        charReplMap.put('4', "4");
        charReplMap.put('5', "5");
        charReplMap.put('6', "6");
        charReplMap.put('7', "7");
        charReplMap.put('8', "8");
        charReplMap.put('9', "9");

        charReplMap.put('a', "a");
        charReplMap.put('b', "b");
        charReplMap.put('c', "c");
        charReplMap.put('d', "d");
        charReplMap.put('e', "e");
        charReplMap.put('f', "f");
        charReplMap.put('g', "g");
        charReplMap.put('h', "h");
        charReplMap.put('i', "i");
        charReplMap.put('j', "j");
        charReplMap.put('k', "k");
        charReplMap.put('l', "l");
        charReplMap.put('m', "m");
        charReplMap.put('n', "n");
        charReplMap.put('o', "o");
        charReplMap.put('p', "p");
        charReplMap.put('q', "q");
        charReplMap.put('r', "r");
        charReplMap.put('s', "s");
        charReplMap.put('t', "t");
        charReplMap.put('u', "u");
        charReplMap.put('v', "v");
        charReplMap.put('w', "w");
        charReplMap.put('x', "x");
        charReplMap.put('y', "y");
        charReplMap.put('z', "z");

        charReplMap.put('á', "a");
        charReplMap.put('ă', "a");
        charReplMap.put('â', "a");
        charReplMap.put('à', "a");
        charReplMap.put('ā', "a");
        charReplMap.put('ǻ', "a");
        charReplMap.put('å', "a");
        charReplMap.put('ä', "ae");
        charReplMap.put('ą', "a");
        charReplMap.put('ǎ', "a");
        charReplMap.put('ã', "a");
        charReplMap.put('а', "a");
        charReplMap.put('ª', "a");
        charReplMap.put('æ', "ae");
        charReplMap.put('ǽ', "ae");
        charReplMap.put('б', "b");
        charReplMap.put('č', "c");
        charReplMap.put('ç', "c");
        charReplMap.put('ц', "c");
        charReplMap.put('ċ', "c");
        charReplMap.put('ĉ', "c");
        charReplMap.put('ć', "c");
        charReplMap.put('ч', "ch");
        charReplMap.put('ð', "dj");
        charReplMap.put('ď', "dj");
        charReplMap.put('д', "dj");
        charReplMap.put('đ', "dj");
        charReplMap.put('э', "e");
        charReplMap.put('é', "e");
        charReplMap.put('ё', "e");
        charReplMap.put('ë', "e");
        charReplMap.put('ê', "e");
        charReplMap.put('е', "e");
        charReplMap.put('ĕ', "e");
        charReplMap.put('è', "e");
        charReplMap.put('ę', "e");
        charReplMap.put('ě', "e");
        charReplMap.put('ė', "e");
        charReplMap.put('ē', "e");
        charReplMap.put('ƒ', "f");
        charReplMap.put('ф', "f");
        charReplMap.put('ġ', "g");
        charReplMap.put('ĝ', "g");
        charReplMap.put('ğ', "g");
        charReplMap.put('г', "g");
        charReplMap.put('ģ', "g");
        charReplMap.put('х', "h");
        charReplMap.put('ĥ', "h");
        charReplMap.put('ħ', "h");
        charReplMap.put('ǐ', "i");
        charReplMap.put('ĭ', "i");
        charReplMap.put('и', "i");
        charReplMap.put('ī', "i");
        charReplMap.put('ĩ', "i");
        charReplMap.put('į', "i");
        charReplMap.put('ı', "i");
        charReplMap.put('ì', "i");
        charReplMap.put('î', "i");
        charReplMap.put('í', "i");
        charReplMap.put('ï', "i");
        charReplMap.put('ĳ', "ij");
        charReplMap.put('ĵ', "j");
        charReplMap.put('й', "j");
        charReplMap.put('я', "ja");
        charReplMap.put('ю', "ju");
        charReplMap.put('ķ', "k");
        charReplMap.put('к', "k");
        charReplMap.put('ľ', "l");
        charReplMap.put('ł', "l");
        charReplMap.put('ŀ', "l");
        charReplMap.put('ĺ', "l");
        charReplMap.put('ļ', "l");
        charReplMap.put('л', "l");
        charReplMap.put('м', "m");
        charReplMap.put('ņ', "n");
        charReplMap.put('ñ', "n");
        charReplMap.put('ń', "n");
        charReplMap.put('н', "n");
        charReplMap.put('ň', "n");
        charReplMap.put('ŉ', "n");
        charReplMap.put('ó', "o");
        charReplMap.put('ò', "o");
        charReplMap.put('ǒ', "o");
        charReplMap.put('ő', "o");
        charReplMap.put('о', "o");
        charReplMap.put('ō', "o");
        charReplMap.put('º', "o");
        charReplMap.put('ơ', "o");
        charReplMap.put('ŏ', "o");
        charReplMap.put('ô', "o");
        charReplMap.put('ö', "oe");
        charReplMap.put('õ', "o");
        charReplMap.put('ø', "o");
        charReplMap.put('ǿ', "o");
        charReplMap.put('œ', "oe");
        charReplMap.put('п', "p");
        charReplMap.put('р', "r");
        charReplMap.put('ř', "r");
        charReplMap.put('ŕ', "r");
        charReplMap.put('ŗ', "r");
        charReplMap.put('ſ', "s");
        charReplMap.put('ŝ', "s");
        charReplMap.put('ș', "s");
        charReplMap.put('š', "s");
        charReplMap.put('ś', "s");
        charReplMap.put('с', "s");
        charReplMap.put('ş', "s");
        charReplMap.put('ш', "sh");
        charReplMap.put('щ', "shch");
        charReplMap.put('ß', "ss");
        charReplMap.put('ţ', "t");
        charReplMap.put('т', "t");
        charReplMap.put('ŧ', "t");
        charReplMap.put('ť', "t");
        charReplMap.put('ț', "t");
        charReplMap.put('у', "u");
        charReplMap.put('ǘ', "u");
        charReplMap.put('ŭ', "u");
        charReplMap.put('û', "u");
        charReplMap.put('ú', "u");
        charReplMap.put('ų', "u");
        charReplMap.put('ù', "u");
        charReplMap.put('ű', "u");
        charReplMap.put('ů', "u");
        charReplMap.put('ư', "u");
        charReplMap.put('ū', "u");
        charReplMap.put('ǚ', "u");
        charReplMap.put('ǜ', "u");
        charReplMap.put('ǔ', "u");
        charReplMap.put('ǖ', "u");
        charReplMap.put('ũ', "u");
        charReplMap.put('ü', "ue");
        charReplMap.put('в', "v");
        charReplMap.put('ŵ', "w");
        charReplMap.put('ы', "y");
        charReplMap.put('ÿ', "y");
        charReplMap.put('ý', "y");
        charReplMap.put('ŷ', "y");
        charReplMap.put('ź', "z");
        charReplMap.put('ž', "z");
        charReplMap.put('з', "z");
        charReplMap.put('ż', "z");
        charReplMap.put('ж', "zh");
    }

    public SimpleSlugService() {
        this.maxSlugLength = DEFAULT_MAX_SLUG_LENGTH;
    }

    /**
     * @param maxSlugLength max length of the resultant slug {@code String}
     *                      (default = {@link #DEFAULT_MAX_SLUG_LENGTH}).
     */
    public SimpleSlugService(int maxSlugLength) {
        if (maxSlugLength <= 0) {
            throw new IllegalArgumentException(
                "maxSlugLength must be greater than zero"
            );
        }

        this.maxSlugLength = maxSlugLength;
    }

    @Override
    public String slugify(String text) {
        Objects.requireNonNull(text);

        StringBuilder slug = new StringBuilder();

        for (char nextChar : text.trim().toLowerCase().toCharArray()) {
            if (charReplMap.containsKey(nextChar)) {
                String replString = charReplMap.get(nextChar);

                // Prevent exceeding the limit
                if (slug.length() + replString.length() > maxSlugLength) {
                    break;
                }

                // Prevent unnecessary dashes
                if (replString.equals("-")
                    && slug.length() > 0
                    && slug.charAt(slug.length() - 1) == '-'
                ) {
                    continue;
                }

                slug.append(replString);
            }
        }

        // Trim dashes
        if (slug.length() > 0) {
            if (slug.charAt(0) == '-') {
                slug.deleteCharAt(0);
            }
            if (slug.charAt(slug.length() - 1) == '-') {
                slug.deleteCharAt(slug.length() - 1);
            }
        }

        return slug.toString();
    }
}
