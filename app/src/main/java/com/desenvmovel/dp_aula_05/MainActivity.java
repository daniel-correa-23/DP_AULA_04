package com.desenvmovel.dp_aula_05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private NumberPicker mNumberPicker;
    private RadioGroup mRadioGroup;
    private RadioButton mRadAdicionar, mRadExcluir;
    private EditText mEditText;
    private TextView  mTextView, mTextView2;
    private Button mButton;

    public static final String MINHA_PREF = "Pref_Daniel_Correa";
    public static final int ANO_MIN = 2015;
    public static final int ANO_MAX = 2020;
    public static final int ZERO = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNumberPicker = findViewById(R.id.numberPicker);
        mNumberPicker.setMinValue(ANO_MIN);
        mNumberPicker.setMaxValue(ANO_MAX);

        mNumberPicker.setOnValueChangedListener(alterarDataListener);

        mRadioGroup = findViewById(R.id.idRadioGroup);
        mRadAdicionar = findViewById(R.id.idRadioButtonAdicionar);
        mRadExcluir = findViewById(R.id.idRadioButtonExcluir);
        mEditText = findViewById(R.id.idEditText);
        mTextView = findViewById(R.id.idTextView);
        mTextView2 = findViewById(R.id.idTextView2);
        mButton = findViewById(R.id.idButtonConfirmar);

        mButton.setOnClickListener(mButtonConfirmarClick);

        exibirSaldo(mNumberPicker.getValue());


    }
    private NumberPicker.OnValueChangeListener alterarDataListener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            exibirSaldo(newVal);
            mTextView2.setText("");
        }
    };

    private Button.OnClickListener mButtonConfirmarClick = new Button.OnClickListener(){
        @Override
        public void onClick(View view){
           if(!mEditText.getText().toString().isEmpty()){
               float valor = Float.parseFloat(mEditText.getText().toString());
               int ano = mNumberPicker.getValue();
               switch (mRadioGroup.getCheckedRadioButtonId()){
                   case R.id.idRadioButtonAdicionar:
                       adicionarValor(ano,valor);
                       mTextView2.setText("Adicionado do ano:" +ano+" o valor de " + String.format("R$ %.2f",valor));
                       break;
                   case R.id.idRadioButtonExcluir:
                        excluirValor(ano,valor);
                        mTextView2.setText("Excluído do ano:" + ano +" o valor de " + String.format("R$ %.2f",valor));
                       break;
                   default:
                       mTextView2.setText("Marque uma opção Adicionar ou Excluir!!!");

               }
               exibirSaldo(ano);
           }
        }
    };

    private void adicionarValor(int ano, float valor){
        SharedPreferences sharedPreferences = getSharedPreferences(MINHA_PREF, Context.MODE_PRIVATE);
        float valorAtual = sharedPreferences.getFloat(String.valueOf(ano),ZERO);
        float novoValor = valorAtual + valor;
        sharedPreferences.edit().putFloat(String.valueOf(ano), novoValor).apply();
        mEditText.setText("");
    }
    private void excluirValor(int ano, float valor){
        SharedPreferences sharedPreferences = getSharedPreferences(MINHA_PREF, Context.MODE_PRIVATE);
        float valorAtual = sharedPreferences.getFloat(String.valueOf(ano),ZERO);
        float novoValor = valorAtual - valor;
        if (novoValor < 0) {
            novoValor = 0;
        }
        sharedPreferences.edit().putFloat(String.valueOf(ano), novoValor).apply();
        mEditText.setText("");
    }
    private void exibirSaldo(int ano){
        SharedPreferences sharedPreferences = getSharedPreferences(MINHA_PREF, Context.MODE_PRIVATE);
        float saldo = sharedPreferences.getFloat(String.valueOf(ano), 0);
        mTextView.setText(String.format("R$ %.2f",saldo));
    }
}