package com.example.atikshaikh.fire;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import uk.co.senab.photoview.PhotoViewAttacher;

public class AdminTimeTableFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    String h;    private String mParam2;

    private Button selectImage, uploadImage;
    private ProgressDialog progressDialog;
    private ImageView timeTable;

    private static int GALLARY_INTENT = 2;

    private OnFragmentInteractionListener mListener;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
            .setTimestampsInSnapshotsEnabled(true)
            .build();


    Uri uri;
    Uri uriMain;

    private StorageTask mUploadTask;

    public AdminTimeTableFrag() {
        // Required empty public constructor
    }

    public static AdminTimeTableFrag newInstance(String param1, String param2) {
        AdminTimeTableFrag fragment = new AdminTimeTableFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        firestore.setFirestoreSettings(settings);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admintimetable, container, false);

        if(uriMain != null) Glide.with(getContext()).load(uriMain.toString()).into(timeTable);
            //Picasso.get().load(uriMain).fit().centerCrop().into(timeTable);

        timeTable = (ImageView)view.findViewById(R.id.timeTable);
        selectImage = (Button) view.findViewById(R.id.selectImage);
        uploadImage = (Button) view.findViewById(R.id.uploadImage);
        progressDialog = new ProgressDialog(getContext());

        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();

        selectImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLARY_INTENT);
            }
        });
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(getContext(), "Upload in progress", Toast.LENGTH_SHORT);
                }
                else  uploadImageFile();
            }
        });
        return view;
     }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLARY_INTENT && resultCode == Activity.RESULT_OK){
            uri = data.getData();
            uriMain = uri;
            Picasso.get().load(uri).fit().centerCrop().into(timeTable);
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(timeTable);
            photoViewAttacher.update();
        }
    }

    private void uploadImageFile(){
        if(uri != null) {
            progressDialog.setMessage("Uploading...");
            progressDialog.show();
            String name = "tt";
            StorageReference filepath = mStorageRef.child("TimeTable").child(name);//child(uri.getLastPathSegment());
            mUploadTask = filepath.putFile(uri)
                     .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getContext(), "Upload Done", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();

                           // Upload upload = new Upload("TimeTable", taskSnapshot.getStorage().getDownloadUrl().toString());
                            //String uploadId = mDatabaseRef.push().getKey();
                            //mDatabaseRef.child(uploadId).setValue(upload);



                            //Task<Uri> dowloadUri = taskSnapshot.getStorage().getDownloadUrl();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Failed " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploading " + (int) progress + "%");
                        }
                    })
             .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if(task.isSuccessful()){
                       // Task<Uri> urlTask = task.getResult().getStorage().getDownloadUrl();
                       // while (!urlTask.isSuccessful());
                        //Uri downloadUrl = urlTask.getResult();
                        Task<Uri> downloadUri = task.getResult().getStorage().getDownloadUrl();

                        Map<String ,String> userMap = new HashMap<>();
                       // userMap.put("image", downloadUrl);
                        userMap.put("image", "hello");
                        firebaseFirestore.collection("TimeTable").document("Image")
                                .set(userMap);
                        /*        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                    progressDialog.dismiss();
                                    Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT);
                            }else{
                                String e = task.getException().getMessage();
                                    Toast.makeText(getContext(), "Firestore Eroor: " + e, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });*/
                    }
                }
            });
        }
        else Toast.makeText(getContext(), "No File Selected", Toast.LENGTH_SHORT).show();
    }


    
   /* private String getFileExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }*/

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
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
