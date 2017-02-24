package com.marano.gianluca.todo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marano.gianluca.todo.R;
import com.marano.gianluca.todo.model.Nota;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gianluca Marano on 24/02/2017.
 */

public class ViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<String> campi;
    Nota n;

    public ViewAdapter(Nota nota) {
        campi = new ArrayList<>();
        n = nota;
        campi.add(nota.getTitolo());
        campi.add(nota.getCorpo());
        campi.add(nota.getDataCreazione());
        campi.add(nota.getDataUltimaModifica());
        campi.add(nota.getDataScadenza());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_titolo, parent, false);
                return new ViewAdapter.TitoloVH(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_testo, parent, false);
                return new ViewAdapter.TestoVH(view);
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prima_data, parent, false);
                return new ViewAdapter.PrimaDataVH(view);
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);
                return new ViewAdapter.DataVH(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                TitoloVH titoloVH = (TitoloVH) holder;
                titoloVH.titoloTv.setText(campi.get(holder.getAdapterPosition()));
                if (n.isSpeciale())
                    titoloVH.starImg.setVisibility(View.VISIBLE);
                else
                    titoloVH.starImg.setVisibility(View.INVISIBLE);
                break;
            case 1:
                TestoVH testoVH = (TestoVH) holder;
                testoVH.testo.setText(campi.get(holder.getAdapterPosition()));
                break;
            case 2:
                PrimaDataVH primaDataVH = (PrimaDataVH) holder;
                primaDataVH.data.setText(campi.get(holder.getAdapterPosition()));
                primaDataVH.dataNome.setText("Data Creazione");
                break;
            case 3:
                DataVH dataVH = (DataVH) holder;
                dataVH.data.setText(campi.get(holder.getAdapterPosition()));
                if (holder.getAdapterPosition() == 3) {
                    dataVH.dataNome.setText("Data ultima modifica");
                } else if (holder.getAdapterPosition() == 4) {
                    dataVH.dataNome.setText("Data scadenza");
                }
        }

    }

    @Override
    public int getItemCount() {
        return campi.size();
    }


    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                return 3;
        }
    }


    //titolo
    private class TitoloVH extends RecyclerView.ViewHolder {
        TextView titoloTv;
        ImageView starImg;

        private TitoloVH(View view) {
            super(view);
            titoloTv = (TextView) view.findViewById(R.id.item_titolo_titolo);
            starImg = (ImageView) view.findViewById(R.id.item_titolo_star_img);

        }
    }

    //fine titolo
    private class TestoVH extends RecyclerView.ViewHolder {
        TextView testo;

        public TestoVH(View view) {
            super(view);
            testo = (TextView) view.findViewById(R.id.item_testo_testo_tv);
        }
    }


    private class PrimaDataVH extends RecyclerView.ViewHolder {
        TextView data, dataNome;

        public PrimaDataVH(View view) {
            super(view);
            data = (TextView) view.findViewById(R.id.item_prima_data_testo_tv);
            dataNome = (TextView) view.findViewById(R.id.item_prima_data_nome_tv);


        }
    }

    private class DataVH extends RecyclerView.ViewHolder {
        TextView data, dataNome;

        public DataVH(View view) {
            super(view);
            data = (TextView) view.findViewById(R.id.item_data_testo_tv);
            dataNome = (TextView) view.findViewById(R.id.item_data_nome_tv);
        }
    }
}
