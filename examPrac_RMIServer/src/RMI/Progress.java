/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import javax.swing.JProgressBar;

/**
 *
 * @author JET
 */
public class Progress extends Thread{
    private boolean life;
    private int order;
    private int prog;
    private int size;
    private JProgressBar progBar;
    
    public Progress(Object in, int size) {
        this.progBar = (javax.swing.JProgressBar) in;
        this.life = true;
        this.prog = 0;
        this.size = size;
    }
    public void run() {
        while (life) {
            int porcent = 100 / size;
            this.prog += porcent;
            
            this.progBar.setValue(this.prog);
            
            try{
               Thread.sleep(1250);
            } catch (Exception e) {
                System.out.println("Error en " + e.getMessage());
            }
        }
    }
}
