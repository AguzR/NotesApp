package aguzri.io.github.notesapp.view;

import java.util.List;

import aguzri.io.github.notesapp.model.Note;

public interface MainView {

    void showLoading();
    void hideLoading();
    void onGetResult(List<Note> note);
    void onErrorLoading(String message);

}
