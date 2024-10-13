package ef1.conti.proyectofinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText notesInput;
    private Button configButton;
    private TextView notesTitle;
    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar elementos de la interfaz
        notesInput = findViewById(R.id.notes_input);
        configButton = findViewById(R.id.configButton);
        notesTitle = findViewById(R.id.notes_title);
        mainLayout = findViewById(R.id.main_layout); // Asegúrate de que el ID sea correcto en tu XML

        SharedPreferences sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
        String colorFondo = sharedPreferences.getString("backgroundColor", "#FFFFFF");

        // Configurar el botón para acceder a la configuración
        configButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    // Este método se llama cuando la actividad vuelve a ser visible
    @Override
    protected void onResume() {
        super.onResume();

        // Recuperar el color de fondo guardado
        SharedPreferences sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
        String colorFondo = sharedPreferences.getString("backgroundColor", "#FFFFFF"); // Color por defecto: blanco

        // Aplicar el color al fondo del layout principal
        try {
            if (colorFondo != null && !colorFondo.isEmpty()) {
                mainLayout.setBackgroundColor(Color.parseColor(colorFondo));
                Log.d("MainActivity", "Color de fondo aplicado: " + colorFondo);
            } else {
                Log.d("MainActivity", "No se encontró ningún color guardado, se usará blanco por defecto.");
                mainLayout.setBackgroundColor(Color.WHITE); // Color por defecto
            }
        } catch (IllegalArgumentException e) {
            Log.e("MainActivity", "El valor de color es inválido: " + colorFondo);
            mainLayout.setBackgroundColor(Color.WHITE); // Si el color es inválido, aplicar blanco
        }
    }
}

