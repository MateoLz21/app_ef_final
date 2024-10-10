package ef1.conti.proyectofinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Elementos de la interfaz
    private EditText notesInput;
    private Button configButton;
    private TextView notesTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Asegúrate de que el layout se llama activity_main.xml

        // Inicializar elementos de la interfaz
        notesInput = findViewById(R.id.notes_input);
        configButton = findViewById(R.id.configButton);
        notesTitle = findViewById(R.id.notes_title);

        // Configurar el botón para acceder a la configuración
        configButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el estado de autenticación de SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
                boolean isAuthEnabled = sharedPreferences.getBoolean("authEnabled", false);

                if (isAuthEnabled) {
                    // Si la autenticación está activada, redirige a LoginActivity
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out); // Aplicar animaciones
                } else {
                    // Si la autenticación está desactivada, redirige directamente a ConfigActivity
                    Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out); // Aplicar animaciones
                }
            }
        });
    }
}

