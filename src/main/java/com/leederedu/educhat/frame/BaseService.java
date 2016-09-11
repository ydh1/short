package com.leederedu.educhat.frame;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.leederedu.educhat.utils.MyLog;

public class BaseService extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static boolean isStart = false;
	private int workCount = -1;

	@Override
	public void init() throws ServletException {
		super.init();
		this.getServletContext().setAttribute("SETTING", GKSetting.getMap());

		if (isStart) {
			return;
		} else {
			isStart = true;
		}
		startWorkingThread();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	private void startWorkingThread() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
				}
				while (true) {
					try {
						// 分10个档
						if (workCount++ > 10) {
							workCount = 0;
						}

						if (workCount % 10 == 0) {
							BaseService.this.getServletContext().setAttribute("SETTING", GKSetting.getMap());
						}
			
						// 1分钟分间隔
						Thread.sleep(60000);

					} catch (Exception e) {
						MyLog.error(e);
					}
				}
			}

		}).start();
	}
}
