package com.octopus.android.car.apps.bluetooth;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.octopus.android.car.apps.bluetooth.adapter.BtPhoneBookAdapter;
import com.octopus.android.car.apps.bluetooth.bean.PhoneBookBean;
import com.octopus.android.car.apps.common.BaseViewBindingFragment;
import com.octopus.android.car.apps.databinding.FragmentBluetoothPhoneLogBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class BluetoothPhoneLogFragment extends BaseViewBindingFragment<FragmentBluetoothPhoneLogBinding> implements View.OnClickListener {
    private static final String ARG_COLUMN_COUNT = "column-count";
    private final String TAG = "BluetoothPhoneLogFragment";
    private BtPhoneBookAdapter btPhoneBookAdapter;
    private final List<PhoneBookBean> callIn = new ArrayList<>();
    private final List<PhoneBookBean> callOut = new ArrayList<>();
    private final List<PhoneBookBean> callMiss = new ArrayList<>();
    private int sPhoneState;

    public BluetoothPhoneLogFragment() {
    }

    @SuppressWarnings("unused")
    public static BluetoothPhoneLogFragment newInstance(int columnCount) {
        BluetoothPhoneLogFragment fragment = new BluetoothPhoneLogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected FragmentBluetoothPhoneLogBinding onCreateViewBinding() {
        return FragmentBluetoothPhoneLogBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setListener() {
        binding.ivInCallLog.setOnClickListener(this);
        binding.ivOutCallLog.setOnClickListener(this);
        binding.ivMissCallLog.setOnClickListener(this);
        binding.ivDelete.setOnClickListener(this);
        btPhoneBookAdapter = new BtPhoneBookAdapter(new ArrayList<>());
        binding.recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recycleView.setAdapter(btPhoneBookAdapter);
        callIn.clear();
        callOut.clear();
        callMiss.clear();

    }

    @Override
    public void onFragmentVisible(boolean isVisible) {
        super.onFragmentVisible(isVisible);
    }

    /**
     * @param params 数据源
     * @param type   0 拨入 1 呼出 2未接
     */
    private void updatePhone(Bundle params, int type) {
        String name = params.getString("name");
        String number = params.getString("number");

        if (TextUtils.isEmpty(name) && TextUtils.isEmpty(number)) {
            return;
        }
        if (TextUtils.isEmpty(name)) {
            name = "未知";
        }
        PhoneBookBean phoneBookBean = new PhoneBookBean();
        phoneBookBean.setNumber(number);
        phoneBookBean.setName(name);
        phoneBookBean.setTime(params.getLong("time"));
        if (type == 0) {
            callIn.add(phoneBookBean);

        } else if (type == 1) {
            callOut.add(phoneBookBean);
            btPhoneBookAdapter.setDataItem(phoneBookBean);
        } else if (type == 2) {
            callMiss.add(phoneBookBean);
        }
        Log.d(TAG, "通话联系人 name = " + phoneBookBean.toString());
    }

    @Override
    public void onClick(View v) {

    }
}