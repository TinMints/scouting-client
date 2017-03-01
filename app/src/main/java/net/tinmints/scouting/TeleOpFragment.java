package net.tinmints.scouting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeleOpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeleOpFragment extends Fragment {


    public TeleOpFragment() {
        // Required empty public constructor
    }

    public static TeleOpFragment newInstance(String param1, String param2) {
        TeleOpFragment fragment = new TeleOpFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tele_op, container, false);
    }

}
