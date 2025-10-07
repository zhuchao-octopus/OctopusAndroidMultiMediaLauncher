package com.octopus.android.car.apps.bluetooth;

import android.os.Bundle;

import com.octopus.android.car.apps.common.BaseViewBindingFragment;
import com.octopus.android.car.apps.databinding.FragmentCallingBinding;

/**
 * 拨打电话界面
 */
public class BluetoothCallingFragment extends BaseViewBindingFragment<FragmentCallingBinding> {
    private static final String ARG_COLUMN_COUNT = "column-count";
    private final String TAG = "BluetoothPhoneFragment";

    public BluetoothCallingFragment() {
    }

    @SuppressWarnings("unused")
    public static BluetoothCallingFragment newInstance(int columnCount) {
        BluetoothCallingFragment fragment = new BluetoothCallingFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected FragmentCallingBinding onCreateViewBinding() {
        return FragmentCallingBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setListener() {
    }

    @Override
    public void onFragmentVisible(boolean isVisible) {
        super.onFragmentVisible(isVisible);
    }

}