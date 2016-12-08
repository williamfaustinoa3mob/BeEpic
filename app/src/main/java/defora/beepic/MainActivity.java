package defora.beepic;

import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_Play;
    private Button btn_Pause;
    private Button btn_Stop;
    private ListView lst_Musica;
    private Musica musicaAtual = new Musica();
    private Integer musicaSelecionada;

    // Volume
    private SeekBar skb_Volume;
    private AudioManager audioManager;

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
        lst_Musica = (ListView) findViewById(R.id.lst_musica) ;
        final ArrayList<Integer> arrayMusicas = musicaAtual.PreencherListViewMusica();
        ArrayAdapter<Integer> adapterMusicas = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_checked, arrayMusicas);
        lst_Musica.setAdapter(adapterMusicas);

        lst_Musica.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                musicaSelecionada = arrayMusicas.get(position);
                musicaAtual.Stop();
            }
        });
        // Fim ListView--

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        AlterarVolume();

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

    private void AlterarVolume() {
        //teste github
        skb_Volume = (SeekBar) findViewById(R.id.skb_volume);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        skb_Volume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        skb_Volume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        skb_Volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onStopTrackingTouch(SeekBar arg0) {
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar arg0) {

                    }

                    @Override
                    public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, arg1, 0);
                    }
                });
    }

}
