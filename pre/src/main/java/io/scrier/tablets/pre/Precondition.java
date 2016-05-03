package io.scrier.tablets.pre;

import com.sun.javadoc.Tag;
import com.sun.tools.doclets.Taglet;

import java.util.Map;

/**
 * Created by andreas on 03/05/16.
 */
public class Precondition implements Taglet {

    private static final String NAME = "scrier.pre";
    private static final String HEADER = "Preconditions:";

    public Precondition() {

    }

    public static void register(Map tagletMap) {
        Taglet taglet = new Precondition();
        if( tagletMap.containsKey(taglet.getName()) ) {
            tagletMap.remove(taglet.getName());
        }
        tagletMap.put(taglet.getName(), taglet);
    }

    public boolean inField() {
        return false;
    }

    public boolean inConstructor() {
        return false;
    }

    public boolean inMethod() {
        return true;
    }

    public boolean inOverview() {
        return false;
    }

    public boolean inPackage() {
        return false;
    }

    public boolean inType() {
        return false;
    }

    public boolean isInlineTag() {
        return false;
    }

    public String getName() {
        return NAME;
    }

    public String toString(Tag tag) {
        return "<dt><span class=\"paramLabel\">" + HEADER + "</span></dt>" +
                "<dd><code>" + tag.text() + "</code></dd>";
    }

    public String toString(Tag[] tags) {
        StringBuilder builder = new StringBuilder("<dt><span class=\"paramLabel\">" + HEADER + "</span></dt>");
        for( Tag tag : tags ) {
            builder.append("<dd><code>" + tag.text() + "</code></dd>");
        }
        return builder.toString();
    }
}
