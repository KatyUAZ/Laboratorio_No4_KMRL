package com.example.laboratorio_no5_kmrl;

import android.Manifest;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.NumberFormat;

public class SecondActivity extends AppCompatActivity {

    //declaracion de variables
    private Button btnEnviar;
    private String mensaje;
    RadioButton rdbC;
    RadioButton rdbM;
    RadioButton rdbG;
    RadioButton rdbR;
    RadioButton rdbV;
    CheckBox checkLimon;
    CheckBox checkCebolla;
    CheckBox checkRepollo;
    CheckBox checkRabanos;

    EditText txtCant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //numero del contacto que recibira el pedido
        String tel = "527838401445";

        //se almacena el mensaje que se va enviar
        mensaje = getMensaje();

        //se asigna el boton correspondiente
        btnEnviar = (Button) findViewById(R.id.btnPedido);

        ActivityCompat.requestPermissions(SecondActivity.this, new String[]{Manifest.permission.SEND_SMS},1);

        //acciones al dar click en ell boton
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(SecondActivity.this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(SecondActivity.this, new String[]{Manifest.permission.SEND_SMS},1);
                }
                mensaje= getMensaje();

                //Abre el contacto listo para mandar el mensaje con el pedido
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                String uri = "whatsapp://send?phone="+tel+"&text="+mensaje;
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });

    }

    /**
     * Metodo que permite obtener el mensaje que se enviará
     * @return
     */
    private String getMensaje() {
        //mensaje de entrada
        String mensaje = "Buen dia! \n"+
                "El pedido de pozole es el siguiente: ";

        //recursos correspondientes
        txtCant = (EditText) findViewById(R.id.txtNumCant);
        rdbC = (RadioButton) findViewById(R.id.rdbC);
        rdbM = (RadioButton) findViewById(R.id.rdbM);
        rdbG = (RadioButton) findViewById(R.id.rdbG);
        rdbR = (RadioButton) findViewById(R.id.rdbR);
        rdbV = (RadioButton) findViewById(R.id.rdbV);
        checkLimon = (CheckBox) findViewById(R.id.checkLimon);
        checkCebolla = (CheckBox) findViewById(R.id.checkCebolla);
        checkRepollo = (CheckBox) findViewById(R.id.checkRepollo);
        checkRabanos = (CheckBox) findViewById(R.id.checkRabano);

        //para capturar la cantidad de pozoles
        mensaje = mensaje + "\n\nCantidad de Pozoles= "+ txtCant.getText().toString();

        //Para capturar tamaño
        if(rdbC.isChecked()){
            mensaje = mensaje +"\n\nTamaño de pozole: Chico";
        }else if (rdbM.isChecked()){
            mensaje = mensaje +"\n\nTamaño de pozole: Mediano";
        }else if(rdbG.isChecked()){
            mensaje = mensaje +"\n\nTamaño de pozole: Grande";
        }

        //para capturar tipo de pozole
        if (rdbR.isChecked()){
            mensaje = mensaje + "\n\nTipo de pozole: Rojo";
        }else if (rdbV.isChecked()){
            mensaje = mensaje + "\n\nTipo de pozole: Verde";
        }

        //para capturar los ingredientes adicionales
        mensaje = mensaje + "\n\nIngredientes Adicionales:";

        if(checkLimon.isChecked()  ){
            mensaje = mensaje + "\nLimón";
        }

        if(checkCebolla.isChecked()){
            mensaje = mensaje + "\nCebolla";
        }

        if(checkRepollo.isChecked()){
            mensaje = mensaje + "\nRepollo";
        }

        if(checkRabanos.isChecked()){
            mensaje = mensaje + "\nRabanos";
        }

        mensaje = mensaje + "\n\nGracias!";
        return mensaje;
    }
}