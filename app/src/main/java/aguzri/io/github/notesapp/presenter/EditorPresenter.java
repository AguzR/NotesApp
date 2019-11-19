package aguzri.io.github.notesapp.presenter;

import android.support.annotation.NonNull;
import android.widget.Toast;

import aguzri.io.github.notesapp.activity.EditorActivity;
import aguzri.io.github.notesapp.client.ApiClient;
import aguzri.io.github.notesapp.client.ApiInterface;
import aguzri.io.github.notesapp.model.Note;
import aguzri.io.github.notesapp.view.EditorView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorPresenter {

    private EditorView view;

    public EditorPresenter(EditorView view) {
        this.view = view;
    }

    public void saveNote(final String title, final String note, final int color) {
        view.showProgressDialog();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Note> call = apiInterface.createNote(title, note, color);

        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(@NonNull Call<Note> call, @NonNull Response<Note> response) {
                view.hideProgressDialog();

                if (response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onAddSuccess(response.body().getMessage());
                    } else {
                        view.onAddError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Note> call, @NonNull Throwable t) {
                view.hideProgressDialog();
                view.onAddError(t.getLocalizedMessage());
            }
        });

    }
}
