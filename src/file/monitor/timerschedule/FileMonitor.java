package file.monitor.timerschedule;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * @see  apache  common-io  FileMonitor
 * 
 * 
 * @author     lubq <lubq@adv.emcom.jp>
 * @copyright  Jun 23, 2011,Adv.EMCOM
 *
 */
public class FileMonitor {
	
	private static final FileMonitor instance = new FileMonitor();
	private Timer timer;
	private Map<String, FileMonitorTask> timerEntries;

	private FileMonitor() {
		this.timerEntries = new HashMap<String, FileMonitorTask>();
		this.timer = new Timer();
	}

	public static FileMonitor getInstance() {
		return instance;
	}

	/**
	 * 对某个文件实行监听
	 * 
	 * @param listener
	 *            The file listener
	 * @param filename
	 *            The filename to watch
	 * @param period
	 *            The watch interval.
	 */
	public void addFileChangeListener(FileChangeListener listener,

	String filename, long period) {
		this.removeFileChangeListener(filename);

		FileMonitorTask task = new FileMonitorTask(listener, filename);

		this.timerEntries.put(filename, task);
		this.timer.schedule(task, period, period);
	}

	/**
	 * 停止对某个文件的监听
	 * 
	 * @param listener
	 *            The file listener
	 * @param filename
	 *            The filename to keep watch
	 */
	public void removeFileChangeListener(String filename) {
		FileMonitorTask task = (FileMonitorTask) this.timerEntries
				.remove(filename);

		if (task != null) {
			task.cancel();
		}
	}

	private static class FileMonitorTask extends TimerTask {
		private FileChangeListener listener;
		private String filename;
		private File monitoredFile;
		private long lastModified;

		public FileMonitorTask(FileChangeListener listener, String filename) {
			this.listener = listener;
			this.filename = filename;

			this.monitoredFile = new File(filename);
			if (!this.monitoredFile.exists()) {
				return;
			}

			this.lastModified = this.monitoredFile.lastModified();
		}

		public void run() {
			long latestChange = this.monitoredFile.lastModified();
			if (this.lastModified != latestChange) {
				this.lastModified = latestChange;
				//对发生改变的文件调用处理方法
				this.listener.fileChanged(this.filename);
			}
		}
	}
}