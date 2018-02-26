package cz.pv239.seminar2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static cz.pv239.seminar2.ListActivity.ListType.LIST_VIEW_SIMPLE;
import static cz.pv239.seminar2.ListActivity.ListType.RECYCLER_VIEW;

public class ListActivity
        extends AppCompatActivity {

    private static final String TAG = ListActivity.class.getSimpleName();

    public static final String EXTRA_LIST_TYPE = "extra_list_type";

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({LIST_VIEW_SIMPLE, RECYCLER_VIEW})
    public @interface ListType {
        int LIST_VIEW_SIMPLE = 0;
        int RECYCLER_VIEW = 1;
    }

    @NonNull
    public static Intent newIntent(@NonNull Context context, @ListType int listType) {
        Intent intent = new Intent(context, ListActivity.class);
        intent.putExtra(EXTRA_LIST_TYPE, listType);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        @ListType int listType = getIntent().getIntExtra(EXTRA_LIST_TYPE, ListType.LIST_VIEW_SIMPLE);
        initList(listType);
    }

    private void initList(@ListType int listType) {
        ListView listView = findViewById(android.R.id.list);
        RecyclerView recyclerView = findViewById(R.id.list_recycler);
        switch (listType) {
            case LIST_VIEW_SIMPLE:
                initSimpleList();
                listView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                break;
            case RECYCLER_VIEW:
                initRecyclerView();
                listView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void initSimpleList() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new UserDb().getNames());
        ListView list = findViewById(android.R.id.list);
        list.setAdapter(adapter);
    }

    private void initRecyclerView() {
        ImageListAdapter adapter = new ImageListAdapter(this, new UserDb());
        RecyclerView recyclerView = findViewById(R.id.list_recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
