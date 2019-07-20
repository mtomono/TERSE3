/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

/**
 *
 * @author masao
 */
public class OneLinerDebug extends OneLiner {
    @Override
    public String exec(String line) {
        System.out.println(line);
        return super.exec(line);
    }
}
