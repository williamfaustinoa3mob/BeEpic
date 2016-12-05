package defora.beepic;

import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_Play;
    private Button btn_Pause;
    private Button btn_Stop;
    private Musica musicaAtual = new Musica();

    // Volume
    private SeekBar skb_Volume;
    private AudioManager audioManager;
    private int maxVolume;
    private int curVolume;

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

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        AlterarVolume();
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {

            case R.id.btn_play:
                Log.i("MainLog","PLAY");
                musicaAtual.Play(this,R.raw.taverna);
                break;

            case R.id.btn_pause:
                Log.i("MainLog","PAUSE");
                musicaAtual.Pause();
                break;

            case R.id.btn_stop:
                Log.i("MainLog","STOP");
                musicaAtual.Stop();
                break;

            default:
                break;
        }
    }

    private void AlterarVolume() {
        //teste github
        skb_Volume = (SeekBar) findViewById(R.id.skb_volume);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        skb_Volume.setMax(maxVolume);
        skb_Volume.setProgress(curVolume);
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
