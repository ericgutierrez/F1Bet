package com.example.f1bet;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.f1bet.bbdd.ApostesConversor;
import com.example.f1bet.bbdd.ApostesSQL;
import com.example.f1bet.constructors.GP;

public class GPDetailFragment extends Fragment {

	private ImageView imatge;
	public static final String ARG_ITEM_ID = "item_id";

	private GP mItem;

	private Cursor cursorApostes;
	private ApostesSQL apostesHelper;
	private ApostesConversor apostesConversor;
	
	private String id;
	private Boolean aposta = false;

	public GPDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {

			mItem = GPListFragment.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
		}

		// comprovo si ha fet una aposta en aquest circuit, si l'ha fet el botó
		// cambiara a consultar aposta, si no deixara fer-ne una.
		apostesHelper = new ApostesSQL(getActivity(), "F1Bet.db", null, 2);
		// obtenir l'objecte BD3
		SQLiteDatabase db = apostesHelper.getWritableDatabase();
		apostesConversor = new ApostesConversor(apostesHelper);
		id = getArguments().getString(("Usuari")) + mItem.getNom();

		// Si s'ha obert correctament la BD
		if (db != null) {
			// retorna true si l'usuari ja ha realitzat una aposta en aquest
			// circuit
			aposta = comprovarAposta(id);
			// Tancar la base de dades
			db.close();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_gp_detail,
				container, false);

		// Agafo tots els textview's del fragment i li assigno les dades del
		// circuit (mItem)
		if (mItem != null) {
			((TextView) rootView.findViewById(R.id.gp_detail)).setText(mItem
					.getNom());
			((TextView) rootView.findViewById(R.id.pais)).setText("País: "
					+ mItem.getPais());
			((TextView) rootView.findViewById(R.id.ciutat)).setText("Ciutat: "
					+ mItem.getCiutat());
			((TextView) rootView.findViewById(R.id.voltes)).setText("Voltes: "
					+ mItem.getVoltes());
			((TextView) rootView.findViewById(R.id.longitud))
					.setText("Longitud: " + String.valueOf(mItem.getLongitud())
							+ " Kms");
			((TextView) rootView.findViewById(R.id.distanciaCarrera))
					.setText("Longitud de carrera: "
							+ String.valueOf(mItem.getDistancia()) + " Kms");
			((TextView) rootView.findViewById(R.id.data))
					.setText("Data de carrera: "
							+ String.valueOf(mItem.getData()));

			// La imatge la descarrego de internet amb thread.
			imatge = ((ImageView) rootView.findViewById(R.id.imatgeCircuit));
			new TascaDescarrega()
					.execute(String.valueOf(mItem.getLinkimatge()));

			Button apostar = (Button) rootView.findViewById(R.id.btnApostar);

			if (aposta) {
				apostar.setText("Veure aposta");
			}

			apostar.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

					if (aposta) {
						Intent i = new Intent(getActivity(),
								ConsultaApostaActivity.class);
						// Passo el nom de l'usuari.
						i.putExtra("Usuari",
								getArguments().getString(("Usuari")));
						i.putExtra("id", id);
						startActivity(i);
					} else {
						Intent i = new Intent(getActivity(),
								ApostaActivity.class);
						// Passo el nom de l'usuari i del circuit a la seguent
						// activitat.
						i.putExtra("Usuari",
								getArguments().getString(("Usuari")));
						i.putExtra("Circuit", mItem.getNom());

						startActivity(i);
					}
				}
			});

		}
		return rootView;
	}

	class TascaDescarrega extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			return loadImageFromNetwork(params[0]);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			imatge.setImageBitmap(result);
		}
	}

	private Bitmap loadImageFromNetwork(String url) {
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream((InputStream) new URL(url)
					.getContent());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	// comprova l'usuari i contrasenya entrats amb la base de dades, retorna
	// true o false si l'usuari i contrasenya son correctes.
	public Boolean comprovarAposta(String id) {
		cursorApostes = apostesConversor.getAll();
		Boolean retorn = false;
		if (cursorApostes != null) {
			while (cursorApostes.moveToNext() && !retorn) {
				if (id.equals(cursorApostes.getString(0))) {
					retorn = true;
				}
			}
			cursorApostes.close();
		}
		return retorn;
	}
}
