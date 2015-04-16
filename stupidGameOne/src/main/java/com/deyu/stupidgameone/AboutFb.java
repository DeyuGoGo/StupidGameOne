package com.deyu.stupidgameone;

import android.app.Activity;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;

public class AboutFb {
    private final Activity mActivity;

    public AboutFb(Activity activity) {
        mActivity = activity;
    }

    public void goFb() {
        com.facebook.Session.openActiveSession(mActivity, true, new StatusCallback() {
            @Override
            public void call(com.facebook.Session session, SessionState state,
                             Exception exception) {
                // TODO Auto-generated method stub
                if (session.isOpened()) {
                    // make request to the /me API
                    Request.newMeRequest(session, new Request.GraphUserCallback() {
                        // callback after Graph API response with user object
                        @Override
                        public void onCompleted(GraphUser user, Response response) {
                            if (user != null) {
                            }
                        }
                    }).executeAsync();
                }
            }
        });
    }
}
