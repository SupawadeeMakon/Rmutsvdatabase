package rmutsv.makon.ppp.rmutsvdatabase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ppp on 7/3/2017 AD.
 */

public class NewRegisterFragment extends Fragment{

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
}//Main Class
