package com.example.paulo.consumocombustivel;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TextView txtPrecoGasolina;
    private TextView txtPrecoEtanol;
    private EditText txtResultado;
    private SeekBar sbPrecoGasolina;
    private SeekBar sbPrecoEtanol;
    private ImageView imagemCombustivel;
    //private static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static DecimalFormat decimalFormat = new DecimalFormat("0,00");
    private static DecimalFormat decimalFormat2 = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPrecoGasolina = (TextView) findViewById(R.id.txt_preco_gasolina);
        txtPrecoEtanol = (TextView) findViewById(R.id.txt_preco_alcool);
        txtResultado = (EditText) findViewById(R.id.edit_resultado) ;
        sbPrecoGasolina = findViewById(R.id.sb_preco_gasolina);
        sbPrecoEtanol = findViewById(R.id.sb_preco_alcool);
        imagemCombustivel = findViewById(R.id.iv_combustivel);

        sbPrecoGasolina.setOnSeekBarChangeListener(new ObservadorDaSeekBar());
        sbPrecoEtanol.setOnSeekBarChangeListener(new ObservadorDaSeekBar());
    }

    private class ObservadorDaSeekBar implements SeekBar.OnSeekBarChangeListener{

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(seekBar.equals(sbPrecoGasolina))
                txtPrecoGasolina.setText(decimalFormat.format(progress));
            if(seekBar.equals(sbPrecoEtanol))
                txtPrecoEtanol.setText(decimalFormat.format(progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            VerificaResultado();
        }
    }

    private void VerificaResultado(){
        double consumoGasolina = sbPrecoGasolina.getProgress();
        double consumoEtanol = sbPrecoEtanol.getProgress();
        double total = (consumoEtanol/consumoGasolina);

        if(total >= 0.7) {
            //txtResultado.setText("Gasolina razao = " + decimalFormat2.format(total ));
            txtResultado.setText(R.string.txt_resultado_combustivel_1);
            //imagemCombustivel.setImageDrawable(R.drawable.gasolina2);
            imagemCombustivel.setImageResource(R.drawable.gasolina);
        }
        else {
            //txtResultado.setText("Etanol razao = " + decimalFormat2.format(total));
            txtResultado.setText(R.string.txt_resultado_combustivel_2);
            imagemCombustivel.setImageResource(R.drawable.etanol);
        }
    }
}
