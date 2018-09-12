package br.edu.ifsp.scl.sdm.layoutssdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String ESTADO_NOTIFICACAO_CHECKBOX = "ESTADO_NOTIFICACAO_CHECKBOX";
    private final String NOTIFICACAO_RADIOBUTTON_SELECIONADO = "NOTIFICACAO_RADIOBUTTON_SELECIONADO";

    private final String EMAIL_VIEWS = "EMAIL_VIEWS";
    private final String TELEFONE_VIEWS = "TELEFONE_VIEWS";

    private CheckBox notificacoesCheckBox;
    private RadioGroup notificacoesRadioGroup;

    private EditText nomeEditText;

    private LinearLayout telefoneLinearLayout;
    private ArrayList<Contato> telefoneArrayList;

    private LinearLayout emailLinearLayout;
    private ArrayList<Contato> emailArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_view_activity_main);

        notificacoesCheckBox = findViewById(R.id.notificacoesCheckBox);
        notificacoesRadioGroup = findViewById(R.id.notificacoesRadioGroup);

        nomeEditText = findViewById(R.id.nomeEditText);

        emailLinearLayout = findViewById(R.id.emailLinearLayout);
        telefoneLinearLayout = findViewById(R.id.telefoneLinearLayout);

        telefoneArrayList = new ArrayList<>();
        emailArrayList = new ArrayList<>();

        if(savedInstanceState != null) {
            emailLinearLayout.removeAllViewsInLayout();
            emailArrayList = (ArrayList<Contato>) savedInstanceState.getSerializable(EMAIL_VIEWS);
            for(int i = 0; i < emailArrayList.size(); i++) {
                adicionarEmail(null);
                ((EditText) emailLinearLayout.getChildAt(i).findViewById(R.id.emailEditText)).setText(emailArrayList.get(i).getEmail());
            }

            telefoneLinearLayout.removeAllViewsInLayout();
            emailArrayList = (ArrayList<Contato>) savedInstanceState.getSerializable(TELEFONE_VIEWS);
            for(int i = 0; i < emailArrayList.size(); i++) {
                adicionarTelefone(null);
                ((EditText) telefoneLinearLayout.getChildAt(i).findViewById(R.id.telefoneEditText)).setText(telefoneArrayList.get(i).getTelefone());
                ((Spinner) telefoneLinearLayout.getChildAt(i).findViewById(R.id.tipoTelefoneSpinner)).setSelection(telefoneArrayList.get(i).getTipoTelefone());
            }
        }

        // Tratando evento de check no checkbox
        notificacoesCheckBox.setOnCheckedChangeListener(checkListener);
    }

    public void adicionarTelefone(View view) {
        //if(view.getId() == R.id.adicionarTelefoneButton) {
            View novoTelefoneLayout = getLayoutInflater().inflate(R.layout.novo_telefone_layout, null);
            telefoneLinearLayout.addView(novoTelefoneLayout);
        //}
    }

    public void adicionarEmail(View view) {
        //if(view.getId() == R.id.adicionarEmailButton) {
            View novoEmailLayout = getLayoutInflater().inflate(R.layout.novo_email_layout, null);
            emailLinearLayout.addView(novoEmailLayout);
        //}
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ESTADO_NOTIFICACAO_CHECKBOX, notificacoesCheckBox.isChecked());
        outState.putInt(NOTIFICACAO_RADIOBUTTON_SELECIONADO, notificacoesRadioGroup.getCheckedRadioButtonId());

        emailArrayList.clear();
        telefoneArrayList.clear();

        for(int i = 0; i < emailLinearLayout.getChildCount(); i++) {
            Contato email = new Contato();
            EditText emailEditText = emailLinearLayout.getChildAt(i).findViewById(R.id.emailEditText);
            email.setEmail(emailEditText.getText().toString());
            emailArrayList.add(email);
        }

        for(int i = 0; i < telefoneLinearLayout.getChildCount(); i++) {
            Contato telefone = new Contato();
            EditText telefoneEditText = telefoneLinearLayout.getChildAt(i).findViewById(R.id.telefoneEditText);
            Spinner tipoTelefoneSpinner = telefoneLinearLayout.getChildAt(i).findViewById(R.id.tipoTelefoneSpinner);
            telefone.setTelefone(telefoneEditText.getText().toString());
            telefone.setTipoTelefone(tipoTelefoneSpinner.getSelectedItemPosition());
            telefoneArrayList.add(telefone);
        }

        outState.putSerializable(EMAIL_VIEWS, emailArrayList);
        outState.putSerializable(TELEFONE_VIEWS, telefoneArrayList);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        notificacoesCheckBox.setChecked(savedInstanceState.getBoolean(ESTADO_NOTIFICACAO_CHECKBOX, false));
        int idRadioButtonSelecionado = savedInstanceState.getInt(NOTIFICACAO_RADIOBUTTON_SELECIONADO);
        if(idRadioButtonSelecionado != -1) {
            notificacoesRadioGroup.check(idRadioButtonSelecionado);
        }

        if(emailLinearLayout.getChildCount() > 0) {
            emailArrayList = (ArrayList<Contato>) savedInstanceState.getSerializable(EMAIL_VIEWS);
            if(!emailArrayList.isEmpty()) {
                for(int i = 0; i < emailArrayList.size(); i++) {
                    ((EditText) emailLinearLayout.getChildAt(i).findViewById(R.id.emailEditText)).setText(emailArrayList.get(i).getEmail());
                }
            }
        }

        if(telefoneLinearLayout.getChildCount() > 0) {
            telefoneArrayList = (ArrayList<Contato>) savedInstanceState.getSerializable(TELEFONE_VIEWS);
            if (!telefoneArrayList.isEmpty()) {
                for (int i = 0; i < telefoneArrayList.size(); i++) {
                    ((EditText) telefoneLinearLayout.getChildAt(i).findViewById(R.id.telefoneEditText)).setText(telefoneArrayList.get(i).getTelefone());
                    ((Spinner) telefoneLinearLayout.getChildAt(i).findViewById(R.id.tipoTelefoneSpinner)).setSelection(telefoneArrayList.get(i).getTipoTelefone());
                }
            }
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
        //limpa os arrays com as views dinâmicas
        emailArrayList.clear();
        telefoneArrayList.clear();

        //limpa o checkbox e o radio group
        notificacoesCheckBox.setChecked(false);
        notificacoesRadioGroup.clearCheck();
    }

}