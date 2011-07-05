package file.monitor.timerschedule;

public class ClassFileChangeListener implements FileChangeListener {

	/*
	 * 当被监听的文件发生改变时，调用此方法
	 * */
	public void fileChanged(String filename) {
		System.out
				.println("File " + filename + " modified ,it must  reload  !");
	}
}