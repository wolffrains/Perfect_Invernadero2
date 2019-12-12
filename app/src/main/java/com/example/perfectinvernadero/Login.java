package com.example.perfectinvernadero;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity {

    Button siguiente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        siguiente = (Button) findViewById(R.id.ButtonLogearse);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent siguiente = new Intent(Login.this, MainActivity.class);
                startActivity(siguiente);
            }
        });
    }
        public void ejecutar_magnetico(View view){
            Intent m = new Intent(this, magnetico.class);
            startActivity(m);
        }

        public void ejecutar_acerca(View view){

            Intent o =new Intent(this, AcercaDe.class);

            startActivity(o);
        }
        public void ejecutar_proximidad(View view){
            Intent p = new Intent(this, proximidad.class);
            startActivity(p);
        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu,menu);

            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle item selection
            switch (item.getItemId()) {
                case R.id.item1:
                    ejecutar_magnetico(null);
                    return true;
                case R.id.item2:
                    ejecutar_acerca(null);
                    return true;
                case R.id.item3:
                    ejecutar_proximidad(null);
                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }
    }
}
