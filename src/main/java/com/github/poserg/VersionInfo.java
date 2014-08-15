package com.github.poserg;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VersionInfo {

    /**
     * @return the deployDate
     */
    private static VersionInfo versionInfo;
    private String buildDate;
    private String version;
    private String fullVersion;
    private static String deployDate = new SimpleDateFormat("dd.MM.yyyy").format(new Date());

    public static synchronized VersionInfo getInstance() {
        if (versionInfo == null) {
            versionInfo = new VersionInfo();
        }
        return versionInfo;
    }

    private synchronized void loadVersion() {


        /*
         * содержимое файла version.txt Version:dev-{rev}-201207041816\n"
         * FullVersion:dev-{rev}-201207041816-{node}\n" BuildDate: 04.07.2012
         * 18:16
         */
        InputStream is = null;
        InputStream revisionIs = null;
        try {
            String revision = null;
            String changeSet = null;
            is = VersionInfo.class.getClassLoader().getResourceAsStream("version.txt");
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            while (bufReader.ready()) {
                String str = bufReader.readLine();

                if (str.startsWith("Version:")) {
                    version = str.substring(8).trim();
                }

                if (str.startsWith("FullVersion:")) {
                    fullVersion = str.substring(12).trim();
                }

            }
            is.close();

            revisionIs = VersionInfo.class.getClassLoader().getResourceAsStream("revision.txt");
            bufReader = new BufferedReader(new InputStreamReader(revisionIs, "UTF-8"));
            while (bufReader.ready()) {
                String str = bufReader.readLine();

                if (str.startsWith("Revision:")) {
                    revision = str.substring(9).trim();
                }

                if (str.startsWith("ChangeSet:")) {
                    changeSet = str.substring(10).trim();
                }

                if (str.startsWith("BuildDate:")) {
                    buildDate = str.substring(10).trim();
                }

            }
            revisionIs.close();

            boolean isRevisionNotEmpty = revision != null && !revision.isEmpty();
            if (isRevisionNotEmpty) {
                version += "." + revision;
                // fullVersion += "." + revision;
            }

            if (isRevisionNotEmpty && changeSet != null && !changeSet.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                String dash = "-";
                sb.append(dash);
                sb.append(buildDate);
                sb.append(dash);
                sb.append(changeSet);
                fullVersion += sb.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {is.close();} catch (IOException ex) {}
            }
            if (revisionIs != null) {
                try {revisionIs.close();} catch (IOException ex) {}
            }
        }
    }

    private VersionInfo() {
        loadVersion();
    }

    public String getVersion() {
        return version;
    }

    public String getFullVersion() {
        return fullVersion;
    }

    public String getBuildDate() {
        return buildDate;
    }

    public String getDeployDate() {
        return deployDate;
    }
}
