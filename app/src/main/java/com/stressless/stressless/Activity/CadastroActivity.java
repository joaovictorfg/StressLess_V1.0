package com.stressless.stressless.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.stressless.stressless.DAO.ConfiguraFirebase;
import com.stressless.stressless.Helper.Base64Custom;
import com.stressless.stressless.Helper.Preferencias;
import com.stressless.stressless.R;
import com.stressless.stressless.Users.Usuarios;

public class CadastroActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editSobre;
    private EditText editTel;
    private EditText editEmail2;
    private EditText editSenha2;
    private EditText editConfSenha;
    private Button btnRegistrar;
    private Button btnVoltar;
    private Usuarios usuarios;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editNome = (EditText) findViewById(R.id.editNome);
        editSobre = (EditText) findViewById(R.id.editSobre);
        editTel = (EditText) findViewById(R.id.editTel);
        editEmail2 = (EditText) findViewById(R.id.editEmail2);
        editSenha2 = (EditText) findViewById(R.id.editSenha2);
        editConfSenha = (EditText) findViewById(R.id.editConfSenha);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editSenha2.getText().toString().equals(editConfSenha.getText().toString())){
                    usuarios =  new Usuarios();
                    usuarios.setNome(editNome.getText().toString());
                    usuarios.setSobrenome(editSobre.getText().toString());
                    usuarios.setEmail(editEmail2.getText().toString());
                    usuarios.setSenha(editSenha2.getText().toString());
                    usuarios.setTelefone(editTel.getText().toString());

                    cadastraUsuario();
                }else {
                    Toast.makeText(CadastroActivity.this, "As senhas não coreespondem!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }

    public void cadastraUsuario() {
        autenticacao = ConfiguraFirebase.getAutenticacao();
        autenticacao.createUserWithEmailAndPassword(usuarios.getEmail(), usuarios.getSenha()).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CadastroActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                    String identificadorUsuario = Base64Custom.codificaBase64(usuarios.getEmail());
                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuarios.setId(identificadorUsuario);
                    usuarios.salvar();

                    Preferencias preferencias = new Preferencias(CadastroActivity.this);
                    preferencias.salvarPreferencias(identificadorUsuario, usuarios.getNome());
                    abrirLogin();
                } else {
                    String erro = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        erro = "Digite uma senha contendo 8 caracteres, sendo letras ou números!";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erro = "E-mail digitado inválido, digite um novo E-mail por favor!";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erro = "E-mail já cadastrado, digite outro E-mail por favor!";
                    } catch (Exception e) {
                        erro = "Erro ao efetuar o cadastro, desculpe!";
                        e.printStackTrace();
                    }
                        Toast.makeText(CadastroActivity.this, "Erro!" + erro, Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        public void abrirLogin() {
            Intent i = new Intent(CadastroActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
    }
}

