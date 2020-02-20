package com.aliya.android.api28;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试 RecyclerView
 *
 * @author a_liYa
 * @date 2020-02-12 20:26.
 */
public class RecyclerActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            mList.add(String.valueOf(i));
        }
        mAdapter = new Adapter(mList);
        mRecyclerView.setAdapter(mAdapter);

        findViewById(R.id.add_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.add(String.valueOf(mList.size()));
                mAdapter.notifyItemInserted(mList.size() - 1);
            }
        });
        findViewById(R.id.delete_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mList.isEmpty()) {
                    mList.remove(0);
                    mAdapter.notifyItemRemoved(0);
                }
            }
        });
    }

    class Adapter extends RecyclerView.Adapter {

        List<String> mData;

        public Adapter(List<String> data) {
            mData = data;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.layout_item_recycler, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
