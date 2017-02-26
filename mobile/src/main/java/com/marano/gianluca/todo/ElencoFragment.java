package com.marano.gianluca.todo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marano.gianluca.todo.activities.MainActivity;
import com.marano.gianluca.todo.adapters.NoteAdapter;
import com.marano.gianluca.todo.controllers.Databasehandler;
import com.marano.gianluca.todo.model.Nota;

import java.util.List;

/**
 * Created by Gianluca Marano on 25/02/2017.
 */

public class ElencoFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    NoteAdapter noteAdapter;
    Databasehandler db;
    List<Nota> note;

    public ElencoFragment() {
        // Required empty public constructor
    }


    public NoteAdapter getNoteAdapter() {
        return noteAdapter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_elenco, container, false);
        db = new Databasehandler(getActivity());
        if (getArguments().getInt(MainActivity.REQUEST) == MainActivity.ELENCO_SEMPLICE) {
            note = db.getAllNotas();
        } else {
            note = db.getSpecialNotas();
        }

        recyclerView = (RecyclerView) root.findViewById(R.id.main_recycler);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        noteAdapter = new NoteAdapter(note);
        noteAdapter.setRequest(getArguments().getInt(MainActivity.REQUEST));
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(noteAdapter);
        registerForContextMenu(recyclerView);
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}

