package com.example.f1bet.bbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class RegistresSQL extends SQLiteOpenHelper {
	// Sentència SQL per crear la taula de Titulars
	private final String SQL_CREATE_REGISTRES = "CREATE TABLE Registres("
			+ "	nom TEXT PRIMARY KEY, " + "	correu TEXT, "
			+ "	contrasenya TEXT)";

	
	public RegistresSQL(Context context, String nom, CursorFactory factory,
			int versio) {
		super(context, nom, factory, versio);
	}

	/**
	 * Event que es produeix quan s'ha de crear la BD
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// S'executen les sentències SQL de creació de la BD
		db.execSQL(SQL_CREATE_REGISTRES);
	}

	/**
	 * Event que es produeix quan s'ha d'actualitzar la BD a una versió superior
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int versioAnterior, int versioNova) {

		// S'elimina la versió anterior de la taula
		db.execSQL("DROP TABLE IF EXISTS Registres");
		// Es crea la nova versió de la taula
		db.execSQL(SQL_CREATE_REGISTRES);
	}
}