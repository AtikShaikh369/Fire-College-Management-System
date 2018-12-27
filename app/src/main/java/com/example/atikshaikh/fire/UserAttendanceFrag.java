package com.example.atikshaikh.fire;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserAttendanceFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserAttendanceFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserAttendanceFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    StudentInformation std;
    String value="";
    String name="";
    TextView tvname,tvroll,tvdept,tvcontact;
    ListView lv;


    public UserAttendanceFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserAttendanceFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static UserAttendanceFrag newInstance(String param1, String param2) {
        UserAttendanceFrag fragment = new UserAttendanceFrag();
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

        final View v = inflater.inflate(R.layout.fragment_user_fragment3, container, false);

        std=new StudentInformation();
        std.setName("AttapattuD");
        final ProgressDialog pd=new ProgressDialog(getContext(),ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Fetching Information");
        pd.setCancelable(true);
        pd.show();

        Intent intent = getActivity().getIntent();
        name = intent.getStringExtra("Name");
        Log.e("name is",name);
        if(name.equals("Email"))
            value=intent.getStringExtra("Email").trim();
        else
            value=intent.getStringExtra("RollNo");
        Log.e("email is", value);
        try{
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = database.getReference();
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {
                        // Toast.makeText(StudentInformationActivity.this, "Hello bro Again", Toast.LENGTH_SHORT).show();
                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                        for (DataSnapshot child : children) {
                            StudentInformation std1=child.getValue(StudentInformation.class);
                            //check for subjects
                            if(name.equals("Email")&&std1.getEmail().equalsIgnoreCase(value))
                                std = std1;
                            else if(name.equals("RollNo")&&std1.getRollno().equalsIgnoreCase(value))
                                std = std1;
                        }
                        pd.dismiss();
                        fn(v);
                    } catch (Exception e) {
                        Log.e("Exception is", e.toString());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }catch(Exception e){
            Log.e("Exception is",e.toString());
        }


        return  v;
    }


    void fn(View v){
        try{
            if(std.getName().equals("AttapattuD")&&name.equals("RollNo")){
                Toast.makeText(getContext(), "Roll No. not found", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(),SelectRollNo.class);
                startActivity(intent);
                getActivity().finish();
            }
        }catch(Exception e){

        }
        tvname=(TextView)v.findViewById(R.id.textView14);
        tvroll=(TextView)v.findViewById(R.id.textView17);
        tvdept=(TextView)v.findViewById(R.id.textView18);
        tvcontact=(TextView)v.findViewById(R.id.textView20);
        lv=(ListView)v.findViewById(R.id.listView3);
        //Toast.makeText(StudentInformationActivity.this, "fn is called", Toast.LENGTH_SHORT).show();
        tvname.setText(std.getName());
        tvroll.setText(std.getRollno());
        tvdept.setText(std.getDepartment());
        tvcontact.setText(std.getPhone());
        ArrayList<Integer> al=std.getSubjects();
        ArrayList<String> subjectList=new ArrayList<String>();
        int count=0;
        int countTot=0;
        for(int i=0;i<7;i++){
            int val=al.get(i);
            if(val!=-1){
                count=count+val%1000;
                String str="CS0"+(i+1)+"   ----   "+val%1000+" / ";
                val=val/1000;
                countTot=countTot+val%1000;
                str=str+val%1000;
                subjectList.add(str);
            }
        }
        if(countTot==0)
            countTot=1;
        Toast.makeText(getContext(), "Your Total Attendance is--"+(count*100)/countTot+"%", Toast.LENGTH_LONG).show();
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,subjectList);
        lv.setAdapter(arrayAdapter);
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
