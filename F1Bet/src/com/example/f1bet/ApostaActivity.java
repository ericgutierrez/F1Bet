package com.example.f1bet;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.f1bet.bbdd.ApostesConversor;
import com.example.f1bet.bbdd.ApostesSQL;
import com.example.f1bet.constructors.Aposta;

public class ApostaActivity extends Activity {

	private Spinner pr, sn, tr, qt, ce, vr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aposta);

		pr = (Spinner) this.findViewById(R.id.primer);
		sn = (Spinner) this.findViewById(R.id.segon);
		tr = (Spinner) this.findViewById(R.id.tercer);
		qt = (Spinner) this.findViewById(R.id.quart);
		ce = (Spinner) this.findViewById(R.id.cinque);
		vr = (Spinner) this.findViewById(R.id.voltarapida);

		// Adaptador dels spinners
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.pilots, R.layout.spinner_style);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Poso un adaptador als Spinners per convertir el color i la lletra.
		pr.setAdapter(adapter);
		sn.setAdapter(adapter);
		tr.setAdapter(adapter);
		qt.setAdapter(adapter);
		ce.setAdapter(adapter);
		vr.setAdapter(adapter);

	}

	public void btnApostarClick(View v) {
		// Creo la nova aposta i la guardo a la base de dades.
		String idAposta = (getIntent().getStringExtra("Usuari") + getIntent()
				.getStringExtra("Circuit"));
		Aposta ap = new Aposta(idAposta, pr.getSelectedItem().toString(), sn
				.getSelectedItem().toString(), tr.getSelectedItem().toString(),
				qt.getSelectedItem().toString(), ce.getSelectedItem()
						.toString(), vr.getSelectedItem().toString());

		guardarAposta(ap);

		Toast.makeText(this, "Aposta Realitzada!", Toast.LENGTH_SHORT).show();

		Intent i = new Intent(ApostaActivity.this, GPListActivity.class);
		i.putExtra("Usuari", getIntent().getStringExtra("Usuari"));
		startActivity(i);
	}

	// Guarda el registre entrat a la base de dades.
	private void guardarAposta(Aposta a) {
		try {
			ApostesSQL apostaHelper = new ApostesSQL(this, "F1Bet.db", null, 2);
			ApostesConversor apostesConversor = new ApostesConversor(
					apostaHelper);
			// obtenir l'objecte BD
			SQLiteDatabase db = apostaHelper.getWritableDatabase();
			// Si s'ha obert correctament la BD4
			if (db != null) {
				// guardar a la base de dades
				apostesConversor.save(a);
				setResult(RESULT_OK);
			}
		} catch (Exception e) {
			setResult(RESULT_CANCELED);
		} finally {
			finish();
		}
	}
}
