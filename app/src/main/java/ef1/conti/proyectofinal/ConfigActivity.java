
package ef1.conti.proyectofinal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ConfigActivity extends AppCompatActivity {

    Switch authSwitch;
    EditText txtColor;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        authSwitch = findViewById(R.id.authSwitch);
        txtColor = findViewById(R.id.txtColor);
        saveButton = findViewById(R.id.button); // Botón para registrar usuario o guardar el color

        SharedPreferences sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE);

        // Cargar el estado de autenticación desde SharedPreferences
        authSwitch.setChecked(sharedPreferences.getBoolean("authEnabled", false));

        // Configurar el Switch para habilitar/deshabilitar la autenticación
        authSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("authEnabled", isChecked);
            editor.apply();
        });

        // Cargar el color de fondo guardado previamente, si existe
        String savedColor = sharedPreferences.getString("backgroundColor", "#FFFFFF");
        txtColor.setText(savedColor);

        // Guardar el color de fondo al hacer clic en el botón "Guardar"
        saveButton.setOnClickListener(v -> {
            String color = txtColor.getText().toString().trim();

            if (!color.isEmpty()) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("backgroundColor", color); // Guardar el color en SharedPreferences
                editor.apply();

                // Mostrar mensaje de confirmación al usuario
                Toast.makeText(ConfigActivity.this, "Color de fondo actualizado a: " + color, Toast.LENGTH_SHORT).show();
            } else {
                // Mostrar mensaje si no se ingresa un color válido
                Toast.makeText(ConfigActivity.this, "Por favor ingrese un color válido", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
