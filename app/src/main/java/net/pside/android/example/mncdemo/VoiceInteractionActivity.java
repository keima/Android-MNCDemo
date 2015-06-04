package net.pside.android.example.mncdemo;

import android.app.Activity;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by keima on 15/06/02.
 */
public class VoiceInteractionActivity extends Activity {
    private static final String TAG = VoiceInteractionActivity.class.getSimpleName();

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume " + isVoiceInteraction());

        if (isVoiceInteraction()) {

            if (getIntent() != null && getIntent().getExtras() == null) {
                Log.d(TAG, "onResume" + getIntent().getExtras());
            }

            String prompt = "ほんまにええのんか？";
            getVoiceInteractor().submitRequest(new Confirm(prompt, null));
        } else {
            finish();
        }
    }

    private class Confirm extends VoiceInteractor.ConfirmationRequest {
        public Confirm(CharSequence prompt, Bundle extras) {
            super(prompt, extras);
        }

        @Override
        public void onConfirmationResult(boolean confirmed, Bundle result) {
            Log.d(TAG, String.format("onConfirmationResult: %s : %s", confirmed, result));
            if (confirmed) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://koutaimanaka.seesaa.net/"));
                startActivity(i);
            }
            finish();
        }
    }
}
