package defora.beepic;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button btn_Play;
    private Button btn_Pause;
    private Button btn_Stop;
    private ListView lst_Musica;
    private Musica musicaAtual = new Musica("",0);
    private Integer musicaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Play = (Button) findViewById(R.id.btn_play) ;
        btn_Play.setOnClickListener(this);

        btn_Pause = (Button) findViewById(R.id.btn_pause) ;
        btn_Pause.setOnClickListener(this);

        btn_Stop = (Button) findViewById(R.id.btn_stop) ;
        btn_Stop.setOnClickListener(this);

        // Listview--
        lst_Musica = (ListView) findViewById(R.id.list);

        final ArrayList<Musica> arrayMusicas = musicaAtual.PreencherListViewMusica();
        lst_Musica.setAdapter(new MusicaAdapter(this, arrayMusicas));

        lst_Musica.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                musicaSelecionada = arrayMusicas.get(position).getIdMusica();
                musicaAtual.Stop();
            }
        });
        // Fim ListView--

    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {

            case R.id.btn_play:
                Log.i("MainLog","PLAY");
                if (musicaSelecionada != null)
                {
                    musicaAtual.Play(this, musicaSelecionada);
                }
                break;

            case R.id.btn_pause:
                Log.i("MainLog","PAUSE");
                if (musicaSelecionada != null){
                    musicaAtual.Pause();
                }
                break;

            case R.id.btn_stop:
                Log.i("MainLog","STOP");
                if (musicaSelecionada != null){
                    musicaAtual.Stop();
                }
                break;

            default:
                break;
        }
    }
}
