package org.smartregister.sync.intent;

import android.content.Intent;

import org.smartregister.CoreLibrary;
import org.smartregister.job.ValidateSyncDataServiceJob;
import org.smartregister.service.ActionService;
import org.smartregister.util.NetworkUtils;

import timber.log.Timber;


public class ExtendedSyncIntentService extends BaseSyncIntentService {

    private ActionService actionService;

    public ExtendedSyncIntentService() {
        super("ExtendedSyncIntentService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        actionService = CoreLibrary.getInstance().context().actionService();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        try {
            super.onHandleIntent(workIntent);
            if (NetworkUtils.isNetworkAvailable()) {
                if (!CoreLibrary.getInstance().getSyncConfiguration().disableActionService()) {
                    actionService.fetchNewActions();
                }
                startSyncValidation();
            }

        } catch (Exception e) {
            Timber.e(e);
        }
    }

    private void startSyncValidation() {
        ValidateSyncDataServiceJob.scheduleJobImmediately(ValidateSyncDataServiceJob.TAG);
    }


}
