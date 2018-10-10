package hospital.com.dkhospitalrecords.view.fragments;


import android.content.Context;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mindorks.placeholderview.ExpandablePlaceHolderView;
import com.mindorks.placeholderview.Utils;

import hospital.com.dkhospitalrecords.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MedicationFragment extends Fragment {
    ExpandablePlaceHolderView mExpandableView;
    Context mContext;


    public MedicationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medication, container, false);
        mContext = getActivity();
        mExpandableView = view.findViewById(R.id.expandableView);
      /*  for (Feed feed : Utils.loadFeeds(getActivity())) {
            mExpandableView.addView(new HeadingView(mContext, feed.getHeading()));
            for (Info info : feed.getInfoList()) {
                mExpandableView.addView(new InfoView(mContext, info));
            }
        }*/
        return view;
    }

}
