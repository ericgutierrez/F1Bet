// Adadptador de la llista de carreres (GPs).
package com.example.f1bet;

import com.example.f1bet.constructors.GP;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GPAdapter extends ArrayAdapter<GP> {

	class Vista {
		public ImageView imatge;
		public TextView nom;
		public TextView ciutat;
	}

	private GP[] dades;
	Activity context;

	public GPAdapter(Activity context, int simpleListItemActivated1, GP[] dades) {
		super(context, R.layout.listitem_gp, dades);
		this.dades = dades;
		this.context = context;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View element = convertView;
		Vista vista;

		if (element == null) {
			LayoutInflater inflater = ((Activity) getContext())
					.getLayoutInflater();
			element = inflater.inflate(R.layout.listitem_gp, null);

			vista = new Vista();
			vista.imatge = (ImageView) element.findViewById(R.id.imatgePais);
			vista.nom = (TextView) element.findViewById(R.id.textViewNom);
			vista.ciutat = (TextView) element.findViewById(R.id.textViewCiutat);
			element.setTag(vista);
		} else {
			vista = (Vista) element.getTag();
		}

		vista.imatge.setImageDrawable(context.getResources().getDrawable(
				dades[position].getImatge()));
		vista.nom.setText(dades[position].getNom());
		vista.ciutat.setText(dades[position].getCiutat());

		return element;

	}

}
