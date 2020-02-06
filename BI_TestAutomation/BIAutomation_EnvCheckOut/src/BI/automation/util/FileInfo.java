package BI.automation.util;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.VerRsrc.VS_FIXEDFILEINFO;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

public class FileInfo {
    private static int MAJOR = 0;
    private static int MINOR = 1;
    private static int BUILD = 2;
    private static int REVISION = 3;
    private static String filepath="";

    public static int getMajorVersionOfProgram(String path) {
        return getVersionInfo(path)[MAJOR];
    }

    public static int getMinorVersionOfProgram(String path) {
        return getVersionInfo(path)[MINOR];
    }

    public static int getBuildOfProgram(String path) {
        return getVersionInfo(path)[BUILD];
    }

    public static int getRevisionOfProgram(String path) {
        return getVersionInfo(path)[REVISION];
    }
    public static int[] getVersionInfo(String path) {
    	IntByReference dwDummy = new IntByReference();
        dwDummy.setValue(0);

        int versionlength = com.sun.jna.platform.win32.Version.INSTANCE.GetFileVersionInfoSize(path, dwDummy);

        byte[] bufferarray = new byte[versionlength];
        Pointer lpData = new Memory(bufferarray.length);
        PointerByReference lplpBuffer = new PointerByReference();
        IntByReference puLen = new IntByReference();
        boolean fileInfoResult = com.sun.jna.platform.win32.Version.INSTANCE.GetFileVersionInfo(path, 0, versionlength, lpData);
        boolean verQueryVal = com.sun.jna.platform.win32.Version.INSTANCE.VerQueryValue(lpData, "\\", lplpBuffer, puLen);

        VS_FIXEDFILEINFO lplpBufStructure = new VS_FIXEDFILEINFO(lplpBuffer.getValue());
        lplpBufStructure.read();

        int v1 = (lplpBufStructure.dwFileVersionMS).intValue() >> 16;
        int v2 = (lplpBufStructure.dwFileVersionMS).intValue() & 0xffff;
        int v3 = (lplpBufStructure.dwFileVersionLS).intValue() >> 16;
        int v4 = (lplpBufStructure.dwFileVersionLS).intValue() & 0xffff;
        //System.out.println("Version: " + v1 + "." + v2 + "." + v3 + "." + v4);
        return new int[] { v1, v2, v3, v4 };
    }
    
    public static String getName(String path){
    	return new File(path).getName();
    }
    
    public static void main(String args[]){
    	/*
    	String path="C:\\WorkDrive\\BI_Work\\BIAutomation_Latestfiles\\drivers\\MicrosoftEdgeDriver\\MicrosoftWebDriver_15_15063.exe";
    	
    	System.out.println(FileInfo.getBuildOfProgram(path));
    	System.out.println(FileInfo.getMajorVersionOfProgram(path));
    	System.out.println(FileInfo.getMinorVersionOfProgram(path));
    	System.out.println(FileInfo.getRevisionOfProgram(path));
    	*/
    }
}