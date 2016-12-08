package defora.beepic;

import android.media.MediaPlayer;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

public class Musica {

    private MediaPlayer musicaAtual;

    public void Play(Context c, int nomeMusica)
    {
        Log.i("MusicaLog","MediaPlayer.create");
        // No futuro, o MediaPlayer.create terá que ser movido para o método de seleção de músicas
        // Deixar esse código no método Play() faz com que o objeto seja recriado todas as vezes, atrapalhando na funcionalidade do método Pause()

        if (musicaAtual == null) { // Este if serve para auxiliar o método Pause. Quando o MediaPlayer.create for movido do Play(), esse if deverá ser apagado
            musicaAtual = MediaPlayer.create(c, nomeMusica);
        }

        Log.i("MusicaLog","musicaAtual.start()");
        musicaAtual.start();
    }

    public void Pause()
    {
        Log.i("MusicaLog","musicaAtual.pause()");
        if (musicaAtual != null) { // Este if serve para evitar que o app crash devido ao musicaAtual estár nulo na inicialização do programa
            musicaAtual.pause();
        }
    }

    public void Stop()
    {
        Log.i("MusicaLog","musicaAtual.stop()");
        if (musicaAtual != null) { // Este if serve para evitar que o app crash devido ao musicaAtual estár nulo na inicialização do programa
            musicaAtual.stop();
            musicaAtual = null; // Estou setando nulo para que force a criação do musicaAtual no método Play(), essa linha deve ser apagada depois que o MediaPlayer.create for movido para o método de seleção de músicas
        }
    }

    public ArrayList<Integer> PreencherListViewMusica()
    {
        ArrayList<Integer> Musicas = new ArrayList<>();
        Musicas.add(R.raw.aeroporto);
        Musicas.add(R.raw.alarme);
        Musicas.add(R.raw.alquimista);
        Musicas.add(R.raw.arcade);
        Musicas.add(R.raw.bar);
        Musicas.add(R.raw.bar_de_jazz);
        Musicas.add(R.raw.bar_vazio);
        Musicas.add(R.raw.cidade_atual);
        Musicas.add(R.raw.cidade_medieval);
        Musicas.add(R.raw.escritorio);
        Musicas.add(R.raw.esgoto);
        Musicas.add(R.raw.fabrica);
        Musicas.add(R.raw.fabrica2);
        Musicas.add(R.raw.ferreiro);
        Musicas.add(R.raw.hospital);
        Musicas.add(R.raw.hospital_com_morte);
        Musicas.add(R.raw.igreja);
        Musicas.add(R.raw.lareira);
        Musicas.add(R.raw.metro);
        Musicas.add(R.raw.multidao);
        Musicas.add(R.raw.multidao2);
        Musicas.add(R.raw.porto);
        Musicas.add(R.raw.prisao);
        Musicas.add(R.raw.restaurante);
        Musicas.add(R.raw.ritual);
        Musicas.add(R.raw.tambor);
        Musicas.add(R.raw.taverna);
        return Musicas;
    }
}
