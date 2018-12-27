package com.example.atikshaikh.fire;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserSyllabusFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserSyllabusFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserSyllabusFrag extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ImageView cn, dbms, toc, isee, sepm;

    public UserSyllabusFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserSyllabusFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static UserSyllabusFrag newInstance(String param1, String param2) {
        UserSyllabusFrag fragment = new UserSyllabusFrag();
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
        View v = inflater.inflate(R.layout.fragment_user_fragment2, container, false);
    /*    Bundle b = getArguments();
        String userType = b.getString("Type");
        Toast.makeText(getContext(), userType, Toast.LENGTH_LONG);*/

        cn = (ImageView)v.findViewById(R.id.usercn);
        isee = (ImageView)v.findViewById(R.id.userisee);
        sepm = (ImageView)v.findViewById(R.id.usersepm);
        dbms = (ImageView)v.findViewById(R.id.userdbms);
        toc = (ImageView)v.findViewById(R.id.usertoc);

        ImageView dbms = (ImageView)v.findViewById(R.id.userdbms);
        dbms.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),Userdbmssy.class);
                startActivity(intent);
            }
        });
        ImageView toc = (ImageView)v.findViewById(R.id.usertoc);
        toc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),Usertocsy.class));
            }
        });

        ImageView cn = (ImageView)v.findViewById(R.id.usercn);
        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),Usercnsy.class));
            }
        });

        ImageView isee = (ImageView)v.findViewById(R.id.userisee);
        isee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),Useriseesy.class));
            }
        });

        ImageView sepm = (ImageView)v.findViewById(R.id.usersepm);
        sepm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),Usersepmsy.class));
            }
        });

        return v;
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

    @Override
    public void onClick(View v) {
        if(v == cn){
            startActivity(new Intent(getContext(), Usercnsy.class));
        }
        if(v == toc){
            startActivity(new Intent(getContext(), Usertocsy.class));
        }
        if(v == dbms){
            startActivity(new Intent(getContext(), Userdbmssy.class));
        }
        if(v == sepm){
            startActivity(new Intent(getContext(), Usersepmsy.class));
        }
        if(v == isee){
            startActivity(new Intent(getContext(), Useriseesy.class));
        }
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
