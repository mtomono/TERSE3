/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import collection.TList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 *
 * @author masao
 */
public class Generator {
    public static <T> void arrayToJson(StringBuilder sb, Collection<T> target, BiConsumer<StringBuilder, T> oJson) {
        sb.append('[');
        Iterator<T> iter = target.iterator();
        if (iter.hasNext())
            oJson.accept(sb, iter.next());
        while (iter.hasNext()) {
            sb.append(',');
            oJson.accept(sb, iter.next());
        }
        sb.append(']');
    }
    
    public static void objectToJson(StringBuilder sb, Consumer<StringBuilder>... s) {
        sb.append('{');
        Iterator<Consumer<StringBuilder>> iter = TList.sof(s).iterator();
        if (iter.hasNext())
            iter.next().accept(sb);
        while (iter.hasNext()) {
            sb.append(',');
            iter.next().accept(sb);
        }
        sb.append('}');
    }
    
    public static void vJson(StringBuilder sb, String name) {
        sb.append('"');
        sb.append(name);
        sb.append('"');
    }
    
    public static void vJson(StringBuilder sb, String name, double value) {
        vJson(sb, name);
        sb.append(':');
        sb.append(value);
    }
    
    public static void vJson(StringBuilder sb, String name, int value) {
        vJson(sb, name);
        sb.append(':');
        sb.append(value);
    }

    public static void vJson(StringBuilder sb, String name, long value) {
        vJson(sb, name);
        sb.append(':');
        sb.append(value);
    }}
