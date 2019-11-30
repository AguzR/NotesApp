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
import aguzri.io.github.notesapp.view.AuthView;
import aguzri.io.github.notesapp.view.EditorView;

public class RegisterActivity extends AppCompatActivity implements AuthView{

    TextView tv_login;
    EditText et_name, et_email, et_password, et_cpassword;
    Button btn_regist;
    AuthPresenter presenter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tv_login = findViewById(R.id.tv_login);
        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_cpassword = findViewById(R.id.et_cpassword);
        btn_regist = findViewById(R.id.btn_register);

        presenter = new AuthPresenter(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait ... ");

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String cpassword = et_cpassword.getText().toString().trim();

                if (name.isEmpty()) {
                    et_name.setError("Please insert your name!");
                } else if (email.isEmpty()) {
                    et_email.setError("Please insert your email address!");
                } else if (password.isEmpty()) {
                    et_password.setError("Please insert your password!");
                } else if (cpassword.isEmpty()) {
                    et_password.setError("Please insert your password!");
                } else if (!cpassword.equals(password)) {
                    et_cpassword.setError("Your password doesn't match!");
                } else {
                    presenter.Regist(name, email, password);
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
    public void onRequestSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onGetUsers(String message, String idUser, String name, String email) {
        // Only Login is Here
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
