package com.example.f1bet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.example.f1bet.constructors.GP;

/**
 * A list fragment representing a list of GPs. This fragment also supports
 * tablet devices by allowing list items to be given an 'activated' state upon
 * selection. This helps indicate which item is currently being viewed in a
 * {@link GPDetailFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class GPListFragment extends ListFragment {
	
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private GP[] dades = new GP[19];
	public static Map<String, GP> ITEM_MAP = new HashMap<String, GP>();

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * activated item position. Only used on tablets.
	 */
	private static final String STATE_ACTIVATED_POSITION = "activated_position";

	/**
	 * The fragment's current callback object, which is notified of list item
	 * clicks.
	 */
	private Callbacks mCallbacks = sGpCallbacks;

	/**
	 * The current activated item position. Only used on tablets.
	 */
	private int mActivatedPosition = ListView.INVALID_POSITION;

	/**
	 * A callback interface that all activities containing this fragment must
	 * implement. This mechanism allows activities to be notified of item
	 * selections.
	 */
	public interface Callbacks {
		/**
		 * Callback for when an item has been selected.
		 */
		public void onItemSelected(String id);
	}

	/**
	 * A dummy implementation of the {@link Callbacks} interface that does
	 * nothing. Used only when this fragment is not attached to an activity.
	 */
	private static Callbacks sGpCallbacks = new Callbacks() {
		@Override
		public void onItemSelected(String id) {
		}
	};

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public GPListFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		crearCircuits();

		setListAdapter(new GPAdapter(getActivity(),
				android.R.layout.simple_list_item_activated_1, dades));
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		// Restore the previously serialized activated item position.
		if (savedInstanceState != null
				&& savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
			setActivatedPosition(savedInstanceState
					.getInt(STATE_ACTIVATED_POSITION));
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Activities containing this fragment must implement its callbacks.
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}

		mCallbacks = (Callbacks) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();

		// Reset the active callbacks interface to the dummy implementation.
		mCallbacks = sGpCallbacks;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		super.onListItemClick(listView, view, position, id);

		// Notify the active callbacks interface (the activity, if the
		// fragment is attached to one) that an item has been selected.

		// utilitzo l'array dades de GPContent (Hi ha les dades de totes les
		// carreres) per seleccionar el nom del circuit.
		mCallbacks.onItemSelected(dades[position].getNom());
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {
			// Serialize and persist the activated item position.
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

	/**
	 * Turns on activate-on-click mode. When this mode is on, list items will be
	 * given the 'activated' state when touched.
	 */
	public void setActivateOnItemClick(boolean activateOnItemClick) {
		// When setting CHOICE_MODE_SINGLE, ListView will automatically
		// give items the 'activated' state when touched.
		getListView().setChoiceMode(
				activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
						: ListView.CHOICE_MODE_NONE);
	}

	private void setActivatedPosition(int position) {
		if (position == ListView.INVALID_POSITION) {
			getListView().setItemChecked(mActivatedPosition, false);
		} else {
			getListView().setItemChecked(position, true);
		}

		mActivatedPosition = position;
	}
	
	//metode on creo tots els circuits.
	public void crearCircuits() {

		try {
			dades[0] = new GP("Albert Park", "Melbourne", "Australia",
					sdf.parse("2014-03-16 14:00:00"), 58, 5.303f, 307.574f,
					R.drawable.au,
					"http://www.f1aldia.com/images/circuitos/1.jpg");
			dades[1] = new GP("Sepang International Circuit", "Kuala Lumpur",
					"Malasia", sdf.parse("2014-03-30 14:00:00"), 56, 5.543f,
					310.408f, R.drawable.my,
					"http://www.f1aldia.com/images/circuitos/2.jpg");
			dades[2] = new GP("Bahrain International Circuit", "Sakhir",
					"Bahrain", sdf.parse("2014-04-06 14:00:00"), 57, 5.412f,
					308.238f, R.drawable.bh,
					"http://www.f1aldia.com/images/circuitos/18.jpg");
			dades[3] = new GP("Shanghai International Circuit", "Shanghai",
					"Xina", sdf.parse("2014-04-20 14:00:00"), 56, 5.451f,
					305.066f, R.drawable.cn,
					"http://www.f1aldia.com/images/circuitos/3.jpg");
			dades[4] = new GP("Circuit de Catalunya", "Barcelona", "Catalunya",
					sdf.parse("2014-05-11 14:00:00"), 66, 4.655f, 307.104f,
					R.drawable.cat,
					"http://www.f1aldia.com/images/circuitos/5.jpg");
			dades[5] = new GP("Circuit de Montecarlo", "Mònaco", "Mònaco",
					sdf.parse("2014-05-25 14:00:00"), 78, 3.340f, 260.520f,
					R.drawable.mc,
					"http://www.f1aldia.com/images/circuitos/6.jpg");
			dades[6] = new GP("Circuit Gilles-Villeneuve", "Montreal",
					"Canadà", sdf.parse("2014-06-08 14:00:00"), 70, 4.361f,
					305.270f, R.drawable.ca,
					"http://www.f1aldia.com/images/circuitos/19.jpg");
			dades[7] = new GP("Red Bull Ring", "Spielberg", "Austria",
					sdf.parse("2014-06-22 14:00:00"), 58, 5.303f, 307.574f,
					R.drawable.at,
					"http://www.f1aldia.com/images/circuitos/20.jpg"); // canviar
																		// aquest
			dades[8] = new GP("Silverstone Circuit", "Silverstone",
					"Regne Unit", sdf.parse("2014-07-06 14:00:00"), 52, 5.891f,
					306.198f, R.drawable.gb,
					"http://www.f1aldia.com/images/circuitos/20.jpg");
			dades[9] = new GP("Nürburgring", "Nürburg", "Alemanya",
					sdf.parse("2014-07-20 14:00:00"), 60, 5.148f, 308.863f,
					R.drawable.de,
					"http://www.f1aldia.com/images/circuitos/9.jpg");
			dades[10] = new GP("Hungaroring", "Budapest", "Hongria",
					sdf.parse("2014-07-27 14:00:00"), 70, 4.381f, 306.630f,
					R.drawable.hu,
					"http://www.f1aldia.com/images/circuitos/10.jpg");
			dades[11] = new GP("Spa-Francorchamps", "Spa", "Bèlgica",
					sdf.parse("2014-08-24 14:00:00"), 44, 7.004f, 308.052f,
					R.drawable.be,
					"http://www.f1aldia.com/images/circuitos/12.jpg");
			dades[12] = new GP("Autodromo di Monza", "Monza", "Italia",
					sdf.parse("2014-09-07 14:00:00"), 53, 5.793f, 306.720f,
					R.drawable.it,
					"http://www.f1aldia.com/images/circuitos/13.jpg");
			dades[13] = new GP("Marina Bay Street Circuit", "Singapur",
					"Singapur", sdf.parse("2014-09-21 14:00:00"), 61, 5.073f,
					309.316f, R.drawable.sg,
					"http://www.f1aldia.com/images/circuitos/14.jpg");
			dades[14] = new GP("Suzuka Circuit", "Suzuka", "Japó",
					sdf.parse("2014-10-05 14:00:00"), 53, 5.807f, 307.471f,
					R.drawable.jp,
					"http://www.f1aldia.com/images/circuitos/15.jpg");
			dades[15] = new GP("Sochi Circuit", "Sochi", "Rússia",
					sdf.parse("2014-10-12 14:00:00"), 58, 5.303f, 307.574f,
					R.drawable.ru,
					"http://www.f1aldia.com/images/circuitos/15.jpg"); // Canviar
																		// aquest
			dades[16] = new GP("Circuito de las Américas", "Austin", "EE.UU",
					sdf.parse("2014-11-02 14:00:00"), 56, 5.516f, 308.896f,
					R.drawable.us,
					"http://www.f1aldia.com/images/circuitos/25.jpg");
			dades[17] = new GP("José Carlos Pace", "São Paulo", "Brasil",
					sdf.parse("2014-11-09 14:00:00"), 71, 4.309f, 305.909f,
					R.drawable.br,
					"http://www.f1aldia.com/images/circuitos/16.jpg");
			dades[18] = new GP("Yas Marina Circuit", "Abu Dabi",
					"Emiratos Árabes Unidos", sdf.parse("2014-11-23 14:00:00"),
					55, 5.554f, 305.355f, R.drawable.ae,
					"http://www.f1aldia.com/images/circuitos/17.jpg");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < dades.length; i++) {
			ITEM_MAP.put(dades[i].getNom(), dades[i]);
		}
	}
}
