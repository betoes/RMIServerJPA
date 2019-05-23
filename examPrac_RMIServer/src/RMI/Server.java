/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JET
 */
public class Server extends UnicastRemoteObject implements IServer {

    public JFrame frame;
    private final List<Image> imagenes;
    List<Image> imagenes2;
    List<Image> imagesEnviar;
    private final List<ICliente> clientes;
    int contadorClientes;
    JDesktopPane pane;
    JProgressBar progressBar;
    private Progress progress;
    private JLabel cc;
    private JLabel limite;
    private JLabel clienteprog;
    EntityManagerFactory emf;
    EntityManager em;

    public Server() throws RemoteException {
        super();
        gui();
        emf = Persistence.createEntityManagerFactory("examPrac_RMIInterfazPU");
        em = emf.createEntityManager();
        imagenes = new ArrayList<>();
        clientes = new ArrayList<>();
        contadorClientes = clientes.size();
        
        Image img = new Image();
        img = em.find(Image.class, 1);
        imagenes.add(img);
        img = em.find(Image.class, 2);
        imagenes.add(img);
        img = em.find(Image.class, 3);
        imagenes.add(img);
        img = em.find(Image.class, 4);
        imagenes.add(img);
        img = em.find(Image.class, 5);
        imagenes.add(img);
        img = em.find(Image.class, 6);
        imagenes.add(img);
        img = em.find(Image.class, 7);
        imagenes.add(img);
        img = em.find(Image.class, 8);
        imagenes.add(img);
        img = em.find(Image.class, 9);
        imagenes.add(img);
        img = em.find(Image.class, 10);
        imagenes.add(img);
        
        imagesEnviar = imagenes;

    }

    private void gui() {
        frame = new JFrame("Server");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pane = new JDesktopPane();
        frame.setContentPane(pane);

        progressBar = new JProgressBar(0, 100);
        JInternalFrame frameIF = new JInternalFrame();
        frameIF.setLayout(new GridLayout(3, 1));

        clienteprog = new JLabel();
        cc = new JLabel();
        limite = new JLabel();
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        JButton botonIniciar = new JButton("Iniciar");
        botonIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (int x = 0; x < clientes.size(); x++) {
                    try {
                        notificarPorcentaje(0, x);
                        progress = new Progress(progressBar, clientes.size());
                        progress.start();

                    } catch (RemoteException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        });
        
        JButton botonEliminar = new JButton("Eliminar");
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        frameIF.add(cc);
        frameIF.add(botonIniciar);
        frameIF.add(clienteprog);
        frameIF.add(progressBar);
        frameIF.add(botonEliminar);
        frameIF.setSize(400, 100);
        frameIF.setVisible(true);
        pane.add(frameIF);
        frame.setVisible(true);

    }

    public void init() throws RemoteException {
        try {
            String direccion = (InetAddress.getLocalHost()).toString();
            int puerto = 3333;
            System.err.println("Iniciando servidor en " + direccion + ":" + puerto);

            Registry registro = LocateRegistry.createRegistry(puerto);
            registro.bind("MiServidor", (IServer) this);

        } catch (Exception ex) {
            System.err.println("Error en" + ex.getMessage());
        }
    }

    @Override
    public void notificarPorcentaje(int porcentaje, int idCliente) throws RemoteException {
        List<Image> img = new ArrayList<>();
        ICliente cliente;
        int i = 1;
        
        Image imgReg;
        if(!clientes.isEmpty()) {
            
            int posCliente = 0;
            
            while(!imagesEnviar.isEmpty()){
                if(posCliente < clientes.size()){
                    em.getTransaction().begin();
                    img.add(imagesEnviar.get(0));
                    imagesEnviar.remove(0);
                    cliente = clientes.get(posCliente);
                    cliente.iniciaProcesamiento(img);
                    img.remove(0);
                    posCliente++;
                    
                    imgReg = em.find(Image.class, i++);
                    imgReg.setFecha(new Date());
                    imgReg.setIdcliente(posCliente);
                    em.getTransaction().commit();
                } else {
                    posCliente = 0;
                }
            } 
            i = 0;
            em.close();
            emf.close();
        }
    }

    @Override
    public int registraCallBackCliente(ICliente cliente) throws RemoteException {
        int i = 1;
        if (!clientes.contains(cliente)) {
            if (clientes.size() < 10) {
                clientes.add(cliente);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        cc.setText("Clientes conectados: " + clientes.size());
                    }
                });

            } else {
                limite.setText("Ya no se pueden agregar mas clientes");
            }

        }
        return clientes.size();
    }

    @Override
    public void desregistraCallBackCliente(ICliente cliente) throws RemoteException {
        if (clientes.contains(cliente)) {
            clientes.remove(cliente);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    cc.setText("Clientes conectados: " + clientes.size());
                }
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                try {
                    (new Server()).init();
                } catch (RemoteException ex) {
                    System.err.println("Error en " + ex.getMessage());
                }
            }

        });
    }
}
