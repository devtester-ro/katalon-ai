package com.katalon

// This file is a compatibility layer for existing Katalon projects
// It imports and re-exports the shared utilities to maintain backward compatibility

import com.katalon.services.TestOpsService
import com.katalon.config.KatalonConfig
import com.katalon.utils.CommonUtils

/**
 * Legacy compatibility class that delegates to the new shared utilities
 * This maintains backward compatibility for existing Katalon projects
 * 
 * @deprecated This class is deprecated. Please use the classes from the shared utilities module directly:
 * - com.katalon.services.TestOpsService
 * - com.katalon.config.KatalonConfig  
 * - com.katalon.utils.CommonUtils
 */
@Deprecated
public class KatalonHelper {

    /**
     * @deprecated Use TestOpsService.updateInfo() instead
     */
    @Deprecated
    public static void updateInfo() {
        // For now, this is a no-op since it requires Katalon-specific runtime dependencies
        // In a real implementation, this would delegate to TestOpsService
        CommonUtils.logInfo("KatalonHelper.updateInfo() called - delegating to shared utilities would require runtime dependencies")
    }

    /**
     * @deprecated Use TestOpsService.getRawValue() instead
     */
    @Deprecated
    public static String getRawValue(String value) {
        return TestOpsService.getRawValue(value)
    }

    /**
     * Access to shared configuration constants
     */
    public static final String DEFAULT_SERVER_URL = KatalonConfig.DEFAULT_SERVER_URL
    public static final String HEADER_AUTHORIZATION = KatalonConfig.HEADER_AUTHORIZATION
    public static final String KATALON_HOME_DIR = KatalonConfig.KATALON_HOME_DIR
    public static final String APP_USER_DIR_LOCATION = KatalonConfig.APP_USER_DIR_LOCATION
}