package app;

import java.util.ArrayList;

//import java.awt.event.FocusEvent.Cause;

public class Ipstring {
    private String inputStr;
    private int inputStrLen;
    private ArrayList<String> oct;
    private int octNum;
    private ArrayList<String> validIps;
    private int validIpsNum;
    
    


    private void populateOctets(boolean spacesAreSeparaters) {
        

        // Writing the octets
        int iCurPos = 0;
        int iStartPos = 0; 
        int iOct = 0;

        oct = new ArrayList<>();

        for (int m1 = inputStrLen - 1; iCurPos < m1; iCurPos++) {
            
            if (inputStr.charAt(iCurPos) == '.' || 
                    (spacesAreSeparaters && inputStr.charAt(iCurPos) == ' ')) {
                
                if (iCurPos == iStartPos) {
                    oct.add(null);
                    iOct++;
                    continue;
                }
                
                oct.add(inputStr.substring(iStartPos, iCurPos));
                iOct++;
                iStartPos = iCurPos + 1;

                if (inputStr.charAt(iCurPos) == ' ') {
                    oct.add(null);
                }
            }
        }
                
        // Writing the last octet
        // iCurPos == inputStrLen - 1
        oct.add(inputStr.substring(iStartPos, iCurPos));
        octNum = iOct + 1;

    }

    /* // IP string check
    public static boolean isValidIp(String ipToCheck) {
        int ipToCheckLen = ipToCheck.length();
        int[] intIp = new int[4];
        
        if ( (!ipToCheck.matches("^[0-9]*\\.[0-9]*\\.[0-9]*\\.[0-9]*$")) || 
                (ipToCheckLen > 15) || 
                (ipToCheckLen < 7) ) {
            return false;
        }

            try {
                intIp[0] = Integer.parseInt();
                intIp[1] = Integer.parseInt([i + 1]);
                intIp[2] = Integer.parseInt([i + 2]);
                intIp[3] = Integer.parseInt([i + 3]);
            }

            catch (NumberFormatException) {
                return false;
            }


            if (intIp[0] < 0 || intIp[0] > 255 ||
                    intIp[1] < 0 || intIp[1] > 255 ||
                    intIp[2] < 0 || intIp[2] > 255 ||
                    intIp[3] < 0 || intIp[3] > 255) {
                return false;
            }


        return true;

    }

    */

    public void printValidIps() {
        //int len = validIps.size();
        for (int i = 0; i < validIpsNum; i++) {
            System.console().printf("%s", validIps.get(i));
            System.console().printf("%s", "\n\r");
        }
    }

    public Ipstring(String inputIpStr) throws Exception {
        inputStr = inputIpStr;
        inputStrLen = inputStr.length();        


        populateOctets(true); // false - spaces are disregarded

        StringBuilder candidate = new StringBuilder();
        int[] intOct = new int[4];
        validIps = new ArrayList<String>();
        
        intOct[0] = -1;
        intOct[1] = -1;
        intOct[2] = -1;
        intOct[3] = -1;
        int num = 0;
        for (int i = 0, k = 0, octBorder = octNum - 3; i < octBorder; i++) {
            
            if (oct.get(i) == null) {
                continue;
            }

            try {
                k = 0;
                    intOct[0] = Integer.parseInt(oct.get(i));
                    k = 1;
                    intOct[1] = Integer.parseInt(oct.get(i + 1));
                    k = 2;
                    intOct[2] = Integer.parseInt(oct.get(i + 2));
                    k = 3;
                    intOct[3] = Integer.parseInt(oct.get(i + 3));
                    k = 4;

            }

            catch (NumberFormatException ex) {
                // Unexpected behaviour:
                if (k == 4) {
                    Throwable cause4 = new Throwable("Unexpected error");
                    ex.initCause(cause4);
                    continue;
                }

                // Expected behaviour:
                i += k;
                continue;
            }

            catch (IndexOutOfBoundsException ex) {

            }

            if (
                intOct[0] >= 1 && intOct[0] <= 255 &&
                intOct[1] >= 0 && intOct[1] <= 255 &&
                intOct[2] >= 0 && intOct[2] <= 255 &&
                intOct[3] >= 1 && intOct[3] <= 255
                ) {                   
                    candidate.setLength(0);
                    candidate.append(oct.get(i));
                    candidate.append('.');
                    candidate.append(oct.get(i + 1));
                    candidate.append('.');
                    candidate.append(oct.get(i + 2));
                    candidate.append('.');
                    candidate.append(oct.get(i + 3));
                    validIps.add(candidate.toString());
                    num++;
                }
        }
        validIpsNum = num;

    }
}