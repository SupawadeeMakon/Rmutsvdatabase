package rmutsv.makon.ppp.rmutsvdatabase.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import rmutsv.makon.ppp.rmutsvdatabase.MyAlert;
import rmutsv.makon.ppp.rmutsvdatabase.PostUserToServer;
import rmutsv.makon.ppp.rmutsvdatabase.R;

/**
 * Created by ppp on 7/3/2017 AD.
 */

public class NewRegisterFragment extends Fragment{

    //Explicit
    private Button button;
    private EditText nameEditText, userEditText,passwordEditText;


    public static NewRegisterFragment newInstance(){
        NewRegisterFragment newRegisterFragment = new NewRegisterFragment();
        Bundle bundle = new Bundle();
        newRegisterFragment.setArguments(bundle);
        return newRegisterFragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.new_register_fragment_layout, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Back Controller
        backController();

        //New Register Controller
        newRegisterController();


    }//onActivityCreate

    private void newRegisterController() {

        //Initial View ผูกความสัมพันธ์ของตัวแปร
        button = (Button) getView().findViewById(R.id.btnNewRegister);
        nameEditText = (EditText) getView().findViewById(R.id.edtName);
        userEditText = (EditText) getView().findViewById(R.id.edtUser);
        passwordEditText = (EditText) getView().findViewById(R.id.edtPassword);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get Value From Edit Text
                String strName = nameEditText.getText().toString().trim(); //ประกาศตัวแปร
                String strUser = userEditText.getText().toString().trim();
                String strPassword = passwordEditText.getText().toString().trim();

                //Check Space เช็คช่องว่าง
                if (strName.length() ==0 || strUser.length()==0 || strPassword.length()==0) {
                    //Have Space

                    Log.d("4JulyV1", "Have Space");
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog("Have Space","Please Fill All Every Blank");

                } else {
                    //No Space
                    Log.d("4JulyV1", "No Space");

                    uploadNewUserToServer(strName,strUser,strPassword);
                }

            }
        });

    }

    private void uploadNewUserToServer(String strName, String strUser, String strPassword) {

        try {

            PostUserToServer postUserToServer = new PostUserToServer(getActivity());
            postUserToServer.execute(strName,strUser,strPassword);

            String result = postUserToServer.get();
            Log.d("4JulyV1", "result ==>" + result);
            if (Boolean.parseBoolean(result)) {

                backHome();

            } else {

                MyAlert myAlert= new MyAlert(getActivity());
                myAlert.myDialog("Connot Upload Value",
                        "Please Try Again Cannot Uploade Value to Server");

            }

        } catch (Exception e) {
            Log.d("4JulyV1", "e upload ==>" + e.toString());
        }

    }

    private void backHome() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.relContent, MainFragment.mainInstance())
                .commit();

    }

    private void backController() {
        ImageView imageView = (ImageView) getView().findViewById(R.id.btnBack);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.relContent, MainFragment.mainInstance())
                        .commit();


            }
        });
    }

}//Main Class
