package org.smartregister;

/**
 * Created by samuelgithengi on 10/19/18.
 */
public abstract class SyncConfiguration {

    private int connectionTimeout = 60000;
    private int readTimeout = 60000;

    public abstract int getSyncMaxRetries();

    public abstract SyncFilter getSyncFilterParam();

    public abstract String getSyncFilterValue();

    public abstract int getUniqueIdSource();

    public abstract int getUniqueIdBatchSize();

    public abstract int getUniqueIdInitialBatchSize();

    // determines whether to sync settings from server side. return false if not
    public boolean isSyncSettings() {
        return false;
    }

    /**
     * Flag that determines whether to sync the data that is on the device to the server if user's account is disabled
     *
     * @return true to disable sync or false to sync data before logout
     */
    public boolean disableSyncToServerIfUserIsDisabled() {
        return false;
    }

    public abstract SyncFilter getEncryptionParam();

    public abstract boolean updateClientDetailsTable();

    /**
     * Returns the read timeout in milliseconds
     *
     * @return read timeout value in milliseconds
     */
    public int getReadTimeout() {
        return readTimeout;
    }

    /**
     * Returns the connection timeout in milliseconds
     *
     * @return connection timeout value in milliseconds
     */
    public int getConnectionTime() {
        return connectionTimeout;
    }

    /**
     * Sets the connection timeout in milliseconds
     *
     * Setting this will call {@link java.net.HttpURLConnection#setConnectTimeout(int)}
     * on the {@link java.net.HttpURLConnection} instance in {@link org.smartregister.service.HTTPAgent}
     */
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    /**
     * Sets the read timeout in milliseconds
     *
     * Setting this will call {@link java.net.HttpURLConnection#setReadTimeout(int)}
     * on the {@link java.net.HttpURLConnection} instance in {@link org.smartregister.service.HTTPAgent}
     */
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }
}
