import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class ReproductorAudio {

    public static void reproducir(String nombreSonido) {
        String nombreArchivo = System.getProperty("user.dir") + "/src/sonidos/" + nombreSonido + ".mp3";
        try {
            FileInputStream fis = new FileInputStream(nombreArchivo);
            BufferedInputStream bis = new BufferedInputStream(fis);
            Player reproductor = new Player(bis);
            reproductor.play();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
