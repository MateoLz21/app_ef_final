package ef1.conti.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    EditText passwordInput,userInput;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userInput = findViewById(R.id.usuarioInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = userInput.getText().toString();
                String password = passwordInput.getText().toString();
                if (checkUser(usuario) && checkPassword(password)) {
                    // Si la contraseña es correcta, ir a la configuración
                    Intent intent = new Intent(LoginActivity.this, ConfigActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkPassword(String password) {
        // Puedes cambiar esta lógica según tus necesidades
        return password.equals("1234"); // Contraseña fija como ejemplo
    }

    private boolean checkUser(String user) {
        // Puedes cambiar esta lógica según tus necesidades
        return user.equals("abcd"); // Usuario fija como ejemplo
    }
}