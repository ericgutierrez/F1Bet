package com.example.f1bet.bbdd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.f1bet.constructors.Registre;
 
public class RegistresConversor {
 
	private RegistresSQL helper;
 
	public RegistresConversor() {
 
	}

	public RegistresConversor(RegistresSQL helper) {
		this.helper = helper;
	}
 
	public long save(Registre registre) {		
		long index = -1;
		// s'agafa l'objecte base de dades en mode escriptura
		SQLiteDatabase db = helper.getWritableDatabase();
		// es crea un objecte de diccionari (clau,valor) per indicar els valors a afegir 
		ContentValues dades = new ContentValues();
 
    	dades.put("nom", registre.getNom());
    	dades.put("correu", registre.getCorreu());
    	dades.put("contrasenya", registre.getContrasenya());
    	try {
    		index = db.insertOrThrow("Registres", null, dades);
    		// volem veure en el log el que passa
    		Log.i("Registres", dades.toString() + " afegit amb codi " + index);
    	}
    	catch(Exception e) {
    		// volem reflectir en ellog que hi ha hagut un error
    		Log.e("Registres", e.getMessage());
    	}
    	return index;
	}	
 
	/**
	 * Retorna un cursor amb totes les dades de la taula
	 * @return
	 */
	public Cursor getAll() {
		SQLiteDatabase db = helper.getReadableDatabase();
 
		return db.query(true, "Registres", 
						new String[]{"nom","correu","contrasenya"}, 
						null, null, null, null, null, null);
	}
 
	/**
	 * Esborra el titular passat per paràmetre
	 * @param t el titular a esborrar
	 * @return la quantitat de titulars eliminats
	 */
	public boolean remove(Registre r) {		
		// obtenir l'objecte BD en mode esriptura
		SQLiteDatabase db = helper.getWritableDatabase();
 
		return db.delete("Registres", "nom=" + r.getNom(),null ) > 0;
	}
	/**
	 * Esborra tots els titulars de la taula
	 * @return 
	 */
	public boolean removeAll() {		
		// obtenir l'objecte BD en mode escriptura
		SQLiteDatabase db = helper.getWritableDatabase();
 
		return db.delete("Registres", null, null ) > 0;
	}
}