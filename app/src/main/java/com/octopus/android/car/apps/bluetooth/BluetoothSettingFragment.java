package com.octopus.android.car.apps.bluetooth;

import android.os.Bundle;

import com.octopus.android.car.apps.common.BaseViewBindingFragment;
import com.octopus.android.car.apps.databinding.FragmentBluetoothSettingBinding;

/**
 * A fragment representing a list of Items.
 */
public class BluetoothSettingFragment extends BaseViewBindingFragment<FragmentBluetoothSettingBinding> {
    private static final String ARG_COLUMN_COUNT = "column-count";
    private final String TAG = "FolderItemFragment";

    public BluetoothSettingFragment() {
    }

    @SuppressWarnings("unused")
    public static BluetoothSettingFragment newInstance(int columnCount) {
        BluetoothSettingFragment fragment = new BluetoothSettingFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected FragmentBluetoothSettingBinding onCreateViewBinding() {
        return FragmentBluetoothSettingBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onFragmentVisible(boolean isVisible) {
        super.onFragmentVisible(isVisible);
    }

}