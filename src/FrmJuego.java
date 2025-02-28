import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.DataBufferDouble;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import javazoom.jl.player.Player;

public class FrmJuego extends JFrame {

    Dado dado1, dado2;
    Random r = new Random();
    JLabel lblDado1, lblDado2, lblLanzamientos, lblCenas;
    int lanzamientos, cenas;

    // metodo constructor
    public FrmJuego() {
        setSize(500, 300);
        setTitle("Apostemos a los dados");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        String nombreArchivo = "/imagenes/1.jpg";
        ImageIcon imgDado = new ImageIcon(getClass().getResource(nombreArchivo));

        lblDado1 = new JLabel();
        lblDado1.setIcon(imgDado);
        lblDado1.setBounds(10, 10, imgDado.getIconWidth(), imgDado.getIconHeight());
        getContentPane().add(lblDado1);

        lblDado2 = new JLabel();
        lblDado2.setIcon(imgDado);
        lblDado2.setBounds(10 + imgDado.getIconWidth(), 10, imgDado.getIconWidth(), imgDado.getIconHeight());
        getContentPane().add(lblDado2);

        JLabel lblTituloLanzamientos = new JLabel("Lanzamientos");
        lblTituloLanzamientos.setBounds(30 + 2 * imgDado.getIconWidth(), 10, 100, 25);
        lblTituloLanzamientos.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblTituloLanzamientos);

        JLabel lblTituloCenas = new JLabel("Cenas");
        lblTituloCenas.setBounds(150 + 2 * imgDado.getIconWidth(), 10, 100, 25);
        lblTituloCenas.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblTituloCenas);

        lblLanzamientos = new JLabel("0");
        lblLanzamientos.setBounds(30 + 2 * imgDado.getIconWidth(), 40, 100, 100);
        lblLanzamientos.setHorizontalAlignment(SwingConstants.RIGHT);
        lblLanzamientos.setFont(new Font("Tahoma", 1, 72));
        lblLanzamientos.setBackground(new Color(0, 0, 0));
        lblLanzamientos.setOpaque(true);
        lblLanzamientos.setForeground(new Color(51, 255, 0));
        getContentPane().add(lblLanzamientos);

        lblCenas = new JLabel("0");
        lblCenas.setBounds(150 + 2 * imgDado.getIconWidth(), 40, 100, 100);
        lblCenas.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCenas.setFont(new Font("Tahoma", 1, 72));
        lblCenas.setBackground(new Color(0, 0, 0));
        lblCenas.setOpaque(true);
        lblCenas.setForeground(new Color(51, 255, 0));
        getContentPane().add(lblCenas);

        JButton btnIniciar = new JButton("Iniciar");
        btnIniciar.setBounds(10, 30 + imgDado.getIconHeight(), 100, 25);
        getContentPane().add(btnIniciar);

        JButton btnLanzar = new JButton("Lanzar");
        btnLanzar.setBounds(10, 60 + imgDado.getIconHeight(), 100, 25);
        getContentPane().add(btnLanzar);

        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciar();
            }
        });

        btnLanzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lanzar();
            }
        });
        // instanciar los objetos dado
        dado1 = new Dado();
        dado2 = new Dado();
        // iniciar los contadores
        lanzamientos = 0;
        cenas = 0;
    }

    private void iniciar() {
        // iniciar los contadores
        lanzamientos = 0;
        cenas = 0;
        // mostrar los contadores
        lblLanzamientos.setText("0");
        lblCenas.setText("0");

        // sonido de inicio de lanzamientos
        ReproductorAudio.reproducir("iniciar");
    }

    private void lanzar() {
        dado1.lanzar(r);
        dado1.mostrar(lblDado1);

        dado2.lanzar(r);
        dado2.mostrar(lblDado2);

        getContentPane().repaint();
        dado1.sonar();

        lanzamientos++;
        lblLanzamientos.setText(String.valueOf(lanzamientos));

        if (dado1.getNumero() + dado2.getNumero() >= 11) {
            cenas++;
            lblCenas.setText(String.valueOf(cenas));
            // sonido de cenas
            ReproductorAudio.reproducir("cenas");
        }
    }
}
