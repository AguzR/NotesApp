package aguzri.io.github.notesapp.view;

public interface EditorView {

    void showProgressDialog();
    void hideProgressDialog();
    void onAddSuccess(String message);
    void onAddError(String message);
}
