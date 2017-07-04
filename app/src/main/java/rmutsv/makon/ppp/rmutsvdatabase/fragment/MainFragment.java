package rmutsv.makon.ppp.rmutsvdatabase.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import rmutsv.makon.ppp.rmutsvdatabase.GetAllData;
import rmutsv.makon.ppp.rmutsvdatabase.MyAlert;
import rmutsv.makon.ppp.rmutsvdatabase.R;
import rmutsv.makon.ppp.rmutsvdatabase.ServiceActivity;

/**
 * Created by ppp on 7/3/2017 AD.
 */

public class MainFragment extends Fragment{


    private Button button;
    private EditText usereditText, passwordEditText;
    private String useString,passwordString;

    //ทำหน้าที่ในการคืนค่า
    public static MainFragment mainInstance(){
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        mainFragment.setArguments(bundle);
        return mainFragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.main_fragment_layout,container, false);
        return view;
    }//onCreateView

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //New Register Controller
        newRegisterController();

        //Login Controller
        loginController();

    }//OnActivityCreate

    private void loginController() {

        //Initial View
        button = (Button) getView().findViewById(R.id.btnlogin);
        usereditText = (EditText) getView().findViewById(R.id.edtUser);
        passwordEditText = (EditText) getView().findViewById(R.id.edtPassword);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                useString = usereditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                if (useString.equals("")|| passwordString.equals("")) {
                    //Have Space
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog("มีช่องว่าง","กรุณากรอกที่กช่อง");

                } else {
                    //No Space
                    checkUserAndPass();


                }

            }//Onclick
        });

    }

    private void checkUserAndPass() {
        String tag = "4JulyV2";
        String urlPHP = "http://androidthai.in.th/piw/getAllUserSupawadee.php";
        boolean b = true;

        String[] columString1 = new String[]{"id","Name","User","Password"};
        String[] strings = new String[columString1.length];


        try {
            GetAllData getAllData = new GetAllData(getActivity());
            getAllData.execute(urlPHP);
            String strJSON = getAllData.get();
            Log.d(tag, "JSON ==>" + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            for (int i=0; i<jsonArray.length(); i+=1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (useString.equals(jsonObject.getString("User"))) {
                    b = false;
                    for (int i1=0; i1<columString1.length; i1++) {
                        strings[i1] = jsonObject.getString(columString1[i1]);
                        Log.d(tag,"Strings["+i1+"] ==>" + strings[i1]);

                    }//for

                }

            }//for

            if (b) {
                //User False
                MyAlert myAlert = new MyAlert(getActivity());
                myAlert.myDialog("User Flase","No This User in my database");
            } else if (passwordString.equals(strings[3])) {
                //Password True
                Toast.makeText(getActivity(),"Welcome "+ strings[1],
                        Toast.LENGTH_SHORT).show();


                //Intent to ServiceActivity
                Intent intent = new Intent(getActivity(), ServiceActivity.class);
                intent.putExtra("Login", strings);
                startActivity(intent);
                getActivity().finish();


            } else {
                //Password False

                MyAlert myAlert = new MyAlert(getActivity());
                myAlert.myDialog("Password False", "Please Try Again Password False");

            }




        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void newRegisterController() {
        TextView textView =  (TextView) getView().findViewById(R.id.textNewRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.relContent, NewRegisterFragment.newInstance())
                        .commit();
            }
        });
    }
}//mainclass
