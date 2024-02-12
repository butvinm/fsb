/**
 * The MIT License (MIT)
 * Copyright © 2024 Mihail Butvin
 *
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the “Software”), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package butvinm.fsb;

/**
 * Opinionated StringBuilder wrapper.
 */
public class FancyStringBuilder {
    private StringBuilder sb = new StringBuilder();

    private Integer indent = 0;

    /**
     * Append object string repr to content.
     *
     * If previous last char is line break or empty add an indention.
     *
     * @param s - Object to append, interpreted as string.
     * @return this.
     */
    public synchronized FancyStringBuilder a(Object s) {
        i().append(s);
        return this;
    }

    /**
     * Append formatted string to content.
     *
     * If previous last char is line break or empty add an indention.
     *
     * @param s    - String to append.
     * @param args - Formatting args.
     * @return this.
     */
    public synchronized FancyStringBuilder a(String s, Object... args) {
        i().append(s.formatted(args));
        return this;
    }

    /**
     * Append object string repr with line break to content.
     *
     * If previous last char is line break or empty add an indention.
     *
     * @param s - Object to append, interpreted as string.
     * @return this.
     */
    public synchronized FancyStringBuilder l(Object s) {
        return this.a(s).n();
    }

    /**
     * Append formatted string with line break to content.
     *
     * If previous last char is line break or empty add an indention.
     *
     * @param s    - String to append.
     * @param args - Formatting args.
     * @return this.
     */
    public synchronized FancyStringBuilder l(String s, Object... args) {
        return this.a(s, args).n();
    }

    /**
     * Begin block - increase indention.
     *
     * @return this.
     */
    public synchronized FancyStringBuilder bb() {
        indent++;
        return this;
    }

    /**
     * End block - decrease indention.
     *
     * @return this.
     */
    public synchronized FancyStringBuilder eb() {
        if (indent == 0) {
            throw new RuntimeException(
                "Are you dummy dumb dumb? You fucked up indention!"
            );
        }
        indent--;
        return this;
    }

    /**
     * Append space - " ".
     *
     * @return this.
     */
    public synchronized FancyStringBuilder s() {
        i().append(' ');
        return this;
    }

    /**
     * Append line break - "\n".
     *
     * @return this.
     */
    public synchronized FancyStringBuilder n() {
        i().append('\n');
        return this;
    }

    /**
     * Append tabulation - "\t".
     *
     * @return this.
     */
    public synchronized FancyStringBuilder t() {
        i().append('\t');
        return this;
    }

    /**
     * Return current content.
     */
    @Override
    public synchronized String toString() {
        return sb.toString();
    }

    private synchronized StringBuilder i() {
        if (sb.length() == 0 || sb.charAt(sb.length() - 1) == '\n') {
            return sb.append("\t".repeat(indent));
        }
        return sb;
    }
}
