package aguzri.io.github.notesapp.view;

public interface EditorView {

    void showProgressDialog();
    void hideProgressDialog();
    void onRequestSuccess(String message);
    void onRequestError(String message);
}
