package aguzri.io.github.notesapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import aguzri.io.github.notesapp.R;
import aguzri.io.github.notesapp.presenter.AuthPresenter;
import aguzri.io.github.notesapp.utils.SessionManager;
import aguzri.io.github.notesapp.view.AuthView;
import aguzri.io.github.notesapp.view.EditorView;

public class LoginActivity extends AppCompatActivity implements AuthView{

    EditText et_email, et_password;
    TextView tv_regist;
    Button btn_login;
    AuthPresenter presenter;
    ProgressDialog progressDialog;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        tv_regist = findViewById(R.id.tv_regist);
        btn_login = findViewById(R.id.btn_login);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        presenter = new AuthPresenter(this);

        tv_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();

                if (email.isEmpty()) {
                    et_email.setError("Email cannot be empty!");
                } else if (password.isEmpty()) {
                    et_password.setError("Password cannot be empty!");
                } else {
                    presenter.Login(email, password);
                }
            }
        });
    }


    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void onGetUsers(String idUser, String name, String email) {
        sessionManager.createSession(idUser, name, email);
    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
