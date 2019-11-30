package aguzri.io.github.notesapp.view;

public interface AuthView {

    void showProgressDialog();
    void hideProgressDialog();
    void onGetUsers(String message, String idUser, String name, String email);
    void onRequestSuccess(String message);
    void onRequestError(String message);

}
