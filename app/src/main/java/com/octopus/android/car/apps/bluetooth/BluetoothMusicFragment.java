package com.octopus.android.car.apps.bluetooth;

import android.os.Bundle;
import android.view.View;

import com.octopus.android.car.apps.common.BaseViewBindingFragment;
import com.octopus.android.car.apps.databinding.FragmentBluetoothMusicBinding;

/**
 * A fragment representing a list of Items.
 */
public class BluetoothMusicFragment extends BaseViewBindingFragment<FragmentBluetoothMusicBinding> implements View.OnClickListener {
    private static final String ARG_COLUMN_COUNT = "column-count";
    private final String TAG = "BluetoothMusicFragment";

    public BluetoothMusicFragment() {
    }

    @SuppressWarnings("unused")
    public static BluetoothMusicFragment newInstance(int columnCount) {
        BluetoothMusicFragment fragment = new BluetoothMusicFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected FragmentBluetoothMusicBinding onCreateViewBinding() {
        return FragmentBluetoothMusicBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setListener() {
        //如果需要蓝牙音乐出声音，就需要切蓝牙音乐通道
        //ApiMain.appId(ApiMain.APP_ID_BTAV, ApiMain.APP_ID_BTAV);
        //        ApiMain.appId(ApiMain.APP_ID_AUDIO_PLAYER, ApiMain.APP_ID_AUDIO_PLAYER);
        binding.viewNext.setOnClickListener(this);
        binding.viewPlay.setOnClickListener(this);
        binding.viewPrev.setOnClickListener(this);

    }

    @Override
    public void onFragmentVisible(boolean isVisible) {
        super.onFragmentVisible(isVisible);
    }

    @Override
    public void onClick(View v) {

    }
}