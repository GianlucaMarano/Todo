package com.marano.gianluca.todo.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marano.gianluca.todo.R;
import com.marano.gianluca.todo.activities.MainActivity;
import com.marano.gianluca.todo.activities.ViewActivity;
import com.marano.gianluca.todo.model.Nota;

import java.util.List;

/**
 * Created by Gianluca Marano on 20/02/2017.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NotaVH> {

    private List<Nota> note;
    private int posizione;
    private int request;
    public NoteAdapter(List<Nota> note) {
        this.note = note;
    }

    public void addNota(Nota nota) {
        note.add(nota);
        notifyItemInserted(getItemCount() - 1);
    }

    public int getRequest() {
        return request;
    }

    public void setRequest(int request) {
        this.request = request;
    }

    public void removeNota(int position) {
        note.remove(position);
        notifyItemRemoved(position);
    }

    public List<Nota> getNote() {
        return note;
    }

    public Nota getNota(int posizione) {
        return note.get(posizione);
    }

    public int getPosizione(Nota nota) {
        for (Nota n : note) {
            if (n.getTitolo().equals(nota.getTitolo()) & n.getCorpo().equals(nota.getCorpo())) {
                return note.indexOf(n);
            }
        }
        return -1;
    }

    public int getPosizione() {
        return posizione;
    }

    @Override
    public NotaVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NotaVH(view);
    }

    @Override
    public void onBindViewHolder(NotaVH holder, int position) {
        holder.titoloTv.setText(note.get(position).getTitolo());
        holder.testoTv.setText(note.get(position).getCorpo());
        holder.dataUltimaModificaTv.setText(note.get(position).getDataUltimaModifica());
        if (note.get(position).isSpeciale()) holder.star.setVisibility(View.VISIBLE);
        else holder.star.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onViewRecycled(NotaVH holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return note.size();
    }

    public class NotaVH extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {
        TextView titoloTv, testoTv, dataUltimaModificaTv;
        ImageView star;

        public NotaVH(View view) {
            super(view);
            titoloTv = (TextView) view.findViewById(R.id.item_titolo_nota_tv);
            testoTv = (TextView) view.findViewById(R.id.item_testo_tv);
            star = (ImageView) view.findViewById(R.id.item_star);
            dataUltimaModificaTv = (TextView) view.findViewById(R.id.item_data_ultima_modifica_tv);
            view.setOnCreateContextMenuListener(this);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), ViewActivity.class);
            i.putExtra("id", getAdapterPosition());
            i.putExtra(MainActivity.REQUEST, getRequest());
            v.getContext().startActivity(i);


        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuInflater inflater = ((MainActivity) v.getContext()).getMenuInflater();
            posizione = getAdapterPosition();
            inflater.inflate(R.menu.menu_note, menu);
            Log.d("createcontext", "lo faccio ");


        }
    }
}
