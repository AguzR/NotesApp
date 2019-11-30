package aguzri.io.github.notesapp.presenter;

import android.support.annotation.NonNull;

import aguzri.io.github.notesapp.client.ApiClient;
import aguzri.io.github.notesapp.client.ApiInterface;
import aguzri.io.github.notesapp.model.Users;
import aguzri.io.github.notesapp.utils.SessionManager;
import aguzri.io.github.notesapp.view.AuthView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthPresenter {

    AuthView view;
    String idUser, name, email;

    public AuthPresenter(AuthView view) {
        this.view = view;
    }

    public void Login(String email, String password) {
        view.showProgressDialog();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Users> call = apiInterface.Login(email, password);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(@NonNull Call<Users> call, @NonNull Response<Users> response) {
                view.hideProgressDialog();

                if (response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onGetUsers(
                                response.body().getMessage(),
                                response.body().getId(),
                                response.body().getName(),
                                response.body().getEmail());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Users> call, @NonNull Throwable t) {
                view.hideProgressDialog();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    public void Regist(String name, String email, String password) {
        view.showProgressDialog();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Users> call = apiInterface.Regist(name, email, password);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(@NonNull Call<Users> call, @NonNull Response<Users> response) {
                view.hideProgressDialog();

                if (response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Users> call, @NonNull Throwable t) {
                view.hideProgressDialog();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }
}
