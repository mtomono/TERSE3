/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debug;

import string.Strings;

/**
 *
 * @author masao
 */
public interface Monitorable {
    public String monitor();
    public default String indent(Object o) {
        if (o instanceof Monitorable)
            return Strings.indent(((Monitorable)o).monitor());
        return Strings.indent(o.getClass().getSimpleName());
    }
}
