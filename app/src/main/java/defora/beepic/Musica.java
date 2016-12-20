package defora.beepic;

import android.media.MediaPlayer;
import android.content.Context;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Musica {

    public Integer idMusica;
    public String nomeMusica;
    private MediaPlayer musicaAtual;

    public Musica(String nomeRaw, Integer idRaw)
    {
        idMusica = idRaw;
        nomeMusica = nomeRaw;
    }

    public Integer getIdMusica()
    {
        return  idMusica;
    }

    public String getNomeMusica()
    {
        return  nomeMusica;
    }

    public void Play(Context c, Integer nomeMusica)
    {
        Log.i("MusicaLog","MediaPlayer.create");
        // Este if serve para auxiliar o método Pause.
        // O MediaPlayer.create faz com que o objeto seja criado todas as vezes que entra no método Play(),
        // atrapalhando na funcionalidade do método Pause().
        // Dessa forma o if valida se o objeto já foi criado, possibilitando que o método Pause() funcione normalmente

        if (musicaAtual == null) {
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

    public ArrayList<Musica> PreencherListViewMusica()
    {
        ArrayList<Musica> Musicas = new ArrayList<>();

        // Esse for each serve para verificar todos os arquivos da pasta R.raw e adiciona-los no objeto Música
        Field[] fields = R.raw.class.getFields();
        for(Field f : fields)
            try {
                Musicas.add(new Musica(f.getName(), f.getInt(null)));
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) { }

        return Musicas;
    }
}
