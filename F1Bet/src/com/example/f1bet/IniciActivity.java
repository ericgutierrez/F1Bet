package com.example.f1bet;

import com.example.f1bet.bbdd.RegistresConversor;
import com.example.f1bet.bbdd.RegistresSQL;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class IniciActivity extends Activity {

	private Cursor cursorRegistres;
	private RegistresSQL registesHelper;
	private RegistresConversor registresConversor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inici);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void btnParticiparClick(View v) {

		Boolean usuariCorrecte = false;
		String nom = ((EditText) findViewById(R.id.nom)).getText().toString();
		String contrasenya = ((EditText) findViewById(R.id.contrasenya))
				.getText().toString();

		// crear l'objecte que crea la connexió amb la BD
		registesHelper = new RegistresSQL(this, "F1Bet.db", null, 1);
		registresConversor = new RegistresConversor(registesHelper);
		// obtenir l'objecte BD3
		SQLiteDatabase db = registesHelper.getWritableDatabase();

		// Si s'ha obert correctament la BD
		if (db != null) {
			// retorna true si l'usuari i contrasenya es correcte
			usuariCorrecte = comprovarUsuari(nom, contrasenya);
			// Tancar la base de dades
			db.close();
		}

		Intent i = new Intent(IniciActivity.this, GPListActivity.class);

		// Si el nom i la contrassenya estan buits toast que avisi que ompli els
		// camps.
		if (nom.equals("") && contrasenya.equals("")) {
			Toast.makeText(this, "Omple els camps!", Toast.LENGTH_SHORT).show();
		} else {
			if (usuariCorrecte) {
				// Paso el nom de l'usuari amb putExtra.
				i.putExtra("Usuari", nom);
				startActivity(i);
			} else {
				// si no toast avisant que l'usuari o la contrasenya son
				// incorrectes.
				Toast.makeText(this, "Usuari o contrasenya incorrectes!",
						Toast.LENGTH_LONG).show();
			}
		}
	}

	public void btnpRegistrarClick(View v) {
		// Inicia la activitat de registrar-se.
		Intent i = new Intent(IniciActivity.this, RegistreActivity.class);
		startActivity(i);
	}

	// comprova l'usuari i contrasenya entrats amb la base de dades, retorna
	// true si l'usuari i contrasenya son correctes.
	public Boolean comprovarUsuari(String n, String c) {
		cursorRegistres = registresConversor.getAll();
		Boolean trobat = false;
		if (cursorRegistres != null) {
			while (cursorRegistres.moveToNext() && !trobat) {
				if (n.equals(cursorRegistres.getString(0))
						&& c.equals(cursorRegistres.getString(2))) {
					trobat = true;
				}
			}
			cursorRegistres.close();
		}
		return trobat;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// action with ID action_refresh was selected
		case R.id.action_info:
			Intent i = new Intent(IniciActivity.this, InfoActivity.class);
			startActivity(i);
			break;
		}
		return true;
	}
}