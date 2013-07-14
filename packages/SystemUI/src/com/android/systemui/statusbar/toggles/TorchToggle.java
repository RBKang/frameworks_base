
package com.android.systemui.statusbar.toggles;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.UserHandle;
import android.provider.Settings;
import android.view.View;

import static com.android.internal.util.aokp.AwesomeConstants.*;
import com.android.internal.util.cm.TorchConstants;
import com.android.systemui.R;
import com.android.systemui.aokp.AwesomeAction;

public class TorchToggle extends StatefulToggle {

    private boolean mActive = false;

    @Override
    public void init(Context c, int style) {
        super.init(c, style);
    }

    @Override
    protected void doEnable() {
        Intent i = new Intent(TorchConstants.ACTION_TOGGLE_STATE);
        mContext.sendBroadcast(i);
    }

    @Override
    protected void doDisable() {
        Intent i = new Intent(TorchConstants.ACTION_TOGGLE_STATE);
        mContext.sendBroadcast(i);
    }

    @Override
    public boolean onLongClick(View v) {
        startActivity(TorchConstants.INTENT_LAUNCH_APP);
        return super.onLongClick(v);
    }

    @Override
    protected void updateView() {
        if (mActive) {
            setIcon(R.drawable.ic_qs_torch_on);
            setLabel(R.string.quick_settings_torch_on_label);
            updateCurrentState(State.ENABLED);
        } else {
            setIcon(R.drawable.ic_qs_torch_off);
            setLabel(R.string.quick_settings_torch_off_label);
            updateCurrentState(State.DISABLED);
        }
        super.updateView();
    }


    public void onReceive(Context context, Intent intent) {
        mActive = intent.getIntExtra(TorchConstants.EXTRA_CURRENT_STATE, 0) != 0;
        scheduleViewUpdate();
    }

}
