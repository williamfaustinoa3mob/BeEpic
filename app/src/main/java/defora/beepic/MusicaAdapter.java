package defora.beepic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

//Adapter que serve para ligar a ListView com o conteúdo do ArrayList<Musica>

public class MusicaAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Musica> listaMusica;

    public MusicaAdapter(Context context, ArrayList<Musica> listaMusica){
        this.context = context;
        this.listaMusica = listaMusica;
    }

    @Override
    public int getCount() {
        return listaMusica.size();
    }

    @Override
    public Object getItem(int position) {
        return listaMusica.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Musica musica = listaMusica.get(position);
        View musicaCelula;

        if(convertView == null){
            // "Infla" o layout para que ele seja tratado como objeto, possibilitando acessar os controles do layout. Ex: R.id.txtNomeMusica
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            musicaCelula = inflater.inflate(R.layout.musica_cell, null);
        }
        else{
            musicaCelula = convertView;
        }

        TextView nomeMusica = (TextView) musicaCelula.findViewById(R.id.txtNomeMusica);
        nomeMusica.setText(musica.getNomeMusica());

        return musicaCelula;
    }
}
