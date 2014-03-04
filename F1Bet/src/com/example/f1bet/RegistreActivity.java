package com.example.f1bet;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.f1bet.R;
import com.example.f1bet.bbdd.RegistresConversor;
import com.example.f1bet.bbdd.RegistresSQL;
import com.example.f1bet.constructors.Registre;

public class RegistreActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registre);
	}

	public void btnRegistrarClick(View v) {

		Intent i = new Intent(RegistreActivity.this, IniciActivity.class);
		// Creo un nou registre obtenint les dades dels EditText.
		Registre r = new Registre();

		r.setNom(((EditText) findViewById(R.id.registerNom)).getText()
				.toString());
		r.setCorreu(((EditText) findViewById(R.id.registerCorreu)).getText()
				.toString());
		r.setContrasenya(((EditText) findViewById(R.id.registerContrasenya))
				.getText().toString());

		guardarRegistre(r);

		Toast.makeText(this, "Registrat correctament!", Toast.LENGTH_SHORT)
				.show();

		startActivity(i);
	}

	//Guarda el registre entrat a la base de dades.
	private void guardarRegistre(Registre r) {
		try {
			RegistresSQL registreHelper = new RegistresSQL(this, "F1Bet.db",
					null, 1);
			RegistresConversor registresConversor = new RegistresConversor(
					registreHelper);
			// obtenir l'objecte BD
			SQLiteDatabase db = registreHelper.getWritableDatabase();
			// Si s'ha obert correctament la BD
			if (db != null) {
				// guardo el registre a la base de dades
				registresConversor.save(r);
				setResult(RESULT_OK);
			}
		} catch (Exception e) {
			setResult(RESULT_CANCELED);
		} finally {
			finish();
		}
	}
}
