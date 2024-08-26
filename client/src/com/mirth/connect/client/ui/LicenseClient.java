/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * 
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL license a copy of which has
 * been included with this distribution in the LICENSE.txt file.
 */

package com.mirth.connect.client.ui;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.util.StringUtil;

import com.mirth.connect.client.core.ClientException;
import com.mirth.connect.model.LicenseInfo;

public class LicenseClient {

    private static Timer timer;
    private static boolean isLicenseExpired = false;

    public static void start() {
        stop();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                check();
            }
        };

        timer = new Timer(true);
        timer.scheduleAtFixedRate(task, 0, 24L * 60L * 60L * 1000L);
    }

    public static void stop() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private static void check() {
        try {
            LicenseInfo licenseInfo = PlatformUI.MIRTH_FRAME.mirthClient.getLicenseInfo();
            String property = PlatformUI.MIRTH_FRAME.mirthClient.getProperty("padlock", "padlockMessage");
            final ZonedDateTime now = ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
            StringBuilder builder = new StringBuilder("<html> ");
            boolean invalidLicense = false;

            if (licenseInfo.getReason() != null) {
                invalidLicense = true;
                if (!licenseInfo.isPadlock()) {
                    builder.append(licenseInfo.getReason().replace("\n", "<br/>")).append("<br/>");
                }
            }

            if (invalidLicense) {
                final String message = builder.toString();

                SwingUtilities.invokeLater(() -> {
                    if (licenseInfo.isExpired() || licenseInfo.isKeyNotFound() || licenseInfo.isUnauthorized()) {
                        PlatformUI.MIRTH_FRAME.alertError(PlatformUI.MIRTH_FRAME, message);
                    } else {
                        PlatformUI.MIRTH_FRAME.alertWarning(PlatformUI.MIRTH_FRAME, message);
                    }
                });
            }
            
            if (!StringUtil.isBlank(property)) {
                PlatformUI.MIRTH_FRAME.updatePadlockWarning(property);
            } else {
                if (StringUtils.isBlank(property) && !StringUtils.isBlank(PlatformUI.MIRTH_FRAME.getPadlockWarning())) {
                    PlatformUI.MIRTH_FRAME.updatePadlockWarning(null);
                }
            }
            
        } catch (ClientException e) {
            // Ignore
        }
    }
}
