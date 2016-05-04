package io.scrier.tablets.pre;

import com.sun.javadoc.Tag;
import com.sun.tools.doclets.Taglet;

import java.util.Map;

/**
 * Created by andreas on 03/05/16.
 */
public class Precondition implements Taglet {

    private static final String X = "X"; // (disable tag)
    private static final String a = "a"; // (all)
    private static final String o = "o"; // (overview)
    private static final String p = "p"; // (packages)
    private static final String t = "t"; // (types, that is classes and interfaces)
    private static final String c = "c"; // (constructors)
    private static final String m = "m"; // (methods)
    private static final String f = "f"; // (fields)

    private static final String NAME = "scrier.pre";
    private static final String HEADER = "Preconditions:";
    private static final String PLACEMENT = "a";

    private static final String NAME_OVERRIDE = "scrier.name";
    private static final String HEADER_OVERRIDE = "scrier.header";
    private static final String PLACEMENT_OVERRIDE = "scrier.placement";

    private final String name;
    private final String header;
    private final String placement;

    public Precondition() {
        this.name = ( null == System.getProperty(NAME_OVERRIDE)
                ? NAME : System.getProperty(NAME_OVERRIDE) );
        this.header = ( null == System.getProperty(HEADER_OVERRIDE)
                ? HEADER : System.getProperty(HEADER_OVERRIDE) );
        this.placement = ( null == System.getProperty(PLACEMENT_OVERRIDE)
                ? PLACEMENT : System.getProperty(PLACEMENT_OVERRIDE) );
    }

    public static void register(Map tagletMap) {
        Taglet taglet = new Precondition();
        if( tagletMap.containsKey(taglet.getName()) ) {
            tagletMap.remove(taglet.getName());
        }
        tagletMap.put(taglet.getName(), taglet);
    }

    public boolean inField() {
        return !this.placement.equals(X) &&
                ( this.placement.equals(f) ||
                        this.placement.equals(a) );
    }

    public boolean inConstructor() {
        return !this.placement.equals(X) &&
                ( this.placement.equals(c) ||
                        this.placement.equals(a) );
    }

    public boolean inMethod() {
        return !this.placement.equals(X) &&
                ( this.placement.equals(m) ||
                        this.placement.equals(a) );
    }

    public boolean inOverview() {
        return !this.placement.equals(X) &&
                ( this.placement.equals(o) ||
                        this.placement.equals(a) );
    }

    public boolean inPackage() {
        return !this.placement.equals(X) &&
                ( this.placement.equals(p) ||
                        this.placement.equals(a) );
    }

    public boolean inType() {
        return !this.placement.equals(X) &&
                ( this.placement.equals(t) ||
                        this.placement.equals(a) );
    }

    public boolean isInlineTag() {
        return false;
    }

    public String getName() {
        return this.name;
    }

    public String toString(Tag tag) {
        return "<dt><span class=\"paramLabel\">" + this.header + "</span></dt>" +
                "<dd><code>" + tag.text() + "</code></dd>";
    }

    public String toString(Tag[] tags) {
        StringBuilder builder = new StringBuilder("<dt><span class=\"paramLabel\">" + this.header + "</span></dt>");
        for( Tag tag : tags ) {
            builder.append("<dd><code>" + tag.text() + "</code></dd>");
        }
        return builder.toString();
    }
}
