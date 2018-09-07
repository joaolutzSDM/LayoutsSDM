package br.edu.ifsp.scl.sdm.layoutssdm;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String ESTADO_NOTIFICACAO_CHECKBOX = "ESTADO_NOTIFICACAO_CHECKBOX";
    private final String NOTIFICACAO_RADIOBUTTON_SELECIONADO = "NOTIFICACAO_RADIOBUTTON_SELECIONADO";

    private CheckBox notificacoesCheckBox;
    private RadioGroup notificacoesRadioGroup;

    private EditText nomeEditText;
    private EditText emailEditText;
    private EditText telefoneEditText;

    private LinearLayout telefoneLinearLayout;
    private ArrayList<View> telefoneArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_view_activity_main);

        notificacoesCheckBox = findViewById(R.id.notificacoesCheckBox);
        notificacoesRadioGroup = findViewById(R.id.notificacoesRadioGroup);

        nomeEditText = findViewById(R.id.nomeEditText);
        emailEditText = findViewById(R.id.emailEditText);
        //telefoneEditText = findViewById(R.id.telefoneEditText);
        telefoneLinearLayout = findViewById(R.id.telefoneLinearLayout);

        telefoneArrayList = new ArrayList<>();

        // Tratando evento de check no checkbox
        notificacoesCheckBox.setOnCheckedChangeListener(checkListener);
    }

    public void adicionarTelefone(View view) {
        if(view.getId() == R.id.adicionarTelefoneButton) {
            LayoutInflater layoutInflater = getLayoutInflater();

            View novoTelefoneLayout = layoutInflater.inflate(R.layout.novo_telefone_layout, null);
            telefoneArrayList.add(novoTelefoneLayout);
            telefoneLinearLayout.addView(novoTelefoneLayout);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ESTADO_NOTIFICACAO_CHECKBOX, notificacoesCheckBox.isChecked());
        outState.putInt(NOTIFICACAO_RADIOBUTTON_SELECIONADO, notificacoesRadioGroup.getCheckedRadioButtonId());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        notificacoesCheckBox.setChecked(savedInstanceState.getBoolean(ESTADO_NOTIFICACAO_CHECKBOX, false));
        int idRadioButtonSelecionado = savedInstanceState.getInt(NOTIFICACAO_RADIOBUTTON_SELECIONADO);
        if(idRadioButtonSelecionado != -1) {
            notificacoesRadioGroup.check(idRadioButtonSelecionado);
        }

    }

    private CompoundButton.OnCheckedChangeListener checkListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked) {
                    notificacoesRadioGroup.setVisibility(View.VISIBLE);
                } else {
                    notificacoesRadioGroup.setVisibility(View.GONE);
                }
        }
    };

    public void limparFormulario(View view) {
        nomeEditText.setText("");
        emailEditText.setText("");
        telefoneEditText.setText("");

        //limpa o checkbox e o radio group
        notificacoesCheckBox.setChecked(false);
        notificacoesRadioGroup.clearCheck();
    }


}
