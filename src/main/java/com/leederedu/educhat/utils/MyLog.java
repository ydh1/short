package com.leederedu.educhat.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.PrintWriter;
import java.io.StringWriter;

public class MyLog {

    /**
     * static Level DEBUG
     DEBUG Level指出细粒度信息事件对调试应用程序是非常有帮助的。

     static Level INFO
     INFO level表明 消息在粗粒度级别上突出强调应用程序的运行过程。

     static Level WARN
     WARN level表明会出现潜在错误的情形。

     static Level ERROR
     ERROR level指出虽然发生错误事件，但仍然不影响系统的继续运行。

     static Level FATAL
     FATAL level指出每个严重的错误事件将会导致应用程序的退出。

     另外，还有两个可用的特别的日志记录级别: (以下描述来自log4j API http://jakarta.apache.org/log4j/docs/api/index.html):
     static Level ALL
     ALL Level是最低等级的，用于打开所有日志记录。

     static Level OFF
     OFF Level是最高等级的，用于关闭所有日志记录。

     日志记录器（Logger）的行为是分等级的。如下表所示：
     分为OFF、FATAL、ERROR、WARN、INFO、DEBUG、TRACE、ALL或者您定义的级别。Log4j建议只使用四个级别，优先级从高到低分别是ERROR、WARN、INFO、DEBUG。通过在这里定义的级别，您可以控制到应用程序中相应级别的日志信息的开关。比如在这里定义了INFO级别，则应用程序中所有DEBUG级别的日志信息将不被打印出来。程序会打印高于或等于所设置级别的日志，设置的日志等级越高，打印出来的日志就越少。如果设置级别为INFO，则优先级高于等于INFO级别（如：INFO、WARN、ERROR）的日志信息将可以被输出,小于该级别的如DEBUG将不会被输出。
     */
	private static Log loger = LogFactory.getLog(MyLog.class);

	public static String error(Exception e) {
		if(e != null){
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw, true);
			e.printStackTrace(pw);
			pw.flush();
			sw.flush();
			String msg = e.getMessage() + "\n" + sw.toString();
			error(msg);

			return msg;
		}else{
			return  "null";
		}

	}


	public static void debug(String log) {
		loger.debug(log);
	}

	public static void info(String log) {
		loger.info(log);
	}
	
	public static void warn(String log){
		loger.warn(log);
	}
	
	public static void error(String err) {
		loger.error(err);
	}
	
	public static void fatal(String log){
		loger.fatal(log);
	}
}
