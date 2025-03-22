package kr.co.cyberline.cmm.util.lang;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CylStringUtil extends StringUtils {
    private static final Logger logger = LoggerFactory.getLogger(CylStringUtil.class);

    public static String getRandomString(int length) {
        char[] charaters = {
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
                '4', '5', '6', '7', '8', '9' };
        return getRandomString(length, charaters);
    }

    public static String getRandomString(int length, char[] charaters) {
        StringBuilder sb = new StringBuilder("");
        Random rn = new Random();
        for (int i = 0; i < length; i++)
            sb.append(charaters[rn.nextInt(charaters.length)]);
        return sb.toString();
    }

    public static String nvl(CharSequence source, String re) {
        CharSequence val = hasText(source) ? source : re;
        return val.toString();
    }

    public static String nvl(Object s, Object o) {
        return isEmpty(String.valueOf(s)) ? String.valueOf(o) : String.valueOf(s);
    }

    public static String nvl(CharSequence source) {
        return nvl(source, "");
    }

    public static String nvl(Object o) {
        return (o == null) ? "" : o.toString();
    }

    public static String lpad(String s, char addChar, int length) {
        StringBuffer val = new StringBuffer();
        int currLength = s.length();
        for (int i = currLength; i < length; i++)
            val.append(addChar);
        return val.append(s).toString();
    }

    public static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }

    public static boolean hasLength(String str) {
        return hasLength(str);
    }

    public static boolean hasText(CharSequence str) {
        if (!hasLength(str))
            return false;
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i)))
                return true;
        }
        return false;
    }

    public static boolean isNotBlank(CharSequence str) {
        return hasText(str);
    }

    public static String carriagereturnToBr(String target) {
        String rtnVal = nvl(target).replaceAll(" ", "&nbsp;").replaceAll("\r\n", "<BR />").replaceAll("\n", "<BR />");
        return rtnVal;
    }

    public static String strToHtmlStyle(String str) {
        String resultStr = "";
        if (str != null) {
            resultStr = str.replaceAll(" ", "&nbsp;");
            resultStr = resultStr.replaceAll("\r\n", "<BR />");
            resultStr = resultStr.replaceAll("\n", "<BR />");
        }
        return resultStr;
    }

    public static String strFormat(String sourceStr, String format, char pattern) {
        String result = "";
        int sourceLength = sourceStr.length();
        if (sourceStr.equals("") || format.equals("")) {
            result = sourceStr;
        } else {
            int index = 0;
            int startIndex = 0;
            for (int i = 0; i < format.length(); i++) {
                if (format.charAt(i) == pattern) {
                    result = result + sourceStr.substring(startIndex, index) + pattern;
                    startIndex = index;
                    index--;
                }
                index++;
                if (sourceLength <= index)
                    break;
            }
            if (startIndex > 0) {
                result = result + sourceStr.substring(startIndex, index);
            } else {
                result = sourceStr;
            }
        }
        return result;
    }

    public static boolean isNumber(String input) {
        String chars = "0123456789";
        if (input == null)
            return false;
        if ("".equals(input))
            return false;
        for (int inx = 0; inx < input.length(); inx++) {
            if (chars.indexOf(input.charAt(inx)) == -1)
                return false;
        }
        return true;
    }

    public static String numFormat(String number, String format) {
        String numberStr = number;
        String tmpNumberStr = "";
        int strLenght = numberStr.length();
        int sit = 0;
        if (strLenght > 3) {
            sit = strLenght % 3;
            if (sit > 0) {
                tmpNumberStr = tmpNumberStr + numberStr.substring(0, sit) + format;
                strLenght -= sit;
            }
            while (strLenght > 3) {
                tmpNumberStr = tmpNumberStr + numberStr.substring(sit, sit + 3) + format;
                strLenght -= 3;
                sit += 3;
            }
            tmpNumberStr = tmpNumberStr + numberStr.substring(sit, sit + 3);
            numberStr = tmpNumberStr;
        }
        return numberStr;
    }

    public static String getEncoding(String strVal, String org, String enc) {
        String returnVal = "";
        try {
            returnVal = new String(strVal.getBytes(org), enc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return returnVal;
    }

    public static String isoToKSC5601(String Unicodestr) {
        if (Unicodestr == null)
            return null;
        String str = null;
        try {
            str = new String(Unicodestr.getBytes("8859_1"), "KSC5601");
        } catch (UnsupportedEncodingException e) {
            str = Unicodestr;
        } catch (Exception e) {
            return str;
        }
        return str;
    }

    public static String kscToISO8859(String Unicodestr) {
        if (Unicodestr == null)
            return null;
        String str = null;
        try {
            str = new String(Unicodestr.getBytes("KSC5601"), "8859_1");
        } catch (UnsupportedEncodingException e) {
            str = Unicodestr;
        } catch (Exception e) {
            return str;
        }
        return str;
    }

    public static String toReplace(String str, String token, String to) {
        String resultStr = "";
        if (str == null || str.equals(""))
            return resultStr;
        int idx = -1;
        while ((idx = str.indexOf(token)) != -1) {
            resultStr = resultStr + str.substring(0, idx) + to;
            str = str.substring(idx + token.length());
        }
        resultStr = resultStr + str;
        return resultStr;
    }

    public static String numToPriceNm(String amt) {
        String danwee = "";
        String price = "";
        String flag = "N";
        int amtLen = amt.length();
        for (int i = 1; i <= amtLen; i++) {
            switch (amt.charAt(amtLen - i)) {
                case '1':
                    danwee = "일";
                case '2':
                    danwee = "이";
                case '3':
                    danwee = "삼";
                case '4':
                    danwee = "사";
                case '5':
                    danwee = "오";
                case '6':
                    danwee = "육";
                case '7':
                    danwee = "칠";
                case '8':
                    danwee = "팔";
                case '9':
                    danwee = "구";
                case '0':
                    break;
                default:
                    if (i == 1) {
                        price = danwee;
                        break;
                    }
                    switch ((i - 1) % 4) {
                        case 1:
                            danwee = danwee + "십";
                            break;
                        case 2:
                            danwee = danwee + "백";
                            break;
                        case 3:
                            danwee = danwee + "천";
                            break;
                    }
                    if (i > 4 && i < 9 && !flag.equals("M")) {
                        danwee = danwee + "만";
                        flag = "M";
                    }
                    if (i >= 9 && i < 13 && !flag.equals("U")) {
                        danwee = danwee + "억";
                        flag = "U";
                    }
                    if (i >= 13 && !flag.equals("J")) {
                        danwee = danwee + "조";
                        flag = "J";
                    }
                    price = danwee + price;
                    break;
            }
        }
        return price;
    }

    public static String unscript(String data) {
        if (data == null || data.trim().equals(""))
            return "";
        String ret = data;
        ret = ret.replaceAll("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;script");
        ret = ret.replaceAll("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;/script");
        ret = ret.replaceAll("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;object");
        ret = ret.replaceAll("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;/object");
        ret = ret.replaceAll("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;applet");
        ret = ret.replaceAll("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;/applet");
        ret = ret.replaceAll("<(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        ret = ret.replaceAll("</(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        ret = ret.replaceAll("<(F|f)(O|o)(R|r)(M|m)", "&lt;form");
        ret = ret.replaceAll("</(F|f)(O|o)(R|r)(M|m)", "&lt;form");
        return ret;
    }

    public static String getUnderscore(String str) {
        char[] chars = str.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        for (char cha : chars) {
            if (cha >= 'A' && cha <= 'Z')
                stringBuffer.append('_');
            stringBuffer.append(cha);
        }
        return stringBuffer.toString().toUpperCase();
    }

    public static String toStringByReflection(Object obj, ToStringStyle stype) {
        if (obj == null)
            return "";
        return ToStringBuilder.reflectionToString(obj, stype);
    }

    public static String toStringByReflection(Object obj) {
        return toStringByReflection(obj, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
