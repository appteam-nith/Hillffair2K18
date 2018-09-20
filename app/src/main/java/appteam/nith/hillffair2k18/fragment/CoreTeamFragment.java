package appteam.nith.hillffair2k18.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import appteam.nith.hillffair2k18.R;
import appteam.nith.hillffair2k18.adapter.ClubAdapter;
import appteam.nith.hillffair2k18.adapter.TeamAdapter;
import appteam.nith.hillffair2k18.model.Team;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CoreTeamFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CoreTeamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoreTeamFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView thirdRec;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView3;
    private RecyclerView.LayoutManager recylerViewLayoutManager3;
    private TeamAdapter recyclerViewAdapter3;
    private List<Team> teamList;
    Context context;
    private OnFragmentInteractionListener mListener;
    public CoreTeamFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CoreTeamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CoreTeamFragment newInstance(String param1, String param2) {
        CoreTeamFragment fragment = new CoreTeamFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        thirdRec = getView().findViewById(R.id.thirdRec);
        context = getContext();
        recyclerView3 = thirdRec;
        recylerViewLayoutManager3 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView3.setLayoutManager(recylerViewLayoutManager3);
        recyclerViewAdapter3 = new TeamAdapter(teamList, (Activity) context);
        recyclerView3.setAdapter(recyclerViewAdapter3);
        return inflater.inflate(R.layout.fragment_core_team, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
