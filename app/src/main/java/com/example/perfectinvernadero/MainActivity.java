package com.example.perfectinvernadero;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    Button next;
    private EditText txtCodigo;
    private EditText txtNombre;
    private TextView txtResultado;

    private Button btnInsertar;
    private Button btnActualizar;
    private Button btnEliminar;
    private Button btnListar;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtenemos las referencias a los controles
        txtCodigo = findViewById(R.id.txtReg);
        txtNombre = findViewById(R.id.txtVal);
        txtResultado = findViewById(R.id.txtResultado);

        btnInsertar = findViewById(R.id.btnInsertar);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnListar = findViewById(R.id.btnListar);

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        UsuariosSQLiteHelper usdbh =
                new UsuariosSQLiteHelper(this, "DBUsuarios", null, 1);

        db = usdbh.getWritableDatabase();
        next = (Button) findViewById(R.id.buttonMapa);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next = new Intent(MainActivity.this, Mapa.class);
                startActivity(next);
            }
        });

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Recuperamos los valores de los campos de texto
                String cod = txtCodigo.getText().toString();
                String nom = txtNombre.getText().toString();

                //Alternativa 1: método sqlExec()
                //String sql = "INSERT INTO Usuarios (codigo,nombre) VALUES ('" + cod + "','" + nom + "') ";
                //db.execSQL(sql);

                //Alternativa 2: método insert()
                ContentValues nuevoRegistro = new ContentValues();
                nuevoRegistro.put("codigo", cod);
                nuevoRegistro.put("nombre", nom);
                db.insert("Usuarios", null, nuevoRegistro);
                limpiarCampos();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Recuperamos los valores de los campos de texto
                String cod = txtCodigo.getText().toString();
                String nom = txtNombre.getText().toString();

                //Alternativa 1: método sqlExec()
                //String sql = "UPDATE Usuarios SET nombre='" + nom + "' WHERE codigo=" + cod;
                //db.execSQL(sql);

                //Alternativa 2: método update()
                ContentValues valores = new ContentValues();
                valores.put("nombre", nom);
                db.update("Usuarios", valores, "codigo=" + cod, null);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Recuperamos los valores de los campos de texto
                String cod = txtCodigo.getText().toString();

                //Alternativa 1: método sqlExec()
                //String sql = "DELETE FROM Usuarios WHERE codigo=" + cod;
                //db.execSQL(sql);

                //Alternativa 2: método delete()
                db.delete("Usuarios", "codigo=" + cod, null);
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Alternativa 1: método rawQuery()
                Cursor c = db.rawQuery("SELECT codigo, nombre FROM Usuarios", null);

                //Alternativa 2: método delete()
                //String[] campos = new String[] {"codigo", "nombre"};
                //Cursor c = db.query("Usuarios", campos, null, null, null, null, null);

                //Recorremos los resultados para mostrarlos en pantalla
                txtResultado.setText("");
                if (c.moveToFirst()) {
                    //Recorremos el cursor hasta que no haya más registros
                    do {
                        String cod = c.getString(0);
                        String nom = c.getString(1);

                        txtResultado.append(" " + cod + " - " + nom + "\n");
                    } while(c.moveToNext());
                }

            }
        });
    }

    void limpiarCampos(){
        txtCodigo.setText("");
        txtNombre.setText("");


    }
}