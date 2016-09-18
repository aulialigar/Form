package id.sch.smktelkom_mlg.tugas01.xirpl4009.form;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etNama, etNo;
    //    RadioButton rbRPL,rbTKJ;
    RadioGroup rgAngkatan;
    CheckBox cbOsis, cbMpk, cbPustel;
    Spinner spKelas;
    Button bKirim;
    TextView tvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = (EditText) findViewById(R.id.editTextNama);
        etNo = (EditText) findViewById(R.id.editTextNo);
        cbOsis = (CheckBox) findViewById(R.id.checkBoxOsis);
        cbMpk = (CheckBox) findViewById(R.id.checkBoxMpk);
        cbPustel = (CheckBox) findViewById(R.id.checkBoxPustel);
        /*rbRPL = (RadioButton) findViewById(R.id.radioButtonRPL);
        rbTKJ = (RadioButton) findViewById(R.id.radioButtonTKJ);*/
        rgAngkatan = (RadioGroup) findViewById(R.id.radioGroupAngkatan);
        spKelas = (Spinner) findViewById(R.id.spinnerKelas);
        bKirim = (Button) findViewById(R.id.buttonKirim);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);

        bKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doClick();
            }
        });
    }

    private void doClick() {
        String hasil = null;
        String nama = etNama.getText().toString();
        String hasilcb = "Organisasi ";
        int startlen = hasilcb.length();

        if (isValid()) {

            int no = Integer.parseInt((etNo.getText().toString()));
            if (rgAngkatan.getCheckedRadioButtonId() != -1) {
                RadioButton rb = (RadioButton)
                        findViewById(rgAngkatan.getCheckedRadioButtonId());
                hasil = rb.getText().toString();
            }

            if (hasil == null) {
                tvHasil.setError("Belum memilih Angkatan");
            } else {
                if (cbOsis.isChecked()) hasilcb += cbOsis.getText().toString() + "\n";
                if (cbMpk.isChecked()) hasilcb += cbMpk.getText().toString() + "\n";
                if (cbPustel.isChecked()) hasilcb += cbPustel.getText().toString() + "\n";

                if (hasil.length() == startlen) hasil += "Tidak ada pada Pilihan";

                tvHasil.setText("Nama Anda " + nama + "\n\nNomor Absen " + no + "\n\nAngkatan " + hasil + "\n\n" + hasilcb + "\n\nKelas " + spKelas.getSelectedItem().toString());
            }
        }
    }

    /*private void doProcess() {
    }*/

    private boolean isValid() {
        boolean valid = true;

        String nama = etNama.getText().toString();
        String no = etNo.getText().toString();

        if (nama.isEmpty()) {
            etNama.setError("Nama belum diisi");
            valid = false;
        } else if (nama.length() < 3) {
            etNama.setError("Nama minimal 3 karakter");
            valid = false;
        } else {
            etNama.setError(null);
        }

        if (no.isEmpty()) {
            etNo.setError("No Absen belum diisi");
            valid = false;
        } else if (no.length() != 2) {
            etNo.setError("Format No Absen bukan xx");
            valid = false;
        } else {
            etNo.setError(null);
        }

        return valid;
    }
}
