package com.example.f1bet;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.f1bet.bbdd.ApostesConversor;
import com.example.f1bet.bbdd.ApostesSQL;
import com.example.f1bet.constructors.Aposta;

public class ConsultaApostaActivity extends Activity {

	private Cursor cursorApostes;
	private ApostesSQL apostesHelper;
	private ApostesConversor apostesConversor;
	private Aposta aposta;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consulta_aposta);

		apostesHelper = new ApostesSQL(this, "F1Bet.db", null, 2);
		// obtenir l'objecte BD3
		SQLiteDatabase db = apostesHelper.getWritableDatabase();
		apostesConversor = new ApostesConversor(apostesHelper);

		// Si s'ha obert correctament la BD
		if (db != null) {
			// retorna true si l'usuari ja ha realitzat una aposta en el circuit
			// seleccionat.
			aposta = obtenirAposta();
			// Tancar la base de dades
			db.close();
		}

		// mostro el resultat de la aposta als textview's
		((TextView) findViewById(R.id.consulta1)).setText("Posicio 1: "
				+ aposta.getPosicio1());
		((TextView) findViewById(R.id.consulta2)).setText("Posicio 2: "
				+ aposta.getPosicio2());
		((TextView) findViewById(R.id.consulta3)).setText("Posicio 3: "
				+ aposta.getPosicio3());
		((TextView) findViewById(R.id.consulta4)).setText("Posicio 4: "
				+ aposta.getPosicio4());
		((TextView) findViewById(R.id.consulta5)).setText("Posicio 5: "
				+ aposta.getPosicio5());
		((TextView) findViewById(R.id.consultavr)).setText("Volta Ràpida: "
				+ aposta.getVoltarapida());
	}

	public void btnEliminarClick(View v) {
		// Elimina la aposta.
		apostesConversor.remove(aposta);

		// Retorna a la llista de gps.
		Intent i = new Intent(ConsultaApostaActivity.this, GPListActivity.class);
		i.putExtra("Usuari", getIntent().getStringExtra("Usuari"));
		Toast.makeText(this, "Eliminada correctament", Toast.LENGTH_SHORT)
				.show();
		startActivity(i);
	}

	// Agafo la aposta de la base de dades.
	public Aposta obtenirAposta() {
		String id = getIntent().getStringExtra("id");
		Aposta a = null;
		cursorApostes = apostesConversor.getAll();
		Boolean trobat = false;
		if (cursorApostes != null) {
			while (cursorApostes.moveToNext() && !trobat) {
				if (id.equals(cursorApostes.getString(0))) {
					a = new Aposta(cursorApostes.getString(0),
							cursorApostes.getString(1),
							cursorApostes.getString(2),
							cursorApostes.getString(3),
							cursorApostes.getString(4),
							cursorApostes.getString(5),
							cursorApostes.getString(6));
					trobat = true;
				}
			}
			cursorApostes.close();
		}
		return a;
	}
}
