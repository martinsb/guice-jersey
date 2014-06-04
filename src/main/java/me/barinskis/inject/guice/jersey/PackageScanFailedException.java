package me.barinskis.inject.guice.jersey;


/**
 * Copied from the Sitebricks project (http://www.sitebricks.org)
 * @author Dhanji R. Prasanna (dhanji@gmail.com)
 *
 * @see com.google.sitebricks.PackageScanFailedException
 */
class PackageScanFailedException extends RuntimeException {
    public PackageScanFailedException(String s, Exception e) {
        super(s, e);
    }
}
