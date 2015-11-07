package org.dalol.headeredgrid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.dalol.headeredgrid.R;
import org.dalol.headeredgrid.adapter.GridListAdapter;
import org.dalol.headeredgrid.model.GridItem;
import org.dalol.headeredgrid.model.HeaderItem;
import org.dalol.headeredgrid.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int DEFAULT_SPAN_COUNT = 4;
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private GridListAdapter mAdapter;
    private List<Item> mItemList = new ArrayList<>();
    private Button mAddHeader, mAddGridItem;
    private int mHeaderCounter = 0;
    private int mGridCounter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        configViews();
    }

    private void configViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewList);
        mAddHeader = (Button) findViewById(R.id.btnAddHeader);
        mAddGridItem = (Button) findViewById(R.id.btnAddGridItem);
        mAddHeader.setOnClickListener(this);
        mAddGridItem.setOnClickListener(this);

        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), DEFAULT_SPAN_COUNT);

        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new GridListAdapter(mItemList, gridLayoutManager, DEFAULT_SPAN_COUNT);
        mRecyclerView.setAdapter(mAdapter);


        addMockList();
    }

    private void addMockList() {
        mAdapter.addItem(new HeaderItem("Header " + getHeaderCounter()));

        mAdapter.addItem(new GridItem("Grid " + getGridCounter(), mGridCounter));
        mAdapter.addItem(new GridItem("Grid " + getGridCounter(), mGridCounter));
        mAdapter.addItem(new GridItem("Grid " + getGridCounter(), mGridCounter));
        mAdapter.addItem(new GridItem("Grid " + getGridCounter(), mGridCounter));
        mAdapter.addItem(new GridItem("Grid " + getGridCounter(), mGridCounter));
        mAdapter.addItem(new GridItem("Grid " + getGridCounter(), mGridCounter));
        mAdapter.addItem(new GridItem("Grid " + getGridCounter(), mGridCounter));
        mAdapter.addItem(new GridItem("Grid " + getGridCounter(), mGridCounter));
        mAdapter.addItem(new GridItem("Grid " + getGridCounter(), mGridCounter));
        mAdapter.addItem(new GridItem("Grid " + getGridCounter(), mGridCounter));

        int headerPosition = new Random().nextInt(19) + 1;

        for (int i = 0; i < 100; i++) {
            if (i % headerPosition == 0) {
                mAdapter.addItem(new HeaderItem("Header " + getHeaderCounter()));
                headerPosition = new Random().nextInt(19) + 1;
            }

            mAdapter.addItem(new GridItem("Grid " + getGridCounter(), mGridCounter));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddHeader:
                addHeader();
                break;
            case R.id.btnAddGridItem:
                addGridItem();
                break;
        }
    }

    private void addHeader() {
        mAdapter.addItem(new HeaderItem("Header " + getHeaderCounter()));
    }

    private void addGridItem() {
        mAdapter.addItem(new GridItem("Grid " + getGridCounter(), mGridCounter));
    }

    public int getHeaderCounter() {
        mGridCounter = 0;
        return ++mHeaderCounter;
    }

    public int getGridCounter() {
        return ++mGridCounter;
    }
}
