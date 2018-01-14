package pl.ksdev.slugify;

public interface SlugService {

    /**
     * Change the text to slug.
     *
     * @param text a {@code String} to be changed to slug.
     * @return a slug {@code String}.
     */
    String slugify(String text);
}