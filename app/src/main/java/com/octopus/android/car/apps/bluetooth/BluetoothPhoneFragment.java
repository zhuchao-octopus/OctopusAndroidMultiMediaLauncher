package com.octopus.android.car.apps.bluetooth;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.octopus.android.car.apps.bluetooth.adapter.BtPhoneBookAdapter;
import com.octopus.android.car.apps.bluetooth.bean.PhoneBookBean;
import com.octopus.android.car.apps.common.BaseViewBindingFragment;
import com.octopus.android.car.apps.databinding.FragmentBluetoothPhoneBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A fragment representing a list of Items.
 */
public class BluetoothPhoneFragment extends BaseViewBindingFragment<FragmentBluetoothPhoneBinding> {
    private static final String ARG_COLUMN_COUNT = "column-count";
    private final String TAG = "BluetoothPhoneFragment";
    private BtPhoneBookAdapter btPhoneBookAdapter;
    private List<PhoneBookBean> listTemp = new ArrayList<>();

    public BluetoothPhoneFragment() {
    }

    @SuppressWarnings("unused")
    public static BluetoothPhoneFragment newInstance(int columnCount) {
        BluetoothPhoneFragment fragment = new BluetoothPhoneFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected FragmentBluetoothPhoneBinding onCreateViewBinding() {
        return FragmentBluetoothPhoneBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setListener() {
        listTemp.clear();
        btPhoneBookAdapter = new BtPhoneBookAdapter(new ArrayList<>());
        binding.recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recycleView.setAdapter(btPhoneBookAdapter);
        binding.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<PhoneBookBean> bookBeanList = btPhoneBookAdapter.getBookDate();
                if (!bookBeanList.isEmpty()) {
                    Log.d(TAG, "onClick: " + bookBeanList.size());
                    btPhoneBookAdapter.removeData(0);
                }
            }
        });
        binding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 执行搜索操作
                    Log.d(TAG, "onEditorAction: " + v.getText().toString().trim());

                    String textInput = v.getText().toString().trim();
                    List<PhoneBookBean> result = listTemp.stream().filter(item -> item.getName().contains(textInput) || item.getNumber().contains(textInput)).collect(Collectors.toList());
                    btPhoneBookAdapter.setData(result);
                    // 隐藏软键盘
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                    return true;
                }
                return false;
            }
        });
        binding.ivDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public void onFragmentVisible(boolean isVisible) {
        super.onFragmentVisible(isVisible);
    }

}