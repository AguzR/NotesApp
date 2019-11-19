package aguzri.io.github.notesapp.activity;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.thebluealliance.spectrum.SpectrumPalette;

import aguzri.io.github.notesapp.presenter.EditorPresenter;
import aguzri.io.github.notesapp.view.EditorView;
import retrofit2.Call;

import aguzri.io.github.notesapp.R;
import aguzri.io.github.notesapp.client.ApiClient;
import aguzri.io.github.notesapp.client.ApiInterface;
import aguzri.io.github.notesapp.model.Note;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorActivity extends AppCompatActivity implements EditorView {

    EditText et_title, et_note;
    ProgressDialog progressDialog;
    SpectrumPalette palette;

    ApiInterface apiInterface;
    EditorPresenter presenter;

    int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        et_title = findViewById(R.id.title);
        et_note = findViewById(R.id.note);
        palette = findViewById(R.id.palette);

        palette.setOnColorSelectedListener(
                clr -> color = clr
        );

        // Default color setup
        palette.setSelectedColor(getResources().getColor(R.color.white));
        palette.getResources().getColor(R.color.white);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        presenter = new EditorPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                // Save
                String title = et_title.getText().toString().trim();
                String note = et_note.getText().toString().trim();
                int color = this.color;

                if (title.isEmpty()) {
                    et_title.setError("Please enter a title");
                } else if (note.isEmpty()) {
                    et_note.setError("Please enter a title");
                } else {
                    presenter.saveNote(title, note, color);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void onAddSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onAddError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
