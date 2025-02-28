import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Dado {

    private int numero;

    public void lanzar(Random r) {
        numero = r.nextInt(6) + 1;
    }

    public void mostrar(JLabel lbl) {
        String nombreArchivo = "/imagenes/" + numero + ".jpg";
        ImageIcon imgDado = new ImageIcon(getClass().getResource(nombreArchivo));
        lbl.setIcon(imgDado);
    }

    public int getNumero() {
        return numero;
    }

    public void sonar() {
        ReproductorAudio.reproducir("LanzarDados");
    }

}
