package aguzri.io.github.notesapp.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import aguzri.io.github.notesapp.R;
import aguzri.io.github.notesapp.adapter.NoteAdapter;
import aguzri.io.github.notesapp.model.Note;
import aguzri.io.github.notesapp.presenter.MainPresenter;
import aguzri.io.github.notesapp.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView{

    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    MainPresenter presenter;
    NoteAdapter adapter;
    NoteAdapter .ItemClickListener itemClickListener;

    List<Note> note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.add);
        recyclerView = findViewById(R.id.recyler_view);
        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab.setOnClickListener(view ->
                startActivity(new Intent(this, EditorActivity.class))
        );

        presenter = new MainPresenter(this);
        presenter.getData();

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getData()
        );

        itemClickListener = (((view, position) -> {
            String title = note.get(position).getTitle();
            Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
        }));
    }

    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onGetResult(List<Note> notes) {
        adapter = new NoteAdapter(this, notes, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        note = notes;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
