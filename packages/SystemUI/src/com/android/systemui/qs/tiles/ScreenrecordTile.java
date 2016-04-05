/*
 * Copyright (C) 2016 DEMENTED
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.systemui.qs.tiles;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.android.systemui.qs.QSTile;
import com.android.systemui.R;

import org.cyanogenmod.internal.logging.CMMetricsLogger;

public class ScreenrecordTile extends QSTile<QSTile.BooleanState> {

    public ScreenrecordTile(Host host) {
        super(host);
    }

    @Override
    protected void handleUpdateState(BooleanState state, Object arg) {
        state.visible = true;
        state.icon = ResourceIcon.get(R.drawable.ic_qs_screenrecord);
        state.label = mContext.getString(R.string.quick_settings_screenrecord_label);
    }

    @Override
    protected BooleanState newTileState() {
        return new BooleanState();
    }

    @Override
    protected void handleClick() {
        mHost.collapsePanels();
        mHandler.postDelayed(mRunnable, 850);
    }

    private Runnable mRunnable = new Runnable() {
        public void run() {
            Intent screenrecord = new Intent(Intent.ACTION_SCREENRECORD);
            mContext.sendBroadcast(screenrecord);
        }
    };

    @Override
    public int getMetricsCategory() {
        return CMMetricsLogger.TILE_SCREENRECORD;
    }

    @Override
    public void setListening(boolean listening) {
        // Do nothing
    }
}
