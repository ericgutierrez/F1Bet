package com.example.f1bet.bbdd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.f1bet.constructors.Aposta;

public class ApostesConversor {

	private ApostesSQL helper;

	public ApostesConversor() {

	}

	public ApostesConversor(ApostesSQL helper) {
		this.helper = helper;
	}

	public long save(Aposta aposta) {
		long index = -1;
		// s'agafa l'objecte base de dades en mode escriptura
		SQLiteDatabase db = helper.getWritableDatabase();
		// es crea un objecte de diccionari (clau,valor) per indicar els valors a afegir
		ContentValues dades = new ContentValues();

		dades.put("idaposta", aposta.getId());
		dades.put("primer", aposta.getPosicio1());
		dades.put("segon", aposta.getPosicio2());
		dades.put("tercer", aposta.getPosicio3());
		dades.put("quart", aposta.getPosicio4());
		dades.put("cinque", aposta.getPosicio5());
		dades.put("voltarapida", aposta.getVoltarapida());

		try {
			index = db.insertOrThrow("Apostes", null, dades);
			// volem veure en el log el que passa
			Log.i("Apostes", dades.toString() + " afegit amb codi " + index);
		} catch (Exception e) {
			// volem reflectir en ellog que hi ha hagut un error
			Log.e("Apostes", e.getMessage());
		}
		return index;
	}

	public Cursor getAll() {
		SQLiteDatabase db = helper.getReadableDatabase();

		return db.query(true, "Apostes",
				new String[] { "idaposta", "primer", "segon", "tercer",
						"quart", "cinque", "voltarapida" }, null, null,
				null, null, null, null);
	}

	public boolean remove(Aposta a) {
		// obtenir l'objecte BD en mode esriptura
		SQLiteDatabase db = helper.getWritableDatabase();

		return db.delete("Apostes", "idaposta=" + a.getId(),null ) > 0;
	}

	public boolean removeAll() {
		// obtenir l'objecte BD en mode escriptura
		SQLiteDatabase db = helper.getWritableDatabase();

		return db.delete("Apostes", null, null) > 0;
	}
}