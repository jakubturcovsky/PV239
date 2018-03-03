package cz.pv239.seminar2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FirstFragment
        extends Fragment {

    private static final String TAG = FirstFragment.class.getSimpleName();

    private static final String ARG_BOOL = "bool";

    private boolean mBool;

    @BindView(R.id.column) TextView mColumn;
    private Unbinder mUnbinder;

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    /**
     * Fragment attached to its Activity. Fragment yet merely exists.
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
    }

    /**
     * Fragment creation or state restoration. No views.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        // Restore variable content after orientation change
        if (savedInstanceState != null) {
            mBool = savedInstanceState.getBoolean(ARG_BOOL);
        }
    }

    /**
     * Here come the views.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        // Inflating Fragment with layout and its views
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        // Binding views from XML to Fragment's global variables annotated @BindView(R.id....)
        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    /**
     * Activity is completely created.
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    /**
     * Orientation change or pressing home button has happened. Let's save variables' states.
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
        // Save variable content to the Bundle
        outState.putBoolean(ARG_BOOL, mBool);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
        mUnbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }
}
