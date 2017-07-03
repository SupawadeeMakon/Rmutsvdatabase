package rmutsv.makon.ppp.rmutsvdatabase.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import rmutsv.makon.ppp.rmutsvdatabase.NewRegisterFragment;
import rmutsv.makon.ppp.rmutsvdatabase.R;

/**
 * Created by ppp on 7/3/2017 AD.
 */

public class MainFragment extends Fragment{

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
