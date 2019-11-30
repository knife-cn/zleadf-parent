package com.zlead.exception;


import java.lang.reflect.Field;

/**
 * <desc>
 * 全局异常
 *
 * 要求项目中产生运行时错误时抛出本异常，本异常的消息是模块定义的唯一错误码。建议在模块主包下定义枚举类型ErrorCode及错误码字段，并在枚举类型字段中定义注解ErrorDef，作为异常消息模板
 * 错误码的形式为：模块大写二字缩写+下划线+三位顺序数字，如common模块错误码为“CM_001”
 *
 * 建议构造本异常时尽量将错误的关键步骤变量值组织为异常模板参数传递出来，便于确定问题，但要注意在组织值时发生二次错误的情况。
 *
 * 本异常在呈现层（web应用）或服务层需要被处理，将异常消息返回给调用端。
 *
 * 如果没有定义注解ErrorDef，则根据参数个数构造默认模板，形式为：错误码+模板参数1|...|模板参数n
 * </desc>
 * @author hejie
 */
public class BaseException extends RuntimeException implements java.io.Serializable {

    public BaseException(){}

    Enum errorCode;
    Object[] errorArgs;

    /**
     * 没有传递异常，或主动抛出异常时使用的构造方法
     * @param errorCode 错误码
     * @param errorArgs 错误模板参数
     */
	public BaseException(Enum errorCode, Object...errorArgs) {
        super(BaseException.getLocalizedMessage(errorCode, errorArgs));
        this.errorCode = errorCode;
        this.errorArgs = errorArgs;
	}

    /**
     * 有传递异常，组装为本异常时使用的构造方法
     * @param cause 传递异常
     * @param errorCode 错误码
     * @param errorArgs 错误模板参数
     */
    public BaseException(Throwable cause, Enum errorCode, Object...errorArgs) {
        super(BaseException.getLocalizedMessage(errorCode, errorArgs) , cause);
        this.errorCode = errorCode;
        this.errorArgs = errorArgs;
    }

    /**
     * 处理本异常时，取出错误模板参数组装友好的异常消息
     * @return 错误模板参数
     */
    public Object[] getErrorArgs() {
        return errorArgs;
    }

    /**
     * 处理本异常时，取出错误代码查找错误消息模板资源文件组装友好的异常消息
     * @return 错误代码
     */
    public String getErrorCode() {
        return errorCode.name();
    }

    @Override
    public String getMessage() {
        return getLocalizedMessage();
    }

    @Override
    public String getLocalizedMessage() {
        return BaseException.getLocalizedMessage(errorCode, errorArgs);
    }

    private static String getDefaultFormatPattern(Enum errorCode, Object...errorArgs) {
        return errorCode.name() + ':' + BaseException.buildDefaultFormatIndices(errorArgs != null ? errorArgs.length : 0);
    }

    private static String getLocalizedMessage(Enum errorCode, Object...errorArgs) {
        try {
            Field field = errorCode.getClass().getDeclaredField(errorCode.name());
            ErrorDef ed = field.getAnnotation(ErrorDef.class);
            if (ed != null) {
                int countMatches = StringUtils.countMatches(ed.value(), "%s");
                String errorPattern = ed.value();
                if (errorArgs != null && errorArgs.length > countMatches)
                    errorPattern += buildDefaultFormatIndices(errorArgs.length - countMatches);
                return String.format(errorPattern, errorArgs);
            }
        }
        catch (NoSuchFieldException e) { }
        return String.format(getDefaultFormatPattern(errorCode, errorArgs), errorArgs);
    }

    /*
        根据错误模板参数个数产生默认的错误消息模板中的参数占位符部分
     */
    private static String buildDefaultFormatIndices(int num) {
        StringBuilder sb = new StringBuilder();
        sb.append(' ').append('[');
        for (int i = 0; i < num; i++)
            sb.append('%').append('s').append('|');
        if (num > 0) sb.deleteCharAt(sb.length()-1);
        sb.append(']');
        return sb.toString();
    }

   static class StringUtils{
       // Count matches
       //-----------------------------------------------------------------------
       /**
        * <p>Counts how many times the substring appears in the larger String.</p>
        *
        * <p>A <code>null</code> or empty ("") String input returns <code>0</code>.</p>
        *
        * <pre>
        * StringUtils.countMatches(null, *)       = 0
        * StringUtils.countMatches("", *)         = 0
        * StringUtils.countMatches("abba", null)  = 0
        * StringUtils.countMatches("abba", "")    = 0
        * StringUtils.countMatches("abba", "a")   = 2
        * StringUtils.countMatches("abba", "ab")  = 1
        * StringUtils.countMatches("abba", "xxx") = 0
        * </pre>
        *
        * @param str  the String to check, may be null
        * @param sub  the substring to count, may be null
        * @return the number of occurrences, 0 if either String is <code>null</code>
        */
       public static int countMatches(String str, String sub) {
           if (isEmpty(str) || isEmpty(sub)) {
               return 0;
           }
           int count = 0;
           int idx = 0;
           while ((idx = str.indexOf(sub, idx)) != INDEX_NOT_FOUND) {
               count++;
               idx += sub.length();
           }
           return count;
       }

       /**
        * Represents a failed index search.
        * @since 2.1
        */
       public static final int INDEX_NOT_FOUND = -1;

       // Empty checks
       //-----------------------------------------------------------------------
       /**
        * <p>Checks if a String is empty ("") or null.</p>
        *
        * <pre>
        * StringUtils.isEmpty(null)      = true
        * StringUtils.isEmpty("")        = true
        * StringUtils.isEmpty(" ")       = false
        * StringUtils.isEmpty("bob")     = false
        * StringUtils.isEmpty("  bob  ") = false
        * </pre>
        *
        * <p>NOTE: This method changed in Lang version 2.0.
        * It no longer trims the String.
        * That functionality is available in isBlank().</p>
        *
        * @param str  the String to check, may be null
        * @return <code>true</code> if the String is empty or null
        */
       public static boolean isEmpty(String str) {
           return str == null || str.length() == 0;
       }
   }
}
